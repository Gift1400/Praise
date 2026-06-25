package service.announcementsService.impl

import org.springframework.stereotype.Service;
import za.ac.cput.domain.Announcement;
import za.ac.cput.repository.impl.AnnouncementRepository;
import za.ac.cput.service.IAnnouncementService;

import java.util.Set;

@Service
public class AnnouncementService implements IAnnouncementService {

    private static AnnouncementService service = null;
    private final AnnouncementRepository repository;

    private AnnouncementService() {
        repository = AnnouncementRepository.getRepository();
    }

    public static AnnouncementService getService() {

        if (service == null) {
            service = new AnnouncementService();
        }

        return service;
    }

    @Override
    public Announcement create(Announcement announcement) {
        return repository.create(announcement);
    }

    @Override
    public Announcement read(String announcementId) {
        return repository.read(announcementId);
    }

    @Override
    public Announcement update(Announcement announcement) {
        return repository.update(announcement);
    }

    @Override
    public boolean delete(String announcementId) {
        return repository.delete(announcementId);
    }

    @Override
    public Set<Announcement> getAll() {
        return repository.getAll();
    }
}
}
