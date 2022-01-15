package mylink.mylink.Board.Service;

import mylink.mylink.AppConfig;
import mylink.mylink.Board.Repository.BoardRepository;
import mylink.mylink.Board.domain.Board;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BoardServiceApp {
    public static void main(String[] args) {
        /*AppConfig appConfig = new AppConfig();
        BoardService boardService = appConfig.boardService();
        BoardRepository boardRepository = appConfig.boardRepository();*/
        
        //given
        Board board = new Board( "jwc", 25, "male", "SW", "title1", "body1");
        //when
        //게시물 create
        boardS.createPost(board);
        //then
        Board result = boardService.viewPost(1L);
        System.out.println("saved name = "+result.getName());
        System.out.println("expected name = jwc");
        //=========================================================================
        System.out.println(boardRepository.findById(1L));
        //null이 반환됨 .. why??
        //혹시 싱글톤이 아닌 방식을 사용해서!?

    }
}
