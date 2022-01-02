package mylink.mylink.Controller;


import org.springframework.web.bind.annotation.GetMapping;

@org.springframework.stereotype.Controller
public class Controller {

    @GetMapping("/")
    public String home(){
        return "home";
    }
    @GetMapping("/login")
    public String login(){
        return "MemberService/login";
    }
    @GetMapping("/join")
    public String join(){
        return "MemberService/join";
    }

}
