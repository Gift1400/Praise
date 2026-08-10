package za.ac.service.rsvpService;

import za.ac.domain.Sermon;
import za.ac.service.sermonService.ISermon;
import java.util.List;
import java.util.Set;

public class RsvpServiceImpl implements ISermon {

    @Override
    public Sermon create(Sermon sermon) {
        return null;
    }

    @Override
    public Sermon read(String s) {
        return null;
    }

    @Override
    public Sermon update(Sermon sermon) {
        return null;
    }

    @Override
    public boolean delete(String s) {
        return false;
    }

    @Override
    public Set<Sermon> getAll() {
        return Set.of();
    }

    @Override
    public Set<Sermon> getSermonsByLeader(String leaderId) {
        return Set.of();
    }
}
