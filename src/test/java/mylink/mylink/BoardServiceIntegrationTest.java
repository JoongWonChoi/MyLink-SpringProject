package mylink.mylink;


import mylink.mylink.Board.Repository.BoardRepository;
import mylink.mylink.Board.Service.BoardServiceImpl;
import mylink.mylink.Board.domain.Board;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class BoardServiceIntegrationTest {

    @Autowired
    BoardServiceImpl boardService;
    @Autowired BoardRepository boardRepository;

    @Test
    public void 게시물작성 () throws Exception {
        //given
        Board board = new Board(1L,"jwc",25,"male","SW","title1","body1");

        //when
        boardService.createPost(board);
        //then



    }



}
