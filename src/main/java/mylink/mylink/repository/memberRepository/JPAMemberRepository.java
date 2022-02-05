package mylink.mylink.repository.memberRepository;

import lombok.Getter;
import mylink.mylink.domain.Member;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Getter
public class JPAMemberRepository implements MemberRepository{
    /*최대한 DB에 접근, 조회, 반환 의 단순 임무를 수행하게끔 설계*/

    @PersistenceContext
    private EntityManager em; //영속성 컨텍스트 매니저 생성

    @Override
    public Long save(Member member) {
        em.persist(member); // 영속성 컨텍스트 1차 캐시에 저장 (DB에 insert문을 날려 저장하는 것이 아님. 그 전 단계)
        return member.getId();
    }
    //동적 쿼리 사용하기!
    @Override
    public List<Member> findByAddress(String address) {
        return em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", address)
                .getResultList();
    }

    @Override
    public List<Member> findAll() {
        return em.createQuery("find m from Member m", Member.class).getResultList(); //Member 엔티티 조회 타입으로 조회, 리스트 형태로 반환
    }

    @Override
    public Member findMember(Long id) {
        return em.find(Member.class, id); //Member 엔티티 클래스에서 / 식별자 'id'로 해당 row 반환return null;
    }
}
