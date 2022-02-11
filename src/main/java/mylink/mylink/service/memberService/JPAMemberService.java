package mylink.mylink.service.memberService;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import mylink.mylink.domain.Member;
import mylink.mylink.repository.memberRepository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


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
        Member joinMember = validateDuplicateMember(member);
        System.out.println("in Service joinMember = " + joinMember);
        if (joinMember==null) {
            return -1L;//저장 로직이기 때문에 굳이 결과값을 반환받지 않아도 되지만, 혹시 몰라 저장된 member의 id를 반환받음
        }
        return memberRepository.save(member);
        /*새롭게 알게 된 점 : null은 equals() 연산 자체가 불가능하다 . .
        CallByValue, 즉 할당된 값을 불러와 비교하기 때문에 null을 불러오는 것은 불가능함!
        null은 == 연산자로 비교하자 . .*/
    }

    private Member validateDuplicateMember(Member member) {
        List<Member> byAddress = memberRepository.findByAddress(member.getAddress());
        System.out.println(byAddress.toString());
        if (byAddress.size()!=0) { //아이디(address)로 검색한 Member테이블의 크기가 0 이 아님==> 해당 아이디를 쓰는 멤버 객체가 하나 이상존재한다는 의미
//            throw new IllegalStateException("already exists . .");
            return null;
        }
        return member;
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

    /*===로그인===*/
    /*1. 내가 생각한 로직
     * View의 로그인 폼에서는 보통 아이디(address)와 비밀번호(password)가 넘어온다. 다른 정보 기입은 본 적이 없음.
     * 따라서 로그인 폼으로 넘어오는 아이디와 비밀번호 정보만을 갖고 로그인 검증을 해야한다고 생각.
     * ::로그인 폼에서 address, password 바인딩 -> 컨트롤러를 통해 서비스 비즈니스 로직으로 전달
     * ->저장소에서 address로 객체를 찾고(findByAddress), 해당 객체의 password가 파라미터로 넘어온 password와 일치하는지 확인
     * -> 아이디와 비밀번호를 통해 기존의 객체 정보와 일치 불일치 여부를 다시 반환하는 트랜잭션 개발
     * 최종 전달을 받는 컨트롤러에서는 객체가 반환되거나(일치) 그렇지 않다면 아무것도 반환되지 않을 것.(null)
     *
     * <예외처리를 하지 않는 이유?>
     * 로그인 검증 결과를 확실하게 객체 vs null로 구분을 하여 컨트롤러에 넘길 생각.
     * 그러면 그에 따른 로그인 View 폼에서의 결과 반환을 나눌 수 있지 않을까?
     */
    @Override
    public Member login(String address, String password) {
        Member loginMember = memberRepository.login(address, password);
        System.out.println("in service"+loginMember);
        //memberRepository를 통해 검증을 마치고, 존재하는 회원인지 판별
        if (loginMember == null) {
            return null;
        }
        return loginMember;
    }

    @Override
    public void logout(String address) {

    }
}
