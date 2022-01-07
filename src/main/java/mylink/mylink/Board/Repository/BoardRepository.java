package mylink.mylink.Board.Repository;

import mylink.mylink.Board.domain.Board;

import java.util.List;
import java.util.Optional;
public interface BoardRepository {
    // ==== CRUD ====

    //for create
    Board save(Board board); //Board자료형 return
    //for read
    Board read(Board board);
    //for delete
    Board delete(Board board);
    //for update
    Board update(Board board);

    Optional<Board> findByTitle(String keyword);
    //게시판처럼 글
    List<Board> findAllBoards();

    void clear();




}
