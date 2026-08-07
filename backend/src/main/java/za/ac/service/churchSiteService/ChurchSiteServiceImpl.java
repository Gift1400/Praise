package za.ac.service.churchSiteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.domain.ChurchSite;
import za.ac.repository.IChurchSiteRepository;
import java.util.List;

@Service
public class ChurchSiteServiceImpl implements IChurchSite{

    public final IChurchSiteRepository repository;

    @Autowired
    public ChurchSiteServiceImpl(IChurchSiteRepository repository){ this.repository = repository;}
    @Override
    public ChurchSite create(ChurchSite churchSite) {
        return null;
    }

    @Override
    public ChurchSite read(String s) {
        return null;
    }

    @Override
    public ChurchSite update(ChurchSite churchSite) {
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
