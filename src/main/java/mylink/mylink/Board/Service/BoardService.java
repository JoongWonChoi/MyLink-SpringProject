package mylink.mylink.Board.Service;

import mylink.mylink.Board.Repository.BoardRepository;
import mylink.mylink.Board.domain.Board;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class BoardService {
    //  ===> 게시물 작성 / 수정 / 삭제 / 읽기 (CRUD) 로직 구현
    @Autowired private final BoardRepository boardRepository;


    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    //게시물
    public void createPost(Board board){
        //중복되는 title 있는지 검증 ==> 제목을 통한 검색을 위해.
        validateDuplicateTitle(board);
        boardRepository.save(board);
    }

    private void validateDuplicateTitle(Board board) {
        boardRepository.findByTitle(board.getTitle()).ifPresent( m-> {new IllegalStateException("same title exists . .");});
        /*
        if(boardRepository.findByTitle(title).isPresent()){
            System.out.println("same title exists . .")
            };
         */

    }
    public List<Board> viewAllPosts(){
        return boardRepository.findAllBoards();
    }

}
