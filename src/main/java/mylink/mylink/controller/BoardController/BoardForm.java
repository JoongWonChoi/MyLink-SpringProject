package mylink.mylink.controller.BoardController;

import lombok.Getter;
import lombok.Setter;
import mylink.mylink.domain.Member;

@Getter
@Setter
public class BoardForm {
    private Member member;
    private String department;
    private String title;
    private String body;


}
