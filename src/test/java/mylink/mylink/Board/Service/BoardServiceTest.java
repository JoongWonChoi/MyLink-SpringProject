package mylink.mylink.Board.Service;

import mylink.mylink.AppConfig;
import mylink.mylink.Board.domain.Board;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BoardServiceTest {
    BoardService boardService;

    @BeforeEach
    public void beforeEach() {
        AppConfig appConfig = new AppConfig();
        boardService = appConfig.boardService();
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
    void 중복_제목_판별(){

    }
}