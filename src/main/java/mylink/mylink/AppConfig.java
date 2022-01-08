package mylink.mylink;

import mylink.mylink.Board.Repository.BoardRepository;
import mylink.mylink.Board.Repository.MemoryBoardRepository;
import mylink.mylink.Board.Service.BoardService;
import mylink.mylink.Board.Service.BoardServiceImpl;
import mylink.mylink.Controller.BoardController.BoardController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class AppConfig {
    @Bean
    public BoardRepository boardRepository() {
        return new MemoryBoardRepository();
    }
    @Bean
    public BoardService boardService() {
        return new BoardServiceImpl(boardRepository());
    }
    @Bean
    public BoardController boardController() {
        return new BoardController(boardService());
    }



}
