package za.ac.service.churchSiteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.domain.ChurchSite;
import za.ac.repository.IChurchSiteRepository;
import java.util.*;

@Service
public class ChurchSiteServiceImpl implements IChurchSite{

    public final IChurchSiteRepository repository;

    @Autowired
    public ChurchSiteServiceImpl(IChurchSiteRepository repository){ this.repository = repository;}

    @Override
    public ChurchSite create(ChurchSite churchSite) {
        return repository.save(churchSite);
    }

    @Override
    public ChurchSite read(String churchSiteId) {
        return repository.findById(churchSiteId).orElse(null);
    }

    @Override
    public ChurchSite update(ChurchSite churchSite) {
        return repository.save(churchSite);
    }

    @Override
    public boolean delete(String churchSiteId) {
        if(repository.existsById(churchSiteId)){
            repository.deleteById(churchSiteId);
            return true;
        }
        return false;
    }

    @Override
    public List<ChurchSite> getAll() {
        return repository.findAll();
    }

}
