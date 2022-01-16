package mylink.mylink.Controller.BoardController;

import mylink.mylink.Board.Service.BoardService;
import mylink.mylink.Board.domain.Board;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;


@Controller
public class BoardController {

    private final BoardService boardService; //final 키워드를 통해 전달받아온 의존관계에 대한 수정 및 누락 불가.

        @Autowired
        public BoardController(BoardService boardService) {
            this.boardService = boardService;
        }

    //Move to each Boards.
    @GetMapping("/board-link")
    public String boardLink(Model model){
        List<Board> boards = boardService.viewAllPosts();
        model.addAttribute("boards", boards);
        return "Board/boardLink";
    }
    @GetMapping("/board-free")
    public String boardFree(){
        return "Board/boardFree";
    }
    //Move to each WRITE PAGE..

    //GET ==> move to there
    @GetMapping("/board-link/write")
    public String writeLinkBoard(){
        return "Board/writeLink";
    }
    //POST ==> get data and do something , redirect to before page.
    /*@PostMapping("/board-link/write")
    public String createLinkBoard(BoardForm form){
        Board board = new Board();

        board.setName(form.getName());
        board.setAge(form.getAge());
        board.setSex(form.getSex());
        board.setTitle(form.getTitle());
        board.setBody(form.getBody());

        boardService.createPost(board);
        return "redirect:/board-link";
    }*/
    @PostMapping("/board-link/write")
    public String createLinkBoard(Board board){
        boardService.createPost(board);
        return "redirect:/board-link";
    }
    @GetMapping("/board-free/write")
    public String writeFreeBoard(){
        return "Board/writeFree";
    }

    @PostMapping("/board-free/write")
    public String createFreeBoard(Board board){
        return "redirect:/";
    }

    //특정 게시물의 index를 받는 controller
    @GetMapping("/board-link/post/{id}")
    //Get 방식의 query string 방식 : .../post?id=1 과 같은 방식
    //Get 방식의 path value 방식 : .../post/1 과 같이 값을 경로에 넣는 방식
    //나는 post 상세보기 로직에서 파라미터 매핑을 path value방식으로 하고싶었기 때문에 PathVariable 어노테이션을 사용하였다.
    public String boardLinkDetail(@PathVariable("id") Long id, Model model){
        //Service를 통해 특정 id 값으로 post 반환해오는 로직
        Board post = boardService.viewPost(id);
        model.addAttribute("post",post);
        return "Board/boardDetail";
    }
    @PostMapping("/board-link/post/{id}")
    public String deletePost(@PathVariable("id") Long id){
        boardService.deletePost(id);
        return "redirect:/board-link";
    }

    @GetMapping("/board-link/post/update/{id}")
    public String getPostForUpdate(@PathVariable("id")Long id) {
        return "Board/updateBoard";
    }
    @PostMapping("/board-link/post/update/{id}")
    public String updatePost(@PathVariable("id") Long id,Board board) {
        boardService.updatePost(board);
        return "redirect:/board-link";
    }


}


