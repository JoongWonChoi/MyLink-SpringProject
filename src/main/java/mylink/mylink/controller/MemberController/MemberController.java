package mylink.mylink.controller.MemberController;


import lombok.RequiredArgsConstructor;
import mylink.mylink.domain.Member;
import mylink.mylink.service.memberService.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MemberController {
    @Autowired
    private final MemberService memberService;


    /*public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }*/

    /*===회원가입===*/
    @GetMapping("/join")
    public String moveToJoin(@ModelAttribute("memberForm")MemberForm memberForm) {
        return "MemberService/join";
    }

    @PostMapping("/join")
    public String join(@ModelAttribute("memberForm") @Valid MemberForm memberForm, BindingResult bindingResult) {
        Member member = new Member();
        member.createMember(memberForm.getName(),
                memberForm.getAge(),
                memberForm.getSex(),
                memberForm.getDepartment(),
                memberForm.getAddress(),
                memberForm.getPassword());
        Long join = memberService.join(member);
        System.out.println(join);
        if (join==-1L) { //멤버 객체가 저장되면 id값이 최소 0부터 하나씩 증가하기 때문에, 중복 객체 판별 시 절대 나올 수 없는 음수를 넘겨받아 판별 
            FieldError fieldError = new FieldError("memberForm", "address", "이미 존재하는 아이디입니다.");
            bindingResult.addError(fieldError);
            return "MemberService/join";
        }
        return "redirect:/";
    }

    /*===회원 목록 조회===*/
    @GetMapping("/members")
    public String memberList(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "MemberService/memberList";
    }

    /*===회원 상세보기===*/
    @GetMapping("members/{id}")
    public String memberDetail(@PathVariable("id") Long id, Model model) {
        Member member = memberService.findMember(id);
        model.addAttribute("member", member);
        return "MemberService/memberDetail";
    }

    /*===회원 정보 수정===*/
    @GetMapping("members/{id}/edit")
    public String updateForm(@PathVariable("id") Long id, Model model) {
        Member member = memberService.findMember(id);
        model.addAttribute("member", member);
        return "MemberService/memberUpdateForm";
    }

    @PostMapping("members/{id}/edit")
    public String updateMember(@PathVariable("id") Long id, @ModelAttribute("form") MemberForm form, HttpServletRequest request) {
        /*MemberForm 은 웹 계층, controller에서의 사용 용도 클래스이기때문에, 서비스 로직으로 보내는 것은 좋지 않다고 생각
         * 그렇다고 새로운 Member 엔티티 객체를 생성하는 것은, 엔티티를 마구잡이로 외부에서 반환하고 사용하는 것은 엔티티 고유의 역할을 방해할 수
         * 있기 때문에 사용하지 않음. 엔티티 클래스는 순수하게 엔티티(DB의 테이블) 생성 기능에 충실해야한다고 생각함.
         * 그러면 이런식으로 MemberForm으로 넘어온 폼의 데이터들을, 직접 꺼내어 서비스 단으로 보내는 방식을 사용하는데 이는 딱 봐도 좀 지저분함
         * 어떻게 하면 좋을까...*/
        memberService.updateMember(
                id,
                form.getName(),
                form.getAge(),
                form.getSex(),
                form.getDepartment(),
                form.getAddress(),
                form.getPassword());
        //회원 수정을 하면 수정 사항이 세션에 반영되지 못함. DB에만 적용되는 것 뿐 세션에는 처음 저장한 회원 객체만 존재.
        //따라서 새롭게 수정한 회원 객체를 세션에 등록해줘야함!
        Member updatedMember = memberService.findMember(id);
        HttpSession session = request.getSession(true);
        session.setAttribute("loginMember",updatedMember);
        return "redirect:/members";
    }

    @GetMapping("members/{id}/delete")
    public String deleteMember(@PathVariable("id") Long id, HttpServletRequest request) {
        memberService.deleteMember(id);
        //회원 객체가 삭제되면 로그인이 유지될 수 없음. 즉 세션이 끊겨야 한다고 생각
        //로그아웃과 같은 원리라고 생각! 세션에 저장되어있던 login된 회원 객체를 삭제!
        HttpSession session = request.getSession();
        session.removeAttribute("loginMember");
        return "redirect:/members";
    }


    /*@PostMapping("/join")
    public String join(Member member) {
        boolean join = memberService.join(member);
        if (join == true) {
            return "MemberService/joinSuccess";
        }
        else{
            return "MemberService/joinFailed";
        }
    }*/
    /*===로그인===*/
    /*1. 내가 생각한 로직
     * View의 로그인 폼에서는 보통 아이디(address)와 비밀번호(password)가 넘어온다. 다른 정보 기입은 본 적이 없음.
     * 따라서 로그인 폼으로 넘어오는 아이디와 비밀번호 정보만을 갖고 로그인 검증을 해야한다고 생각.
     * ::로그인 폼에서 address, password 바인딩 -> 컨트롤러를 통해 서비스 비즈니스 로직으로 전달
     * ->저장소에서 address로 객체를 찾고(findByAddress), 해당 객체의 password가 파라미터로 넘어온 password와 일치하는지 확인
     * -> 아이디와 비밀번호를 통해 기존의 객체 정보와 일치 불일치 여부를 다시 반환하는 트랜잭션 개발
     * 최종 전달을 받는 컨트롤러에서는 객체가 반환되거나(일치) 그렇지 않다면 아무것도 반환되지 않을 것.(null)
     *
     * 개발 하며 알게된 점 : String은 엄연히 클래스이고 ==연산자와 equal()연산자의 차이를 잘 이해했어야 함 ㅎㅎ..*/

    @GetMapping("/login")
    public String loginForm(@ModelAttribute("memberForm") MemberForm memberForm) {
        return "MemberService/login";
    }

    @PostMapping("/login")
    public String login(@Valid MemberForm memberForm, BindingResult bindingResult, HttpServletRequest request, HttpServletResponse response) {
        /*String id = request.getParameter("id");
        String pw = request.getParameter("pw");

        if (id.isEmpty() || pw.isEmpty()) {
            System.out.println("id or pw please");
        }
        HttpSession session = request.getSession();
        if (session.getAttribute("id") == null) {
            session.setAttribute("id", memberForm.getAddress());
            session.setAttribute("pw", memberForm.getPassword());
            System.out.println("로그인 완료");
        }*/
        HttpSession session = request.getSession(true);

        Member loginMember = memberService.login(memberForm.getAddress(), memberForm.getPassword());

        if (loginMember != null) {
            session.setAttribute("loginMember",loginMember);
            return "redirect:/";
            /*클라이언트가 서버에 접근하여 로그인 판별을 하고, 성공하면 회원 객체를 "loginMember"라는 ID로 세션 저장소에 저장됨.
            * ***그런데 만약 동시에 두 명 이상의 클라이언트가 접근하여 로그인 로직을 실행하면?***
            * 현재 Test중에는 로그인 시도를 하려면, 반드시 현재 유지되는 세션을 지우고(logout or remove) 로그인을 진행하는
            * 순차적 순서로 진행됨.
            * 그러나 동시에 두명의 클라이언트, 두개 이상의 브라우저가 실행되어 동시에 로그인을 진행하면,
            * 현재는 동일한 하나의 세션 저장소 ID("loginMember")에 저장되기 때문에 세션에 저장되는 각 회원 객체를 구분할 수 없음.
            * ===해결 방법===
            * 1. 각 회원 정보를 저장하는 세션 저장소마다 식별 가능한  고유 식별값이 필요할듯.
            *
            * <<의문점>>
            여러 시도를 해보다 진전이 되지 않아 살펴보던 도중, 클라이언트와 세션의 관계는 1:1로, 해당 브라우저가 내려갈 때 해당 브라우저로부터 저장되었던 세션이 삭제된다는 것을 발견하였다.
            * 그래서 기존에 동시 로그인을 시도하였던 브라우저 말고 타 브라우저로 서버를 띄우고 로그인을 시도해보았다. 결과는 문제가 발생하지 않고 의도했던 회원 객체로 정상적으로 로그인이 되었다.
            * 물론 기존의 브라우저에서도 세션이 잘 유지되고 있는 모습을 보였다.
            * 클라이언트와 서버의 1:1관계로부터 세션이 생성되고 유지된다고 알고있는데, 그렇다면 여기서 말하는 클라이언트란 하나의 브라우저라는 생각이 든다.
            * 그렇다면 한 브라우저 내에서 다른 탭으로 동시 로그인을 시도하였을 때 정상 작동 하지 않는 것은 정상적인 것일까?
            * =====결론, 알게된 점=====
            * 1. 서버와 클라이언트 1:1 관계로부터 필요 시 세션이 생성되고 유지된다.
            * 2. 한 브라우저 내에서 동시 로그인 불가, 그런데 다른 브라우저로 서버에 접근해보니 정상 작동. ===> "클라이언트 == 브라우저"
            * 3. 그렇다면 같은 브라우저 내에서 동시 로그인이 불가능 한것은 정상적인 로직??
            * ===========실험 결과, 학교 홈페이지 / 학과 홈페이지 / 넥슨 홈페이지/ 등은 동일 브라우저 내에서 타 객체로 동시 로그인 불가.

            * */
        } else {
            FieldError fieldError = new FieldError("memberForm", "address", "아이디가 일치하지 않거나 존재하지 않는 아이디 입니다.");
            bindingResult.addError(fieldError);
            return "MemberService/login";
        }
        /*현재 방식은, 해당하는 아이디가 없거나 잘못되었으면 fail을 나타내는 폼을 생성한 후 그곳으로 이동.
         * 해당하는 멤버가 있어도 변화하는 것은 없음. 즉 로그인 성공하면 내 정보를 보는 등의 변화가 있었으면 좋겠음.
         * ==>HttpServletRequest의 session 사용!!
         * */

    }
    // 로그아웃 ==> HTTP 세션에 저장되어있던 로그인 된 멤버 객체 삭제
    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if(session!=null){
            session.removeAttribute("loginMember");
            //session.invalidate();
        }
        return "redirect:/";
    }
}

