package mylink.mylink.Board.Repository;

import mylink.mylink.AppConfig;
import mylink.mylink.Board.Service.BoardService;
import mylink.mylink.Board.domain.Board;

public class BoardRepositoryApp {
    public static void main(String[] args) {
        AppConfig appConfig = new AppConfig();
        BoardService boardService = appConfig.boardService();

        Board board = new Board(1L, "jwc", 25, "male", "SW", "title1", "body1");
        boardService.createPost(board);
        System.out.println(board.getIndex()+"  "+board.getTitle());
    }
}
