package mylink.mylink.Controller.BoardController;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BoardController {
    //Move to each Boards.
    @GetMapping("/board-link")
    public String boardLink(){
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
    //POST ==> get datas and do something , redirect to before page.
    @PostMapping("/board-link/write")
    public String createLinkBoard(){
        return "redirect:/board-link";
    }

    @GetMapping("/board-free/write")
    public String writeFreeBoard(){
        return "Board/writeFree";
    }
    @PostMapping("/board-free/write")
    public String createFreeBoard(){
        return "redirect:/";
    }



}
