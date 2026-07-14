package ac.za.service.eventService;

import ac.za.domain.Event;
import ac.za.repository.IEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventServiceImpl implements IEventService{

    private IEventRepository repository;

    @Autowired
    public EventServiceImpl (IEventRepository repository){
        this.repository = repository;
    }

    @Override
    public Event create(Event event) {
        return this.repository.save(event);
    }

    @Override
    public Event read(String id) {
        return this.repository.findById(id).
    }

    @Override
    public Event update(Event event) {
        return null;
    }

    @Override
    public boolean delete(String s) {
        return false;
    }
}
