package mylink.mylink.repository.boardRepository;

import mylink.mylink.AppConfig;
import mylink.mylink.service.boardService.BoardService;
import mylink.mylink.domain.Board;

public class BoardRepositoryApp {
    public static void main(String[] args) {
        AppConfig appConfig = new AppConfig();
        BoardService boardService = appConfig.boardService();
        BoardRepository boardRepository = appConfig.boardRepository();

        Board board = new Board( "jwc", 25, "male", "SW", "title1", "body1");
        boardRepository.save(board);
        Board result = boardRepository.findByTitle("title1").get();
        System.out.println(result.getName()+"  is equal to  "+board.getName());
    }
}
