package mylink.mylink.Member.Service;

import mylink.mylink.AutoAppConfig;
import mylink.mylink.Member.Repository.MemberRepository;
import mylink.mylink.Member.domain.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@EnableAutoConfiguration
class MemberServiceImplTest {
    MemberRepository memberRepository;
    MemberService memberService;

    @BeforeEach
    void beforeEach() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class);
        this.memberRepository = ac.getBean("memberRepository", MemberRepository.class);
        this.memberService = ac.getBean("memberService", MemberService.class);
    }

    @Test
    void join() {
        Member member = new Member("최중원", 25, "male", "sw", "wnddnjs1130", "abc123");
        memberService.join(member);
        Optional<Member> byAddress = memberRepository.findByAddress(member.getAddress());
        System.out.println(byAddress.get().toString());

        assertThat(byAddress.get().getAddress()).isSameAs(member.getAddress());

    }

    @Test
    void 아이디_중복_검사() {
        Member member = new Member("최중원", 25, "male", "sw", "wnddnjs1130", "abc123");
        Member member2 = new Member("ghghgh", 24, "male", "sw2", "wnddnjs1130", "abc1234");
        Member member3 = new Member("ghghgh", 24, "male", "sw2", "bse05015", "abc1234");
        memberService.join(member);
        memberService.join(member2);
        memberService.join(member3);
        List<Member> all = memberRepository.findAll();
        System.out.println(all);
        assertThat(all.size()).isNotEqualTo(3);

    }
    @Test
    void test() {

        
    }
}