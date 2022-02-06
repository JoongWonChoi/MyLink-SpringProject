package mylink.mylink.service.boardService;

import lombok.RequiredArgsConstructor;
import mylink.mylink.domain.Board;
import mylink.mylink.repository.boardRepository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    // Service 로직 ====> Client가  사용하는 클래스!!
    //  ===> 게시물 작성 / 수정 / 삭제 / 읽기 (CRUD) 로직 구현

    //BoardRepository boardRepository = new MemoryBoardRepository(); // ==> DB연동 여부를 모르기에 BoardRepository 인터페이스를 상속받은 구현체 MBR을 저장소로 사용
    @Autowired
    private final BoardRepository boardRepository;

    //생성자 주입 => 생성자 형태로 의존관계 주입.
    /*@Autowired // => 생성자에 붙어서 스프링 컨테이너가 자동으로 스피링 빈을 찾아서 주입.
    public BoardServiceImpl(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }*/

    /*ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
    applicationContext.getBean("boardRepository",BoardRepository.class);*/


    //게시물
    public void createPost(Board board) {
        //중복되는 title 있는지 검증 ==> 제목을 통한 검색을 위해.
        validateDuplicateTitle(board);
        boardRepository.save(board);
    }

    @Override
    public void updatePost(Board board, Long index) {

        boardRepository.update(board, index);
    }

    @Override
    public void deletePost(Long index) {
        boardRepository.delete(index);
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
    public Board viewPost(Long index) {
        Board post = boardRepository.findById(index);
        return post;
    }
}
