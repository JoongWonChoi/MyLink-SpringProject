package mylink.mylink.controller.MemberController;


import lombok.RequiredArgsConstructor;
import mylink.mylink.domain.Member;
import mylink.mylink.service.memberService.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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
    public String moveToJoin() {
        return "MemberService/join";
    }

    @PostMapping("join")
    public String join(MemberForm form) {
        Member member = new Member();
        member.createMember(form.getName(),
                form.getAge(),
                form.getSex(),
                form.getDepartment(),
                form.getAddress(),
                form.getPassword());
        memberService.join(member);
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
    public String updateMember(@PathVariable("id") Long id, @ModelAttribute("form") MemberForm form) {
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
        return "redirect:/members";
    }

    @GetMapping("members/{id}/delete")
    public String deleteMember(@PathVariable("id") Long id) {
        memberService.deleteMember(id);
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
    * 최종 전달을 받는 컨트롤러에서는 객체가 반환되거나(일치) 그렇지 않다면 아무것도 반환되지 않을 것.(null)*/

    @GetMapping("/login")
    public String loginForm() {
        return "MemberService/login";
    }

    @PostMapping("/login")
    public String login(MemberForm form) {
        Member loginMember = memberService.login(form.getAddress(), form.getPassword());
        if (loginMember != null) {
            return "redirect:/";
        }
        else{return "MemberService/loginFailed";}

    }

}
