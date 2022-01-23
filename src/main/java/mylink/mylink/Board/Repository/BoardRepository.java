package mylink.mylink.Board.Repository;

import mylink.mylink.Board.domain.Board;

import java.util.List;
import java.util.Optional;
public interface BoardRepository {
    // ==== CRUD ====

    //for create
    Board save(Board board); //Board자료형 return
    //for delete
    void delete(Long index);
    //for update
    void update(Board updatedBoard, Long index);

    Optional<Board> findByTitle(String keyword);
    //게시판처럼 글

    Board findById(long id); //id로 게시물 찾기
    List<Board> findAllBoards();

    void clear();




}
