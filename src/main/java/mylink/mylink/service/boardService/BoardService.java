package mylink.mylink.service.boardService;

import mylink.mylink.domain.Board;

import java.util.List;

public interface BoardService {

    void createPost(Board board);

    void updatePost(Board board, Long index);
    void deletePost(Long index);
    List<Board> viewAllPosts();
    Board viewPost(Long index);



}
