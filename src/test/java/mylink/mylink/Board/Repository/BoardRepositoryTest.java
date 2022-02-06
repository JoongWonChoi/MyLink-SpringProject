package mylink.mylink.Board.Repository;

import mylink.mylink.AutoAppConfig;
import mylink.mylink.repository.boardRepository.BoardRepository;
import mylink.mylink.domain.Board;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class BoardRepositoryTest {
    private BoardRepository boardRepository;
    @BeforeEach
    public void beforeEach() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class);
        this.boardRepository = ac.getBean("boardRepository", BoardRepository.class);
    }
    @Test
    @DisplayName("게시물 저장")
    public void 게시물_저장(){
      /*  //given
        Board board = new Board("jwc", 25, "male", "SW", "title1", "body1");
        //when
        boardRepository.save(board);
        Board result = boardRepository.findByTitle("title1").get();
        //then
        Assertions.assertThat(result.getName()).isEqualTo(board.getName());
    }*/
}}

