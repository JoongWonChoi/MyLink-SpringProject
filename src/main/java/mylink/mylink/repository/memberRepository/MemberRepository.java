package mylink.mylink.repository.memberRepository;

import mylink.mylink.domain.Member;

import java.util.List;


public interface MemberRepository {
    /*기존 JAVA순수 코드를 사용하는 Repository에서는 직접 저장소를 구현하고, 그 내부에 데이터 처리를 하게끔 하는 구조였다면
    * JPA를 사용하게된 후의 Repository는, "DB에 접근, 조회, 저장 등의 직접적 기능을 수행하는 객체'라고 의미를 부여할 수 있을 것 같다.
    *
    * 우선은 Repository 인터페이스를 작성하여, 어떠한 구현체가 와도 상관 없도록 설계를 해보자*/

    //회원 저장(회원가입 시 사용)
    Long save(Member member);
    //회원 address로 회원 찾기 (회원가입 시 아이디 중복 / login시 사용)
    List<Member> findByAddress(String address);
    //모든 회원 조회 (회원가입 시 아이디 중복)
    List<Member> findAll();
    //식별자로 조회
    Member findMember(Long id);



}
