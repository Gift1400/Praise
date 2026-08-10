package za.ac.service.outreachProgramService;

import za.ac.domain.OutreachProgram;

import java.util.Set;

public class OutreachProgramServiceImpl implements IOutreachProgram {

    @Override
    public OutreachProgram create(OutreachProgram outreachProgram) {
        return null;
    }

    @Override
    public OutreachProgram read(String s) {
        return null;
    }

    @Override
    public OutreachProgram update(OutreachProgram outreachProgram) {
        return null;
    }

    @Override
    public boolean delete(String s) {
        return false;
    }

    @Override
    public Set<OutreachProgram> getAll() {
        return Set.of();
    }

    @Override
    public Set<OutreachProgram> getProgramsByMember(String memberId) {
        return Set.of();
    }

    @Override
    public Set<OutreachProgram> getProgramsByLeader(String leaderId) {
        return Set.of();
    }
}
