package za.ac.service.leaderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.domain.Leader;
import za.ac.repository.ILeaderRepository;
import java.util.List;

@Service
public class LeaderServiceImpl implements ILeader{

    public final ILeaderRepository repository;

    @Autowired
    public LeaderServiceImpl(ILeaderRepository repository) {
        this.repository = repository;
    }

    @Override
    public Leader create(Leader leader) {
        return null;
    }

    @Override
    public Leader read(String s) {
        return null;
    }

    @Override
    public Leader update(Leader leader) {
        return null;
    }

    @Override
    public boolean delete(String s) {
        return false;
    }

    @Override
    public List<String> getAll() {
        return List.of();
    }

}
