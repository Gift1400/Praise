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
        return null;
    }

    @Override
    public Testimony read(String s) {
        return null;
    }

    @Override
    public Testimony update(Testimony testimony) {
        return null;
    }

    @Override
    public boolean delete(String s) {
        return false;
    }


    @Override
    public Set<Testimony> getAll() {
        return Set.of();
    }
}
