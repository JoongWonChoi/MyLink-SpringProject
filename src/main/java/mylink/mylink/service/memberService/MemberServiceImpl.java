package mylink.mylink.service.memberService;

import mylink.mylink.repository.memberRepository.MemberRepository;
import mylink.mylink.domain.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
@Service
@Transactional
public class MemberServiceImpl implements MemberService{
    private final MemberRepository memberRepository;

    @Autowired
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
   /* @Override
    *//*public boolean join(Member member) {
        boolean check = checkDuplicateAddress(member);
        if(check==true){
            memberRepository.save(member);
            return true;}
        else{
            System.out.println("already exists . .");
            return false;}
    }*/

   /* private boolean checkDuplicateAddress(Member member) {
        Optional<Member> byAddress = memberRepository.findByAddress(member.getAddress());
        if (byAddress.isEmpty()) {return true;}
        else return false;
    }*/

    @Override
    public Long join(Member member) {
        return null;
    }

    @Override
    public Member findMember(Long id) {
        return null;
    }

    @Override
    public void deleteMember(Long id) {

    }

    /* @Override
     public Optional<Member> findMember(String address) {
         Optional<Member> byAddress = memberRepository.findByAddress(address);
         return byAddress;
     }
     */
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

    @Override
    public List<Member> findMembers() {
        return null;
    }
}
