package mylink.mylink.service.memberService;

import mylink.mylink.domain.Board;
import mylink.mylink.domain.Member;
import mylink.mylink.repository.memberRepository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
//@Service
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

    @Override
    public void updateMember(Long id, String name, int age, String sex, String department, String address, String password) {

    }

    /* @Override
     public Optional<Member> findMember(String address) {
         Optional<Member> byAddress = memberRepository.findByAddress(address);
         return byAddress;
     }
     */

    @Override
    public Member login(String address, String password) {
        return null;
    }

    @Override
    public void logout(String address) {

    }

    @Override
    public List<Member> findMembers() {
        return null;
    }

    @Override
    public List<Board> viewMemberBoards(Long id) {
        return null;
    }
}
