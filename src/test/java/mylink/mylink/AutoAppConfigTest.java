package mylink.mylink;

import mylink.mylink.repository.boardRepository.BoardRepository;
import mylink.mylink.controller.BoardController.BoardController;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

class AutoAppConfigTest {

    @Test
    //기존 AppConfig에는 @Bean 이라는 수작업을 통해 스프링 빈에 등록하였음
    //그런데 이 AutoAppConfig에선 @ComponentScan을 통해 자동으로 컴포넌트들을 스캔하여 자바 빈에 등록한다고 한다.
    // 과연 정상적으로 - 자동으로 - 싱글톤으로 - 스프링 빈에 등록되었는지 Test.
    public void checkBeansByAutoConfig() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class); // AutoAppConfig 타입을 통해 설정 정보 불러오기
        BoardRepository boardRepository = ac.getBean("boardRepository", BoardRepository.class);
        System.out.println("boardRepository = " + boardRepository);

        BoardController boardController = ac.getBean("boardController", BoardController.class);

        Assertions.assertThat(boardRepository).isInstanceOf(BoardRepository.class);
        Assertions.assertThat(boardController).isInstanceOf(BoardController.class);



    }

}