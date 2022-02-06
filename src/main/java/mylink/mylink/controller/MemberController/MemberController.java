package mylink.mylink.controller.MemberController;


import lombok.RequiredArgsConstructor;
import mylink.mylink.domain.Member;
import mylink.mylink.service.memberService.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping("members/{id}")
    public String memberDetail(@PathVariable("id") Long id, Model model) {
        Member member = memberService.findMember(id);
        model.addAttribute("member", member);
        return "MemberService/memberDetail";
    }


    @GetMapping("members/{id}/edit")
    public String updateForm(@PathVariable("id") Long id, Model model) {

        Member member = memberService.findMember(id);
        model.addAttribute("member", member);
        return "MemberService/memberUpdateForm";
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

    @GetMapping("/login")
    public String login() {

        return "MemberService/login";
    }

    @GetMapping("/members")
    public String memberList(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "MemberService/memberList";
    }
}
