package za.ac.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.ac.domain.Event;
import za.ac.service.eventService.EventServiceImpl;

@RestController
@RequestMapping("api/event")
public class EventController {

    @Autowired
    private EventServiceImpl eventService;

    @PostMapping("/create")
    public Event create(@RequestBody Event event) {
        return eventService.create(event);
    }

    @GetMapping("/read/{evendId}")
    public Event read(@PathVariable String eventId){
        return eventService.read(eventId);
    }

    @PutMapping("/update")
    public Event update(@RequestBody Event event){
        return eventService.update(event);
    }

    @DeleteMapping("/delete/{eventId}")
    public boolean delete(@PathVariable String eventId){
        return eventService.delete(eventId);
    }
}
