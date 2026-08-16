package za.ac.service.memberService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.domain.Member;
import za.ac.repository.IMemberRepository;

import java.util.List;

@Service
public class MemberServiceImpl implements IMemberService {

    private final IMemberRepository memberRepository;

    @Autowired
    public MemberServiceImpl(IMemberRepository repository) {
        this.memberRepository = repository;
    }

    @Override
    public Member create(Member member) {
        return memberRepository.save(member);
    }

    @Override
    public Member read(String memberId) {
        return memberRepository.findById(memberId).orElse(null);
    }

    @Override
    public Member update(Member member) {
        return memberRepository.save(member);
    }

    @Override
    public boolean delete(String memberId) {
        if(memberRepository.existsById(memberId)){
            memberRepository.deleteById(memberId);
            return true;
        };
        return false;
    }

    @Override
    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }
}
