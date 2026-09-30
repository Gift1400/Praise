package za.ac.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.ac.domain.Member;
import za.ac.service.memberService.MemberServiceImpl;

import java.util.List;

@RestController
@RequestMapping("api/member")
public class MemberController {

    private final MemberServiceImpl memberService;

    @Autowired
    public MemberController(MemberServiceImpl memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/create")
    public Member create(@RequestBody Member student) {
        return memberService.create(student);
    }

    @GetMapping("/read/{memberId}")
    public Member read(@PathVariable String memberId) {
        return memberService.read(memberId);
    }

    @PutMapping("/update")
    public Member update(@RequestBody Member member) {
        return memberService.update(member);
    }

    @DeleteMapping("/delete/{memberId}")
    public boolean delete(@PathVariable String memberId) {
        if (memberService.delete(memberId)) {
            return true;
        }
        return false;
    }

    @GetMapping("/getAll")
    public List<Member> getAll() {
        return memberService.getAll();
    }

}
