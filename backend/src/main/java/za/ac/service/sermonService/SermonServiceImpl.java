package za.ac.service.sermonService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.domain.Sermon;
import za.ac.repository.ISermonRepository;

import java.util.*;

@Service
public class SermonServiceImpl implements ISermon {
    private final ISermonRepository repository;

    @Autowired
    public SermonServiceImpl(ISermonRepository repository) {
        this.repository = repository;
    }

    @Override
    public Sermon create(Sermon sermon) {
        return repository.save(sermon);
    }

    @Override
    public Sermon read(String sermonId) {
        return repository.findById(sermonId).orElse(null);
    }

    @Override
    public Sermon update(Sermon sermon) {
        return repository.save(sermon);
    }

    @Override
    public boolean delete(String sermonId) {
        if(repository.existsById(sermonId)){
            repository.deleteById(sermonId);
            return true;
        }
        return false;
    }

    @Override
    public List<Sermon> getAll() {
        return repository.findAll();
    }

    @Override
    public List<Sermon> getSermonsByLeader(String leaderId) {
        if(leaderId == null){
            return Collections.emptyList();
        }
        return repository.getSermonsByLeader(leaderId);
    }
}
