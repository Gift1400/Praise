package za.ac.service.announcementsService;

import org.springframework.beans.factory.annotation.Autowired;
import za.ac.domain.Announcement;
import org.springframework.stereotype.Service;
import za.ac.repository.IAnnouncementRepository;

import java.util.Set;

@Service
public class AnnouncementServiceImpl implements IAnnouncementService {

    public final IAnnouncementRepository repository;

    @Autowired
    public AnnouncementServiceImpl(IAnnouncementRepository repository) {
        this.repository = repository;
    }

    @Override
    public Set<Announcement> getAll() {
        return Set.of();
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
        };
        return false;
    }
}
