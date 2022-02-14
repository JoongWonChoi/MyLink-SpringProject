package mylink.mylink.service.boardService;

import mylink.mylink.domain.Board;

import java.util.List;

public interface BoardService {
    //Create
    Long createPost(Board board);
    //Read
    Board viewPost(Long id);
    List<Board> viewAllPosts();
    //Update
    void updatePost(Long id, String title, String body);
    //Delete
    void deletePost(Long index);




}
