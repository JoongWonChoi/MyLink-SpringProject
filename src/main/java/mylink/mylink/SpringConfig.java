package mylink.mylink;

import mylink.mylink.Board.Repository.BoardRepository;
import mylink.mylink.Board.Repository.JdbcTemplateBoardRepository;
import mylink.mylink.Board.Service.BoardService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {
    private DataSource dataSource;

    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public BoardService boardService(){
        return new BoardService(boardRepository());
    }

    @Bean
    public BoardRepository boardRepository() {
        //return new MemoryBoardRepository();
        return new JdbcTemplateBoardRepository(dataSource);
    }


}
