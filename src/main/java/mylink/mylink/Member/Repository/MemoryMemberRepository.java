package mylink.mylink.Member.Repository;

import mylink.mylink.Member.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MemoryMemberRepository implements MemberRepository{
    Map<String, Member> store = new HashMap<>();
    private static long index = 0L;

    @Override
    public void save(Member member) {
        member.setIndex(++index);
        store.put(member.getAddress(), member);
    }
    @Override
    public Optional<Member> findByAddress(String address) {
        return Optional.ofNullable(store.get(address));
    }
    @Override
    public List<Member> findAll() {
        ArrayList<Member> members = new ArrayList<>(store.values());
        return members;
    }
}
