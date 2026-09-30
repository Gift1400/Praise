package za.ac.service.outreachProgramService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.domain.OutreachProgram;
import za.ac.repository.IOutreachProgramRepository;

import java.util.*;

@Service
public class OutreachProgramServiceImpl implements IOutreachProgram {
    private final IOutreachProgramRepository repository;

    @Autowired
    public OutreachProgramServiceImpl(IOutreachProgramRepository repository) {
        this.repository = repository;
    }

    @Override
    public OutreachProgram create(OutreachProgram outreachProgram) {
        return repository.save(outreachProgram);
    }

    @Override
    public OutreachProgram read(String programId) {
        return repository.findById(programId).orElse(null);
    }

    @Override
    public OutreachProgram update(OutreachProgram outreachProgram) {
        return repository.save(outreachProgram);
    }

    @Override
    public boolean delete(String programId) {
        if (repository.existsById(programId)) {
            repository.deleteById(programId);
            return true;
        }
        return false;
    }

    @Override
    public List<OutreachProgram> getAll() {
        return repository.findAll();
    }

    @Override
    public List<OutreachProgram> getProgramsByMember(String memberId) {
        if(memberId == null){
            return Collections.emptyList();
        }
        return repository.getProgramsByMember(memberId);
    }

    @Override
    public List<OutreachProgram> getProgramsByLeader(String leaderId) {
        if(leaderId == null){
            return Collections.emptyList();
        }
        return repository.getProgramsByLeader(leaderId);
    }
}
