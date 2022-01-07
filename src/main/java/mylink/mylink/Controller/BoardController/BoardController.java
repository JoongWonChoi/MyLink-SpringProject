package mylink.mylink.Controller.BoardController;

import mylink.mylink.Board.Service.BoardService;
import mylink.mylink.Board.domain.Board;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class BoardController {

    private final BoardService boardService;

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
}
