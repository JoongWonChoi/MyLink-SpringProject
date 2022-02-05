package mylink.mylink.controller.BoardController;

import mylink.mylink.AutoAppConfig;
import mylink.mylink.repository.boardRepository.BoardRepository;
import mylink.mylink.domain.Board;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@Component
class BoardControllerTest {
    BoardRepository boardRepository;
    BoardController boardController;

    @BeforeEach
    void beforeEach() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class);
        this.boardRepository = ac.getBean("boardRepository", BoardRepository.class);
        this.boardController = ac.getBean("boardController", BoardController.class);
    }
    @AfterEach
    void clear(){
        boardRepository.clear();
    }

    @Transactional
    @Test
    void updatePost() {
        //given
        Board board = new Board("jwc", 25, "male", "SW", "title1", "body1");
        Board board2 = new Board("jwc2", 20, "female", "SW", "title2", "body2");
        boardController.createLinkBoard(board);
        //when ==> 새로운 board로 update
        boardController.updatePost(1L, board2);

        //then
        System.out.println(boardRepository.findById(1L).toString());
        assertThat(boardRepository.findById(1L).getIndex()).isNotSameAs(board2.getIndex()); //id값은 그대로여야함
        assertThat(boardRepository.findById(1L).getAge()).isSameAs(board2.getAge()); // 내용만 업데이트된 값들로 바뀌어야함.

    }
    @Test
    void deleteController() {

        //given
        Board board = new Board("jwc", 25, "male", "SW", "title1", "body1");
        Board board2 = new Board("jwc2", 20, "male", "SW", "title2", "body2");
        boardController.createLinkBoard(board);
        boardController.createLinkBoard(board2);
        System.out.println("before delete"+boardRepository.findAllBoards());
        //when
        boardController.deletePost(2L);
        System.out.println("after delete"+boardRepository.findAllBoards());
        //then
        //assertThat(boardRepository.findById(2L)).isEqualTo(board2);
        assertThat(boardRepository.findById(2L)).isNotEqualTo(board2);
        boardRepository.clear();
    }


}