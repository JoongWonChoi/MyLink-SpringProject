package mylink.mylink.repository.boardRepository;


import lombok.RequiredArgsConstructor;
import mylink.mylink.domain.Board;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class JPABoardRepository implements BoardRepository {
    /*Fore create*/
    @PersistenceContext
    private final EntityManager em;

    @Override
    public Long save(Board board) {
        em.persist(board);
        return board.getId();
    }
    /*Fore read*/
    @Override
    public Board findById(long id) {
        return em.find(Board.class, id);
    }

    @Override
    public List<Board> findAllBoards() {
        return em.createQuery("select b from Board b", Board.class).getResultList();

    }
    /*Fore delete*/
    @Override
    public void delete(Long index) {
        Board board = em.find(Board.class, index);
        em.remove(board);
    }


    /*Fore update*/
    @Override
    public void update(Long id, String title, String body) {
        Board updateBoard = em.find(Board.class, id);
        updateBoard.updatePost(title, body);
    }


    @Override
    public Optional<List> findByTitle(String keyword) {
        List<Board> boardList = em.createQuery("select b from Board b where b.title = :keyword", Board.class)
                .setParameter("keyword", keyword)
                .getResultList();
        return Optional.ofNullable(boardList);
        //boardList가 존재하지 않는다면, 즉 전달된 keyword에 해당하는 제목을 갖는 객체가 없다면
        //OPtional.ofNullalbe()을 통해 null값 전달

    }

    @Override
    public List<Board> findOnesPosts(Long id) {
        return em.createQuery("select m.boards from Member m where m.id =:id")
                .setParameter("id", id)
                .getResultList();
    }

    @Override
    public void clear() {

    }
}
