package mylink.mylink.Board.Service;

import mylink.mylink.Board.domain.Board;
import java.util.Optional;


import java.util.List;

public interface BoardService {

    void createPost(Board board);
    void readPost();
    void updatePost();
    void deletePost();
    List<Board> viewAllPosts();
    Optional<Board> viewPost(String keyword);



}
