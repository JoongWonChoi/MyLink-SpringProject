package mylink.mylink.repository.memberRepository;

import mylink.mylink.domain.Member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@Repository
public class MemoryMemberRepository implements MemberRepository{
    Map<String, Member> store = new HashMap<>();
    private static long index = 0L;

    @Override
    public Long save(Member member) {
        //member.setIndex(++index); //실무에서 사용하는 식으로 setter는 최대한 사용하지 않겠다.
        store.put(member.getAddress(), member);
        return member.getId();
    }

    @Override
    public List<Member> findByAddress(String address) {
        return null;
    }

    /*@Override
    public Optional<Member> findByAddress(String address) {
        return Optional.ofNullable(store.get(address));
    }*/
    @Override
    public List<Member> findAll() {
        ArrayList<Member> members = new ArrayList<>(store.values());
        return members;
    }

    @Override
    public Member findMember(Long id) {
        return null;
    }
}
