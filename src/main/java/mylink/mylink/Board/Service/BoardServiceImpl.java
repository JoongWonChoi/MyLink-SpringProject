package mylink.mylink.Board.Service;

import mylink.mylink.Board.Repository.BoardRepository;
import mylink.mylink.Board.domain.Board;
import java.util.Optional;
import java.util.List;

public class BoardServiceImpl implements BoardService{

    // Service 로직 ====> Client가  사용하는 클래스!!
    //  ===> 게시물 작성 / 수정 / 삭제 / 읽기 (CRUD) 로직 구현

    //BoardRepository boardRepository = new MemoryBoardRepository(); // ==> DB연동 여부를 모르기에 BoardRepository 인터페이스를 상속받은 구현체 MBR을 저장소로 사용
    private final BoardRepository boardRepository;

    public BoardServiceImpl(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    //게시물
    public void createPost(Board board) {
        //중복되는 title 있는지 검증 ==> 제목을 통한 검색을 위해.
        validateDuplicateTitle(board);
        boardRepository.save(board);
    }

    private void validateDuplicateTitle(Board board) {
        boardRepository.findByTitle(board.getTitle()).ifPresent(m -> {
            new IllegalStateException("same title exists . .");
        });
        /*
        if(boardRepository.findByTitle(title).isPresent()){
            System.out.println("same title exists . .")
            };
         */
    }
    public List<Board> viewAllPosts() {
        return boardRepository.findAllBoards();
    }

    @Override
    public Optional<Board> viewPost(String keyword) {
        return boardRepository.findByTitle(keyword);
    }
}
