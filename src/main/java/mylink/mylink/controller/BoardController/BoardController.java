package mylink.mylink.controller.BoardController;

import lombok.RequiredArgsConstructor;
import mylink.mylink.domain.Board;
import mylink.mylink.domain.Member;
import mylink.mylink.service.boardService.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class BoardController {

    @Autowired
    private final BoardService boardService; //final 키워드를 통해 전달받아온 의존관계에 대한 수정 및 누락 불가.



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

    //====================Create========================
    //GET ==> move to there
    @GetMapping("/board-link/new")
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
    @PostMapping("/board-link/new")
    public String createLinkBoard(BoardForm boardForm, HttpSession session){
        Board board = new Board();
        /*글쓰기는 로그인 세션 유지 중에만 작성 가능.
        * 따라서 게시물 작성 시 필요한 회원 정보는 view폼과 controller에서 각각 세션을 호출하여 회원 객체 사용
        * 즉, view의 폼에서 넘어오는 member객체를 controller에서 사용하는 것이 아님!
        * session.getAttribute()의 타입은 Object이므로 내가 필요한 Member타입으로 캐스팅 해줌*/
        Member loginMember = (Member)session.getAttribute("loginMember"); //"loginMember"라는 이름으로 세션 저장소에 저장된 회원 객체 불러오기
        board.createPost(loginMember,boardForm.getTitle(),boardForm.getBody());

        boardService.createPost(board);
        return "redirect:/board-link";
    }

    @GetMapping("/board-free/new")
    public String writeFreeBoard(){
        return "Board/writeFree";
    }

    @PostMapping("/board-free/new")
    public String createFreeBoard(Board board){
        return "redirect:/";
    }

    //====================Read========================
    //특정 게시물의 index를 받는 controller
    @GetMapping("/board-link/{id}")
    //Get 방식의 query string 방식 : .../post?id=1 과 같은 방식
    //Get 방식의 path value 방식 : .../post/1 과 같이 값을 경로에 넣는 방식
    //나는 post 상세보기 로직에서 파라미터 매핑을 path value방식으로 하고싶었기 때문에 PathVariable 어노테이션을 사용하였다.
    public String boardLinkDetail(@PathVariable("id") Long id, Model model){
        //Service를 통해 특정 id 값으로 post 반환해오는 로직
        Board post = boardService.viewPost(id);
        model.addAttribute("post",post);
        return "Board/boardDetail";
    }

    //====================Delete========================
    @PostMapping("/board-link/{id}")
    public String deletePost(@PathVariable("id") Long id){
        boardService.deletePost(id);
        return "redirect:/board-link";
    }

    //====================Update========================
    @GetMapping("/board-link/{id}/edit")
    public String getPostForUpdate(@PathVariable("id")Long id, Model model) {
        Board post = boardService.viewPost(id);
        model.addAttribute("post",post);
        return "Board/updateBoard";
    }
    @PostMapping("/board-link/{id}/edit")
    //@ModelAttribute
    public String updatePost(@PathVariable("id") Long id, @ModelAttribute Board board) {

        //boardService.updatePost(board, id);

        return "redirect:/board-link";
    }


}


