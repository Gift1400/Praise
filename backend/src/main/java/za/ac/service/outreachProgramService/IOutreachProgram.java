package za.ac.service.outreachProgramService;

import za.ac.domain.OutreachProgram;
import za.ac.service.IService;
import java.util.*;

public interface IOutreachProgram extends IService <OutreachProgram, String>{
    Set<OutreachProgram> getAll();
    Set<OutreachProgram> getProgramsByMember(String memberId);
    Set<OutreachProgram> getProgramsByLeader(String leaderId);
}
