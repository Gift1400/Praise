package za.ac.service.eventService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.domain.Event;
import za.ac.repository.IEventRepository;

@Service
public class EventServiceImpl implements IEventService {

    public final IEventRepository eventRepository;

    @Autowired
    public EventServiceImpl(IEventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public Event create(Event event) {
        return eventRepository.save(event);

    }

    @Override
    public Event read(String eventId) {
        return eventRepository.findById(eventId).orElse(null);
    }

    @Override
    public Event update(Event event) {
        return eventRepository.save(event);
    }

    @Override
    public boolean delete(String eventId) {
        if(eventRepository.existsById(eventId)){
            eventRepository.deleteById(eventId);
            return true;
        }
        return false;
    }
}
