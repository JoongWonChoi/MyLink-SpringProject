package mylink.mylink.service.boardService;

import mylink.mylink.domain.Board;
import mylink.mylink.domain.Member;
import mylink.mylink.repository.boardRepository.BoardRepository;
import mylink.mylink.service.memberService.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@Transactional
class JPABoardServiceTest {
    @Autowired
    BoardService boardService;
    @Autowired
    BoardRepository boardRepository;
    @Autowired
    MemberService memberService;

    @Test
    @Rollback(value = false)
    void save() {
        //given
        Member member = new Member();
        member.createMember("A",1,"male","sw","id","pw");
        memberService.join(member);


        //when
        Board board = new Board();
        board.createPost(memberService.findMember(1L), "title","body");
        Long post = boardService.createPost(board);
        System.out.println("post = " + post);

        Member member2 = new Member();
        member2.createMember("A",1,"male","sw","id2","pw");
        memberService.join(member2);

        Board board2 = new Board();
        board2.createPost(memberService.findMember(3L), "title2","body2");
        Long post2 = boardService.createPost(board2);
        System.out.println("post2 = " + post2);


        //then
        //System.out.println(boardRepository.findById(1L).toString());
        //Assertions.assertThat(boardRepository.findById(2L)).isEqualTo(board);
    }

    @Test
    void read() {
        //given
        //when
        //then
    }

    @Test
    void readAll() {
        //given
        Member member = new Member();
        member.createMember("A",1,"male","sw","id","pw");
        memberService.join(member);


        //when
        Board board = new Board();
        board.createPost(memberService.findMember(1L), "title","body");
        Long post = boardService.createPost(board);

        Member member2 = new Member();
        member2.createMember("A",1,"male","sw","id2","pw");
        memberService.join(member2);

        Board board2 = new Board();
        board2.createPost(memberService.findMember(2L), "title2","body2");
        Long post2 = boardService.createPost(board2);

        //then
        List<Board> boards = boardService.viewAllPosts();
        Assertions.assertThat(boards.size()).isSameAs(2);

    }
    @Test
    void update() {
        //given
        //when
        //then
    }

    @Test
    void delete() {
        //given
        //when
        //then
    }




}