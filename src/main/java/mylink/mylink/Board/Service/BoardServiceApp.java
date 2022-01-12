package mylink.mylink.Board.Service;

import mylink.mylink.AppConfig;
import mylink.mylink.Board.domain.Board;

public class BoardServiceApp {
    public static void main(String[] args) {
        AppConfig appConfig = new AppConfig();
        BoardService boardService = appConfig.boardService();

        //given
        Board board = new Board( "jwc", 25, "male", "SW", "title1", "body1");
        //when
        //게시물 create
        boardService.createPost(board);
        //then
        Board result = boardService.viewPost(1L);

        System.out.println("saved name = "+result.getName());
        System.out.println("expected name = jwc");
    }
}
