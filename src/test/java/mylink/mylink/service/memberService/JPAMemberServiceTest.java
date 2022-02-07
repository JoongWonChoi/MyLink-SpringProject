package mylink.mylink.service.memberService;

import mylink.mylink.domain.Member;
import mylink.mylink.repository.memberRepository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;


@SpringBootTest
@ExtendWith(SpringExtension.class)
@Transactional
class JPAMemberServiceTest {

    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;


    @Test
    void
    join() {
        //given
        Member member = new Member();
        member.setName("a");
        member.setAddress("address");

        //when
        memberService.join(member);

        //then
        assertThat(memberService.findMember(1L).getName()).isEqualTo("a");
        assertThat(memberService.findMember(1L).getAddress()).isEqualTo("address");
    }

    @Test
    void 중복_아이디_검사() {
        //given
        Member member = new Member();
        member.setAddress("address");

        Member member2 = new Member();
        member2.setAddress("address"); //address 중복되게 설정

        //when
        memberService.join(member);
        try {
            memberService.join(member2);
        } catch (IllegalStateException e) {
            e.printStackTrace();
            return;
        }
        //then
        fail();
    }

    @Test
    void 객체_삭제() {
        //given
        Member member = new Member();
        member.setAddress("address");
        memberService.join(member);

        //when
        memberService.deleteMember(1L);

        //then
        assertThat(memberService.findMember(1L)).isEqualTo(null);

    }

    /*public void setMember() {
        Member member = new Member();
        member.setName("a");
        member.setAddress("address");

        memberService.join(member);
    }*/
    @Test
    //@Rollback(value = false) ==> Rollback을 하지 않고 실제 DB에 담기는 것 까지 확인
    void 업데이트() {
        //given
        Member member = new Member();
        member.setName("jwc");
        member.setAddress("address");
        memberService.join(member);

        //when
        memberService.updateMember(1L,"update",10, "male","ddd","adf","1232");

        //then
        System.out.println(memberService.findMember(1L).toString());
        assertThat(memberService.findMember(1L).getName()).isEqualTo("update");
    }

    @Test
    void login() {
        //given
        Member member = new Member();
        member.setName("jwc");
        member.setAddress("address");
        member.setPassword("1234");
        memberService.join(member);

        //when
        Member loginMember = memberService.login("address", "1234");
        Member errorMember1 = memberService.login("address1", "1234");
        Member errorMember2 = memberService.login("address", "1234566788");
        //Member errorMember3 = memberService.login("address", "1234566788");

        //then
        assertThat(loginMember).isEqualTo(member);
        assertThat(errorMember1).isEqualTo(null);
        assertThat(errorMember2).isEqualTo(null);
    }

}
