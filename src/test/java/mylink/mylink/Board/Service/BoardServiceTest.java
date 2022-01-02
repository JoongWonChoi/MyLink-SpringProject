package mylink.mylink.Board.Service;

import mylink.mylink.Board.Repository.MemoryBoardRepository;
import mylink.mylink.Board.domain.Board;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class BoardServiceTest {

    MemoryBoardRepository mbr = new MemoryBoardRepository();
    BoardService boardService = new BoardService();

    @AfterEach
    void clear(){
        mbr.clear();
    }

    @Test
    void 게시물_Create() {
        Board board = new Board();
        board.setName("jwc");
        board.setAge(25);
        board.setSex("M");
        board.setDepartment("SW");
        board.setTitle("Test");
        board.setBody("Hello this is test case");
        //when
        //게시물 create
        boardService.createPost(board);
        //then
    }

    @Test
    void 중복_제목_판별(){
        BoardService bs = new BoardService();
        //given
        //board(게시물) 정보 생성
        Board board = new Board();
        board.setTitle("Test");
        board.setBody("Hello this is test case");

        Board board2 = new Board();
        board2.setTitle("Test");
        board2.setBody("Hello this is test case");

        boardService.createPost(board);

        //when
        //같은 title의 객체가 저장될 때 . .
        IllegalStateException e = assertThrows(IllegalStateException.class,  // cntr+alt+v -> 자동 자료형 + 변수명 생성
                () -> boardService.createPost(board2));//예외가 발생해야 함
        //then


        assertThat(e.getMessage()).isEqualTo("same title exists . .");


    }
}