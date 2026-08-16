package za.ac.service.outreachProgramService;

import za.ac.domain.OutreachProgram;
import za.ac.service.IService;
import java.util.*;

public interface IOutreachProgram extends IService <OutreachProgram, String>{
    List<OutreachProgram> getAll();
    List<OutreachProgram> getProgramsByMember(String memberId);
    List<OutreachProgram> getProgramsByLeader(String leaderId);
}
