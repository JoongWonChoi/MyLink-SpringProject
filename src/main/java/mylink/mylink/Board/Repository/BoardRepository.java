package mylink.mylink.Board.Repository;

import mylink.mylink.Board.domain.Board;

import java.util.Optional;
public interface BoardRepository {

    Board save(Board board); //Board자료형 return
    Optional<Board> findByTitle(String keyword);




}
