package mylink.mylink.domain;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Getter
@Setter//최대한 사용하지 않는 것이 바람질 할 것 같지만 여러가지 실험을 위해 Setter를 생성.
public class Board {

    @GeneratedValue @Id
    @Column(name="board_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name="member_id") //Member 엔티티의 'member_id' 필드에 참조함. 외래키, 연관관계의 주인!
    private Member member;

    private String title;

    private String body;

    private String uploadTime;

    /*연관관계 매핑*/
    public void addBoardInMember(Member member) { //연관관계 메서드
        this.member = member;
        member.getBoards().add(this);
    }
    /*비즈니스 로직*/
    //===게시물 생성===
    //도메인에 생성 로직을 작성하는 이유 :: 웹 계층 단인 Controller에 BoardForm을 생성시켜 놓고, 폼으로부터 넘어오는 값은 BoardForm에 저장되도록 한다.
    //Board 도메인에 직접 접근하여 정보를 저장할 수 있지만, 객체 테이블을 생성하고 유지하도록 설정해놓은 도메인의 순수 역할을 유지시키기 위해 따로 BoardForm으로 웹 계층에서 처리하도록 함.
    //따라서 BoardForm에 1차적으로 저장을 한 후, 그 다음에 도메인의 핵심 비즈니스 로직에 접근하여 Board 객체를 생성하고
    //마지막으로 서비스 비즈니스 로직 처리에 의해 영속성 컨텍스트에 저장하도록 흐름을 설정하였다.
    //또한 웹 계층의 BoardForm으로 값이 바인딩 된 이후로는 외부로부터 영향을 받을 일이 없다고 생각하여 Controller에 Board 객체를 직접 생성하여 저장 로직을 실행할 예정이다,
    /* ********************************************************** */
    //그렇다면 서비스나 레포지토리가 아닌 도메인에 직접 핵심 저장 비즈니스 로직을 설정한 이유는?
    //충분히 서비스 단 혹은 Repository 단에서 Board객체를 생성하고 인자로 넘어오는 BoardForm의 정보들을 옮겨 담고 persist할 수 있는 것 아닌감...
    //Service(서비스 관련 로직 제공)와 Repository(DB 및 영속성 컨텍스트 접근 기능) 또한 각자의 분할된 임무가 있기때문에 침해하지 않고자 그런걸까?
    //그렇다면 도메인에 직접 핵심 로직을 설정하여도 문제 될 것이 없을까??
    /* ********************************************************** */
    public void createPost(Member member, String title, String body) {
        this.member = member;
        this.title = title;
        this.body = body;

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //MM:달 mm:분, HH:24시간 기준 hh:12시간 기준
        Date dateTime = new Date();
        String time= sdf.format(dateTime);
        this.uploadTime = time;
    }

    //===게시물 update===
    //로그인 한 회원만이 게시물을 작성할 수 있고, 작성 시 회원 정보는 자동으로 불려오기 떄문에 실질적 수정 사항은 제목과 내용밖에 없도록 설정.
    public void updatePost(String title, String body) {
        this.title = title;
        this.body = body;

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date dateTime = new Date();
        String updateTime= sdf.format(dateTime);
        this.uploadTime = updateTime;
    }


}
