package mylink.mylink.repository.boardRepository;

import mylink.mylink.domain.Board;

import java.util.List;
import java.util.Optional;
public interface BoardRepository {
    // ==== CRUD ====

    //for create
    Long save(Board board); //Board의 식별자 Id값 반환
    //for read
    Board findById(long id); //id로 게시물 찾기
    List<Board> findAllBoards();
    //for delete
    void delete(Long index);
    //for update
    void update(Long id, String title, String body);

    Optional<List> findByTitle(String keyword);
    //게시판처럼 글

    List<Board> findOnesPosts(Long id);



    void clear();




}
