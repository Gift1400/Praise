package za.ac.service.announcementsService;

import org.springframework.stereotype.Service;
import za.ac.domain.Announcement;

import java.util.Set;

@Service
public class AnnouncementServiceImpl implements IAnnouncementService{

    @Override
    public Set<Announcement> getAll() {
        return Set.of();
    }

    @Override
    public Announcement create(Announcement announcement) {
        return null;
    }

    @Override
    public Announcement read(String s) {
        return null;
    }

    @Override
    public Announcement update(Announcement announcement) {
        return null;
    }

    @Override
    public boolean delete(String s) {
        return false;
    }


}
