package za.ac.service.rsvpService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.domain.Rsvp;
import za.ac.domain.Sermon;
import za.ac.repository.IRsvpRepository;
import za.ac.service.sermonService.ISermon;

import java.util.Collections;
import java.util.List;
import java.util.Set;

@Service
public class RsvpServiceImpl implements IRsvp {
    private final IRsvpRepository repository;

    @Autowired
    public RsvpServiceImpl(IRsvpRepository repository) {
        this.repository = repository;
    }

    @Override
    public Rsvp create(Rsvp rsvp) {
        return repository.save(rsvp);
    }

    @Override
    public Rsvp read(String rsvpId) {
        return repository.findById(rsvpId).orElse(null);
    }

    @Override
    public Rsvp update(Rsvp rsvp) {
        return repository.save(rsvp);
    }

    @Override
    public boolean delete(String rsvpId) {
        if(repository.existsById(rsvpId)){
            repository.deleteById(rsvpId);
            return true;
        }
        return false;
    }

    @Override
    public List<Rsvp> getAll() {
        return repository.findAll();
    }

    @Override
    public List<Rsvp> getRsvpByEvent(String eventId) {
        if(eventId == null){
            return Collections.emptyList();
        }
        return repository.getRsvpByEvent(eventId);
    }

    @Override
    public List<Rsvp> getRsvpByMember(String memberId) {
        if(memberId == null){
            return Collections.emptyList();
        }
        return repository.getRsvpByMember(memberId);
    }
}
