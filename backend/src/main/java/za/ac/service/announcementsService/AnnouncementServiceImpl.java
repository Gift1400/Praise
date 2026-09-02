package za.ac.service.announcementsService;

import org.springframework.beans.factory.annotation.Autowired;
import za.ac.domain.Announcement;
import org.springframework.stereotype.Service;
import za.ac.domain.Enums.AnnouncementType;
import za.ac.repository.IAnnouncementRepository;
import java.util.*;

@Service
public class AnnouncementServiceImpl implements IAnnouncementService {

    public final IAnnouncementRepository repository;

    @Autowired
    public AnnouncementServiceImpl(IAnnouncementRepository repository) {
        this.repository = repository;
    }


    @Override
    public Announcement create(Announcement announcement) {
        return repository.save(announcement);
    }

    @Override
    public Announcement read(String announcementId) {
        return repository.findById(announcementId).orElse(null);
    }

    @Override
    public Announcement update(Announcement announcement) {
        return repository.save(announcement);
    }

    @Override
    public boolean delete(String announcementId) {
        if(repository.existsById(announcementId)){
            repository.deleteById(announcementId);
            return true;
        }
        return false;
    }
    @Override
    public List<Announcement> getAll() {
        return repository.findAll();
    }

    @Override
    public List<Announcement> getByType(AnnouncementType announcementType) {
        if(announcementType == null){
            return Collections.emptyList();
        }
        return repository.getByType(announcementType);
    }

    @Override
    public List<Announcement> getByChurchSite(String churchSiteId) {
        if(churchSiteId == null){
            return Collections.emptyList();
        }
        return repository.getByChurchSite(churchSiteId);
    }

}
