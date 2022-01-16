package mylink.mylink.Controller.BoardController;

import mylink.mylink.AutoAppConfig;
import mylink.mylink.Board.Repository.BoardRepository;
import mylink.mylink.Board.Service.BoardService;
import mylink.mylink.Board.domain.Board;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

class BoardControllerTest {


    @Test
    void deleteController() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class);
        BoardRepository boardRepository = ac.getBean("boardRepository", BoardRepository.class);
        BoardService boardService = ac.getBean("boardService", BoardService.class);
        BoardController boardController = ac.getBean("boardController", BoardController.class);

        //given
        Board board = new Board("jwc", 25, "male", "SW", "title1", "body1");
        Board board2 = new Board("jwc2", 20, "male", "SW", "title2", "body2");
        boardController.createLinkBoard(board);
        boardController.createLinkBoard(board2);
        boardService.createPost(board2);
        System.out.println("before delete"+boardRepository.findAllBoards());
        //when
        boardController.deletePost(2L);
        System.out.println("after delete"+boardRepository.findAllBoards());
        //then
        //assertThat(boardRepository.findById(2L)).isEqualTo(board2);
        assertThat(boardRepository.findById(2L)).isNotEqualTo(board2);


    }


}