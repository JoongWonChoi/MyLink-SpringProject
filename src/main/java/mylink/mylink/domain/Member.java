package mylink.mylink.domain;


import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Member {

    /*기존의 도메인 설계는 단지 클래스 형태로, 필드값을 미리 선언해 놓은 후 사용하는 용도로 인식하여 설계하였다.
    * 하지만 JPA의 존재를 알게 된 후, 도메인 설계는 DB의 테이블 설계를 한다는 느낌으로 설계해야한다는 것을 깨달았다.
    * DB의 '테이블'의 개념이 이 Spring에선 Entity라고 사용되고, 객체 기준으로 설계하게 된다.
    * 또한 이 엔티티 클래스에서, 다른 엔티티와의 연관관계등을 설정해주어야 한다.*/

    @GeneratedValue @Id
    @Column(name="member_id")
    private long id;

    private String name;

    private int age;

    private String sex;

    private String department;

    private String address;

    private String password;

    //Order 엔티티와 연관관계 매핑
    @OneToMany(mappedBy = "member") //Order 클래스의 Member타입을 참조하는 'member' 필드에 의해 참조됨
    private List<Board> boards = new ArrayList<>();


}
