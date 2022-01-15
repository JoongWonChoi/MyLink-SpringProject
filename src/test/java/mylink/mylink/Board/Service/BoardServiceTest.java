package mylink.mylink.Board.Service;

import mylink.mylink.AppConfig;
import mylink.mylink.Board.Repository.BoardRepository;
import mylink.mylink.Board.domain.Board;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BoardServiceTest {
    BoardService boardService;
    BoardRepository boardRepository;

    @BeforeEach
    public void beforeEach() {
        AppConfig appConfig = new AppConfig();
        boardService = appConfig.boardService();
        boardRepository = appConfig.boardRepository();
    }

    //BoardService boardService = new BoardService();

    /*@AfterEach
    void clear(){
        boardService.clear();
    }*/

    @Test
    void 게시물_Create() {
        //given
        Board board = new Board("jwc", 25, "male", "SW", "title1", "body1");
        //when
        //게시물 create
        boardService.createPost(board);
        //then
        Board result = boardService.viewPost(1L);
        assertThat(result.getName()).isEqualTo("jwc");
    }


    @Test
    void 게시물_삭제() {
        //given ==> 게시물 생성
        Board board = new Board("jwc", 25, "male", "SW", "title1", "body1");
        Board board2 = new Board("jwc2", 20, "male", "SW", "title2", "body2");
        boardService.createPost(board);
        boardService.createPost(board2);
        //when ==> 게시물 삭제 로직 실행
        System.out.println(boardRepository.findByTitle("title1"));
        System.out.println(boardRepository.findById(2L).getTitle());

        //boardService.deletePost(board2.getIndex());
        //2번 게시물을 삭제한 후 id 확인.
        System.out.println("board.getIndex() = " + board.getIndex());
        System.out.println("board2.getIndex() = " + board2.getIndex());
        //then
        //assertThat(board.getIndex()).isEqualTo(1L);
    }

    /*@Test
    void 중복_제목_판별(){
    }*/
}