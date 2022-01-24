package mylink.mylink.Member.Service;

import mylink.mylink.Member.domain.Member;

import java.util.Optional;


public interface MemberService {
    //회원 가입
    void join(Member member);
    //회원 조회
    Optional<Member> findMember(String address);
    //회원 삭제
    void deleteMember(String address);
    //회원 수정
    void updateMember(String address, Member updatedMember);
    //로그인
    Optional<Member> login(String address, String password);
    //로그아웃
    void logout(String address);

}
