package mylink.mylink.service.boardService;

import lombok.RequiredArgsConstructor;
import mylink.mylink.domain.Board;
import mylink.mylink.repository.boardRepository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class JPABoardService implements BoardService{

    @Autowired private final BoardRepository boardRepository;

    @Override
    @Transactional
    public Long createPost(Board board) {
        boardRepository.save(board);
        return board.getId();
    }

    @Override
    public Board viewPost(Long id) {
        return boardRepository.findById(id);
    }

    @Override
    public List<Board> viewAllPosts() {
        return boardRepository.findAllBoards();
    }

    @Override
    @Transactional
    public void updatePost(Long id, String title, String body) {
        boardRepository.update(id, title, body);
    }

    @Override
    @Transactional
    public void deletePost(Long index) {
        boardRepository.delete(index);
    }

    @Override
    public List<Board> viewOnesPosts(Long id) {
        return boardRepository.findOnesPosts(id);
    }
}
