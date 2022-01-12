package mylink.mylink.Board.Service;

import mylink.mylink.Board.domain.Board;

import java.util.List;

public interface BoardService {

    void createPost(Board board);
    Board readPost(Board board);
    Board updatePost(Board board);
    Board deletePost(Board board);
    List<Board> viewAllPosts();
    Board viewPost(Long index);



}
