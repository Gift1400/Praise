package ac.za.service.memberService;

import ac.za.domain.Member;
import ac.za.repository.IMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements IMemberService {

    private final IMemberRepository repository;

    @Autowired
    public MemberServiceImpl(IMemberRepository repository) {
        this.repository = repository;
    }


    @Override
    public Member create(Member member) {
        return this.repository.save(member);
    }

    @Override
    public Member read(String s) {
        return this.repository.findById(s).orElse(null);
    }

    @Override
    public Member update(Member member) {
        return null;
    }

    @Override
    public boolean delete(String s) {
        return false;
    }
}
