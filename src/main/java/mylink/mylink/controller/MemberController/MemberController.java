package mylink.mylink.controller.MemberController;


import mylink.mylink.service.memberService.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MemberController {
    private final MemberService memberService;
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }


    @GetMapping("/join")

    public String moveToJoin() {
        return "MemberService/join";
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


}
