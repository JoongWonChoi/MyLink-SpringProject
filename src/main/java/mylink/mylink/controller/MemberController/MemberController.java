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
    @GetMapping("/login")
    public String login() {
        return "MemberService/login";
    }

}
