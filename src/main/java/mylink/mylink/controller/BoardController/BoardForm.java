package mylink.mylink.controller.BoardController;

import lombok.Getter;
import lombok.Setter;
import mylink.mylink.domain.Member;

@Getter
@Setter
public class BoardForm {
    private Long id;
    private Member member;
    private String title;
    private String body;

    public void setBoardForm(Long id, Member member, String title, String body) {
        this.id = id;
        this.member = member;
        this.title = title;
        this.body = body;
    }


}
