package mylink.mylink.Board.Repository;

import mylink.mylink.Board.domain.Board;

import java.util.List;
import java.util.Optional;
public interface BoardRepository {
    // ==== CRUD ====

    //for create
    Board save(Board board); //Board자료형 return
    //for read

    //for delete

    //for update


    Optional<Board> findByTitle(String keyword);

    List<Board> findAllBoards();




}
