package mylink.mylink.service.memberService;

import mylink.mylink.domain.Member;
import mylink.mylink.repository.memberRepository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.swing.text.html.parser.Entity;

import static org.assertj.core.api.Assertions.*;



@SpringBootTest
@ExtendWith(SpringExtension.class)
@Transactional

class JPAMemberServiceTest {

    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    EntityManager em;

    @Test
    void
    join() {
        //give
        Member member = new Member();
        member.setName("a");
        member.setAddress("address");

        //when
        memberService.join(member);

        //then
        assertThat(memberService.findMember(1L).getName()).isEqualTo("a");
        //assertThat(memberService.findMember(1L).getAddress()).isEqualTo("address");
    }

    @Test
    void 중복_아이디_검사() {
        //give
        Member member = new Member();
        member.setAddress("address");

        Member member2 = new Member();
        member2.setAddress("address"); //address 중복되게 설정

        //when
        memberService.join(member);
        memberService.join(member2);


        //then



    }

}