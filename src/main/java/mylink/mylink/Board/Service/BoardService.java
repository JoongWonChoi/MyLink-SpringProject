package mylink.mylink.Board.Service;

import mylink.mylink.Board.Repository.MemoryBoardRepository;
import mylink.mylink.Board.domain.Board;

import java.util.List;

import static org.springframework.util.ClassUtils.isPresent;

public class BoardService {
    //  ===> 게시물 작성 / 수정 / 삭제 / 읽기 (CRUD) 로직 구현
    MemoryBoardRepository mbr = new MemoryBoardRepository();

    //게시물
    public void createPost(Board board){
        //중복되는 title 있는지 검증 ==> 제목을 통한 검색을 위해.
        validateDuplicateTitle(board);
        mbr.save(board);
    }

    private void validateDuplicateTitle(Board board) {
        mbr.findByTitle(board.getTitle()).ifPresent( m-> {new IllegalStateException("same title exists . .");});
        /*
        if(mbr.findByTitle(title).isPresent()){
            System.out.println("same title exists . .")
            };
         */

    }
    public List<Board> viewAllPosts(){
        return mbr.findAllBoards();
    }

}
