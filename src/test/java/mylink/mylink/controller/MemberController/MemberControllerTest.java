package mylink.mylink.controller.MemberController;

import mylink.mylink.domain.Member;
import mylink.mylink.service.boardService.BoardService;
import mylink.mylink.service.memberService.MemberService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@ExtendWith(SpringExtension.class)
@Transactional
class MemberControllerTest {

    @Autowired
    MemberService memberService;
    @Autowired
    MemberController memberController;
    @Autowired
    BoardService boardService;

    @Test
    @Rollback(value = false)
    void from_Form_to_Controller() {
        /*update폼에서 정보 받은 후 업데이트 로직을 실행하려하면 자꾸 반영되지 않는 문제 발생
         * 순차적으로 memberService - memberRepo의 update로직을 테스트 해봄 -> 정상 작동
         * 이제 controller에 넘어온 form정보가 서비스 로직 까지 잘 전달되는지 위한 테스트*/

        /*테스트 결과 정상 실행 됨. 따라서 memberUpdateForm에서 컨트롤러로의 데이터 전달에 있어서 문제가 있는 것으로 생각됨.
         * 혹은 url매핑 오류?*/

        /*문제 해결 ==> @Transactional
         * 서비스 로직에 트랜잭션을 오직 읽기로 설정해놨었음.(readOnly)
         * 이에 따라 업데이트를 위해 정보를 반환해도, 읽기 전용 트랜잭션이었기 때문에 쓰기가 진행되지 않았음.
         * Service의 udpate메서드에 트랜잭션을 쓰기로 설정해주어 문제를 해결하였다.*/

        //given
        Member member = new Member();
        member.setName("join");
        member.setAddress("join");
        memberService.join(member);

        MemberForm form = new MemberForm();
        form.setName("test");
        form.setAddress("address");

        //when
        //memberController.updateMember(1L, form);

        //then
        assertThat(memberService.findMember(1L).getName()).isEqualTo("test");
        assertThat(memberService.findMember(1L).getAddress()).isEqualTo("address");
    }

    @Test
    void login() {
        //given
        Member member = new Member();
        member.setAddress("jwc");
        member.setPassword("1234");
        memberService.join(member);

        MemberForm form = new MemberForm();
        form.setAddress("jwc");
        form.setPassword("1234");

        MemberForm errorForm = new MemberForm();
        form.setAddress("jwc12345");
        form.setPassword("1234");

        //when
        /*String validation = memberController.login(form, );
        String errorValidation = memberController.login(errorForm);

        //then
        assertThat(validation).isEqualTo("redirect:/");
        //assertThat(errorValidation).isEqualTo("MemberService/loginFailed");*/

    }

    @Test
    void sessionMember_postMember_validate(HttpSession session) {
        //given
        Member member = new Member();
        member.setAddress("jwc");
        member.setName("jwc");
        memberService.join(member);

        /*session.setAttribute("loginMember", member);

        Member loginMember = (Member)session.getAttribute("loginMember");
*/
       /* //when
        Board board = new Board();
        board.setBody("body");
        board.setTitle("title");
        board.createPost(loginMember, board.getTitle(), board.getBody());
        boardService.createPost(board);

        Board post = boardService.viewPost(2L);*/

        //then
        //System.out.println(loginMember);
        //Assertions.assertThat(post.getMember()).isEqualTo(loginMember);

    }

}