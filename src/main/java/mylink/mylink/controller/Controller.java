package mylink.mylink.controller;


import org.springframework.web.bind.annotation.GetMapping;

@org.springframework.stereotype.Controller // Controller 애노테이션 내에 Component 애노테이션 포함되어있음.
//alt + cntr + b ==> 상세보기

public class Controller {
    @GetMapping("/")
    public String home() {
        return "home";
    }
}
