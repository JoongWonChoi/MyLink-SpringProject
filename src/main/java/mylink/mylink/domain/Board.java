package mylink.mylink.domain;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
public class Board {

    @GeneratedValue @Id
    @Column(name="board_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name="member_id") //Member 엔티티의 'member_id' 필드에 참조함. 외래키, 연관관계의 주인!
    private Member member;

    private String title;

    private String body;

    private LocalDateTime uploadTime;


}
