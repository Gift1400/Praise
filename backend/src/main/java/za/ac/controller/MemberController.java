package za.ac.controller;

import za.ac.domain.Member;
import za.ac.repository.IMemberRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/members")
public class MemberController {

    private final IMemberRepository memberRepository;

    //singleton
    public MemberController(IMemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @GetMapping
    public List<Member> getAl(){
        return memberRepository.findAll();
    }

    @GetMapping("/{id}")
    public Member getById(@PathVariable String id){
        return memberRepository.findById(id).orElse(null);
    }

    @PostMapping
    public Member create(@RequestBody Member member) {
        return memberRepository.save(member);
    }

    @PutMapping("/{id}")
    public Member update(@PathVariable String id, @RequestBody Member member){
        return memberRepository.save(member);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        memberRepository.deleteById(id);
    }

    
}
