package mylink.mylink;

import mylink.mylink.repository.boardRepository.BoardRepository;
import mylink.mylink.repository.boardRepository.MemoryBoardRepository;
import mylink.mylink.service.boardService.BoardService;
import mylink.mylink.service.boardService.BoardServiceImpl;
import mylink.mylink.repository.memberRepository.MemberRepository;
import mylink.mylink.repository.memberRepository.MemoryMemberRepository;
import mylink.mylink.service.memberService.MemberService;
import mylink.mylink.service.memberService.MemberServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration //구성 정보 담당해준다는 애노테이션. 애플리케이션의 환경설정.
public class AppConfig {
    @Bean // 스프링 컨테이너에 빈 형태로 객체 등록
    public BoardRepository boardRepository() {
        return new MemoryBoardRepository();
    }
    @Bean
    public BoardService boardService() {
        return new BoardServiceImpl(boardRepository());
    }
    /*@Bean
    public BoardController boardController() {
        return new BoardController(boardService());
    }*/
    //왜 컨트롤러만 중복 빈 충돌 에러가 발생할까?
    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    @Bean
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }



}
