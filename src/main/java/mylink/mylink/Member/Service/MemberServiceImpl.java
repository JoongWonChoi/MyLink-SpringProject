package mylink.mylink.Member.Service;

import mylink.mylink.Member.Repository.MemberRepository;
import mylink.mylink.Member.domain.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class MemberServiceImpl implements MemberService{
    private final MemberRepository memberRepository;

    @Autowired
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
    @Override
    public void join(Member member) {
        boolean check = checkDuplicateAddress(member);
        if(check==true){memberRepository.save(member);}
        else{
            System.out.println("already exists . .");}

    }

    private boolean checkDuplicateAddress(Member member) {
        Optional<Member> byAddress = memberRepository.findByAddress(member.getAddress());
        if (byAddress.isEmpty()) {
            return true;
        }
        else return false;
    }

    @Override
    public Optional<Member> findMember(String address) {
        return Optional.empty();
    }
    @Override
    public void deleteMember(String address) {

    }
    @Override
    public void updateMember(String address, Member updatedMember) {

    }
    @Override
    public Optional<Member> login(String address, String password) {
        return Optional.empty();
    }

    @Override
    public void logout(String address) {

    }
}
