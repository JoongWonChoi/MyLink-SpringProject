package mylink.mylink.service.memberService;

import mylink.mylink.domain.Board;
import mylink.mylink.domain.Member;

import java.util.List;


public interface MemberService {
    //회원 가입
    Long join(Member member);
    //회원 조회
    Member findMember(Long id);
    //회원 삭제
    void deleteMember(Long id);
    //회원 수정
    void updateMember(Long id, String name, int age, String sex, String department,String address, String password);

    //로그인
    //Optional<Member> login(String address, String password); //엔티티의 식별자로 각 객체를 식별하므로, address로 판별하는 것보다 식별자를 사용하는 방식이 더 나을 것 같다.
    /*생각해보면 로그인을 할 때에는 아이디(address)와 비밀번호(password)만이 넘어온다.
    * 그렇다면 식별자를 확인할 수 있는 방법은?
    *
    * 그냥 address와 password를 넘기고, 이에 해당하는 객체를 찾아내는 로직을 만들자.
    * 그렇게 하면 이에 맞는 Member객체를 받아올 수 있을 것.*/
    Member login(String address, String password);

    //로그아웃
    void logout(String address);

    //회원 목록 조회
    List<Member> findMembers();

    List<Board> viewMemberBoards(Long id);

}
