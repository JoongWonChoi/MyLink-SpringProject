package mylink.mylink.Board.Service;

import mylink.mylink.AutoAppConfig;
import mylink.mylink.repository.boardRepository.BoardRepository;
import mylink.mylink.service.boardService.BoardService;
import mylink.mylink.domain.Board;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

class BoardServiceTest {
    /*BoardService boardService;
    BoardRepository boardRepository;*/

    /*@BeforeEach
    public void beforeEach() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class);
        this.boardRepository = ac.getBean("boardRepository", BoardRepository.class);
        this.boardService = ac.getBean("boardService", BoardService.class);

    }*/

    //BoardService boardService = new BoardService();

    @AfterEach
    void clear(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class);
        BoardRepository boardRepository = ac.getBean("boardRepository", BoardRepository.class);
        boardRepository.clear();
    }
/*

    @Test
    void 게시물_Create() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class);
        BoardRepository boardRepository = ac.getBean("boardRepository", BoardRepository.class);
        BoardService boardService = ac.getBean("boardService", BoardService.class);
        //given
        Board board = new Board("jwc", 25, "male", "SW", "title1", "body1");
        //when
        //게시물 create
        boardService.createPost(board);
        //then
        Board result = boardService.viewPost(1L);
        assertThat(result.getName()).isEqualTo("jwc");
    }

    @Transactional
    @Test
    void 게시물_삭제() {
        //어플리케이션에 등록된 빈 불러오기 (의존관계 주입이 완료된 형태임)
        ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class);
        BoardRepository boardRepository = ac.getBean("boardRepository", BoardRepository.class);
        BoardService boardService = ac.getBean("boardService", BoardService.class);
        //given ==> 게시물 생성
        Board board = new Board("jwc", 25, "male", "SW", "title1", "body1");
        Board board2 = new Board("jwc2", 20, "male", "SW", "title2", "body2");
        boardService.createPost(board);
        boardService.createPost(board2);
        //when ==> 게시물 삭제 로직 실행
        System.out.println("Before delete --> ");
        boardRepository.findAllBoards(); //싱글톤 boardRepository 객체에 두가지 게시물이 저장되었나 확인

        boardService.deletePost(board2.getIndex());
        //2번 게시물을 삭제한 후 id 확인.
        System.out.println("After delete --> ");boardRepository.findAllBoards(); //싱글톤 boardRepository 객체에서 게시물이 삭제되었나 확인

        */
/*//*
/BoardRepository hashmap에서 저장된 게시물 두가지 중 2번쨰 게시물은 remove된 것이지 2번 index를 갖는 board가 아예 사라진 것은 아님!!
        //따라서 이렇게 로그를 출력하면 여전히 board에 관한 정보가 출력됨.
        System.out.println("board.getIndex() = " + board.getIndex());
        System.out.println("board2.getIndex() = " + board2.getIndex());*//*

        //then
        //assertThat(boardRepository.findById(2L)).isEqualTo(board2);
        assertThat(boardRepository.findById(2L)).isNotSameAs(board2);

    }
*/

    /*@Test
    void 중복_제목_판별(){
    }*/
}