package mylink.mylink;


import mylink.mylink.Board.Repository.BoardRepository;
import mylink.mylink.Board.Service.BoardService;
import mylink.mylink.Board.domain.Board;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class BoardServiceIntegrationTest {

    @Autowired BoardService boardService;
    @Autowired BoardRepository boardRepository;

    @Test
    public void 게시물작성 () throws Exception {
        //given
        Board board = new Board();

        board.setName("jwc");
        board.setTitle("Title");
        board.setSex("male");
        board.setAge(25);
        board.setDepartment("SW");
        board.setBody("WWW.agsd");
        //when
        boardService.createPost(board);
        //then



    }



}
