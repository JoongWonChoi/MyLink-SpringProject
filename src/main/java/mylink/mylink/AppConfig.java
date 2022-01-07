package mylink.mylink;

import mylink.mylink.Board.Repository.BoardRepository;
import mylink.mylink.Board.Repository.MemoryBoardRepository;
import mylink.mylink.Board.Service.BoardService;
import mylink.mylink.Board.Service.BoardServiceImpl;

public class AppConfig {

    public BoardService boardService() {
        return new BoardServiceImpl(boardRepository());
    }
    public BoardRepository boardRepository() {
        return new MemoryBoardRepository();
    }

}
