package mylink.mylink.Board.Service;

import mylink.mylink.Board.domain.Board;

import java.util.List;

public interface BoardService {

    void createPost(Board board);
    Board readPost(Board board);
    void updatePost(Board board, Long index);
    void deletePost(Long index);
    List<Board> viewAllPosts();
    Board viewPost(Long index);



}
