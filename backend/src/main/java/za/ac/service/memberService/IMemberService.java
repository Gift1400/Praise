package za.ac.service.memberService;

import za.ac.domain.Member;
import za.ac.service.IService;

import java.util.List;

public interface IMemberService extends IService<Member, String> {

    List<Member> getAllMembers();
}
