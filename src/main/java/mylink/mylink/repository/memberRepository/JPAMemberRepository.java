package mylink.mylink.repository.memberRepository;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import mylink.mylink.domain.Member;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Getter
@RequiredArgsConstructor
public class JPAMemberRepository implements MemberRepository{
    /*최대한 DB에 접근, 조회, 반환 의 단순 임무를 수행하게끔 설계*/

    @PersistenceContext
    private EntityManager em; //영속성 컨텍스트 매니저 생성

    @Override
    public Long save(Member member) {
        em.persist(member); // 영속성 컨텍스트 1차 캐시에 저장 (DB에 insert문을 날려 저장하는 것이 아님. 그 전 단계)
        return member.getId();
    }

    @Override
    public List<Member> findByAddress(String address) {
        return em.createQuery("select m from Member m where m.address = :address", Member.class)
                .setParameter("address", address)
                .getResultList();
    }

    @Override
    public Member findMember(Long id) {
        return em.find(Member.class, id); //Member 엔티티 클래스에서 / 식별자 'id'로 해당 row 반환
    }

    @Override
    public void delete(Long id) {
        Member findMember = findMember(id);
        em.remove(findMember);
    }

    @Override
    public void update(Long id, String name, int age, String sex, String department, String address, String password) {
        Member member = findMember(id); //영속성 컨텍스트에 저장되어있는 멤버 객체 반환
        member.updateMember(name, age, sex, department, address, password); //Member 도메인 내의 수정 로직 실행
    }

    @Override
    public Member login(String address, String password) {
        //1. 아이디(address)로 해당하는 회원 객체 있는지 확인
        List<Member> byAddress = findByAddress(address);
        if (byAddress.size() == 0) { //address 파라미터로 탐색한 리스트 사이즈가 0 ==> 해당하는 아이디의 회원 객체가 존재하지 않음.
            return null;
        }
        Member validMember = byAddress.get(0); //회원 객체가 존재한다면, 중복 회원 존재는 불가능 하므로 항상 0번째 인덱스에 객체 존재할 것.
        System.out.println("in repo  "+validMember);
        System.out.println("param password = " + password + "member password = " + validMember.getPassword());
        //문제 발생 ==> 파라미터로 넘어온 password가 validMember의 비밀번호와 값은 같아도 다른 정보로 인지함.
        System.out.println(password== validMember.getPassword());
        // '=='연산 비교 말고 equals()로 비교하니 같은 값으로 인식! 무슨 차이가 있는거지??
        System.out.println(validMember.getPassword().equals(password));

        if (!validMember.getPassword().equals(password)) { //아이디에 맞는 존재하는 객체의 비밀번호와 파라미터로 넘어온 객체의 비밀번호 검증
            return null;
        }
        else{
            return validMember;
        }
    }

    @Override
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class).getResultList(); //Member 엔티티 조회 타입으로 조회, 리스트 형태로 반환
    }

}
