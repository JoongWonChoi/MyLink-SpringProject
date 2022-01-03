package mylink.mylink.Board.Service;

import mylink.mylink.Board.Repository.MemoryBoardRepository;
import mylink.mylink.Board.domain.Board;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

class BoardServiceTest {

    MemoryBoardRepository mbr = new MemoryBoardRepository();
    //BoardService boardService = new BoardService();

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

        //then
    }

    @Test
    void 중복_제목_판별(){


    }
}