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
    /* <===로그인 된(in session) 회원 객체로 글쓰기===>
    *  1. 세션에 회원 객체 저장 -> 세션에 저장된 객체 view 폼에 옮겨담기 -> 게시물 작성 시 사용 -> 작성된 폼에서 회원 객체 post로 바인딩 해오기
    *  ==>만약 세션이 죽어도, 폼에는 세션에 있던 정보 존재.
    *  하지만 만약 세션이 없는 상태에서 폼을 쓰는 경우가 있다면 정보가 노출될 수 있음.
    *
    *  2. 세션에 회원 객체 저장 -> 폼에서는 세션에 저장된 회원 객체 사용 -> post메서드에서도 세션에 저장된 회원 객체 사용
    *  ==>만약 글 작성 과정에서 세션 죽는다면
    *  로그인 유지는 현재 세션 기준으로 기능하기때문에, 글쓰기도 세션 기준으로 적용됨.
    *  또한 하나의 클라이언트-서버 관계에서는 하나의 세션만 생성되므로, 문제될 것이 없다고 생각.
    *
    *   **따라서 2번 방식 사용 결졍**
    *  하지만 동시 접속자 혹은 사용자가 많아진다면?
    * */
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
    //Get으로 URL 받아 기능 처리하여도 상관 없나?
    //일단 기능적으로는 성공!
    @GetMapping("/board-link/{id}/delete")
    public String deletePost(@PathVariable("id") Long id){
        boardService.deletePost(id);
        return "redirect:/board-link";
    }

    //====================Update========================
    @GetMapping("/board-link/{id}/edit")
    public String getPostForUpdate(@PathVariable("id")Long id, Model model) {
        Board post = boardService.viewPost(id);
        //DB로부터 직접 넘어온 board 객체는 민감한 정보가 있을 수 있으므로 수정에 필요한 정보만을 웹 계층의 BoardForm에 옮겨 담아 폼에 전송
        BoardForm boardForm = new BoardForm();
        boardForm.setBoardForm(post.getId(), post.getMember(), post.getTitle(), post.getBody());
        model.addAttribute("post", boardForm);
        return "Board/updateBoard";
    }
    @PostMapping("/board-link/{id}/edit")
    //@ModelAttribute
    public String updatePost(@PathVariable("id") Long id, @ModelAttribute BoardForm boardForm) {
        boardService.updatePost(boardForm.getId(), boardForm.getTitle(), boardForm.getBody());
        return "redirect:/board-link";
    }



}


