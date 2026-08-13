package za.ac.service.leaderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.domain.Leader;
import za.ac.repository.ILeaderRepository;
import java.util.*;

@Service
public class LeaderServiceImpl implements ILeader{

    public final ILeaderRepository repository;

    @Autowired
    public LeaderServiceImpl(ILeaderRepository repository) {
        this.repository = repository;
    }

    @Override
    public Leader create(Leader leader) {
        return repository.save(leader);
    }

    @Override
    public Leader read(String leaderId) {
        return repository.findById(leaderId).orElse(null);
    }

    @Override
    public Leader update(Leader leader) {
        return repository.save(leader);
    }

    @Override
    public boolean delete(String leaderId) {
        if(repository.existsById(leaderId)){
            repository.deleteById(leaderId);
            return true;
        }
        return false;
    }

    @Override
    public List<Leader> getAll() {
        return repository.findAll();
    }

}
