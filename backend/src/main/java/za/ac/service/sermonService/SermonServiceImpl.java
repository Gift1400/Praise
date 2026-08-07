package za.ac.service.sermonService;

import za.ac.domain.Sermon;

import java.util.List;
import java.util.Set;

public class SermonServiceImpl implements ISermon {

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
