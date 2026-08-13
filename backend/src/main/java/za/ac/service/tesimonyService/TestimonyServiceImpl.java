package za.ac.service.tesimonyService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.domain.Testimony;
import za.ac.repository.ITestimonyRepository;

import java.util.*;

@Service
public class TestimonyServiceImpl implements ITestimony{

    public final ITestimonyRepository repository;

    @Autowired
    public TestimonyServiceImpl(ITestimonyRepository repository){ this.repository = repository;}

    @Override
    public Testimony create(Testimony testimony) {
        return repository.save(testimony);
    }

    @Override
    public Testimony read(String testimonyId) {
        return repository.findById(testimonyId).orElse(null);
    }

    @Override
    public Testimony update(Testimony testimony) {
        return repository.save(testimony);
    }

    @Override
    public boolean delete(String testimonyId) {
       if(repository.existsById(testimonyId)){
           repository.deleteById(testimonyId);
           return true;
       }
       return false;
    }


    @Override
    public List<Testimony> getAll() {
        return repository.findAll();
    }
}
