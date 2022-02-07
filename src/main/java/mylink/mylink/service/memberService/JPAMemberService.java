package mylink.mylink.service.memberService;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import mylink.mylink.domain.Member;
import mylink.mylink.repository.memberRepository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


/*실질적 클라이언트에게 제공하는 비즈니스 로직을 작성하도록 한다.
 * 이 때 서바스 로직 단에서는 기능의 시작과 끝 쌍을 지어야하기 때문에 트랜잭션 기능을 추가하였다.*/
@Service
@Getter
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class JPAMemberService implements MemberService {

    @Autowired
    private final MemberRepository memberRepository;

    /*다음과 같이 생성자로써, 객체가 생성되는 시점에 작동하여 Spring 컨테이너에 있는 memberRepository Bean을 불러와
     * 의존관계를 불러올 수 있다. 하지만 이는 생각보다 코드가 복잡해질 수 있기때문에, 더욱 간편하게 @RequiredArgsConstructor 어노테이션으로 사용햐였다.*/
    /*public JPAMemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }*/

    @Override
    @Transactional //클래스 단에 readOnly로 설정되어있지만, 쓰기 기능이 가능한 Transaction이 메서드에 있으면 우선권을 갖는다ㅓ.
    public Long join(Member member) { //member객체를 넘기고, 해당 객체를 1차캐시에 저장시킨 후 그 결과값을 반환받는다. ==> 트랜잭션?
        validateDuplicateMember(member);
        return memberRepository.save(member);//저장 로직이기 때문에 굳이 결과값을 반환받지 않아도 되지만, 혹시 몰라 저장된 member의 id를 반환받음
    }

    private void validateDuplicateMember(Member member) {//
        List<Member> byAddress = memberRepository.findByAddress(member.getAddress());
        System.out.println(byAddress.toString());
        if (byAddress.size()!=0) { //아이디(address)로 검색한 Member테이블의 크기가 0 이 아님==> 해당 아이디를 쓰는 멤버 객체가 하나 이상존재한다는 의미
            throw new IllegalStateException("already exists . .");
        }
    }

    @Override
    public Member findMember(Long id) {
        Member findMember = memberRepository.findMember(id);
        return findMember;
    }

    @Override
    @Transactional
    public void deleteMember(Long id) {
        memberRepository.delete(id);
    }

    @Override
    @Transactional
    public void updateMember(Long id, String name, int age, String sex, String department, String address, String password) {
        memberRepository.update(id, name, age, sex, department, address, password);
    }
    @Override
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    @Override
    public Optional<Member> login(String address, String password) {
        return Optional.empty();
    }

    @Override
    public void logout(String address) {

    }
}
