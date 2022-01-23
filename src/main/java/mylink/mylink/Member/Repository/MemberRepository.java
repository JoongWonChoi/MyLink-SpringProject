package mylink.mylink.Member.Repository;

import mylink.mylink.Member.domain.Member;

import java.util.List;

public interface MemberRepository {
    //회원 저장(회원가입 시 사용)
    void save(Member member);
    //회원 address로 회원 찾기 (회원가입 시 아이디 중복 / login시 사용)
    Member findByAddress(String address);
    //모든 회원 조회 (회원가입 시 아이디 중복)
    List<Member> findAll();



}
