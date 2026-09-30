package za.ac.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.ac.domain.Event;
import za.ac.service.eventService.EventServiceImpl;

import java.util.List;

@RestController
@RequestMapping("api/events")
public class EventController {

    private final EventServiceImpl eventService;

    @Autowired
    public EventController(EventServiceImpl eventService) {
        this.eventService = eventService;
    }

    @PostMapping("/create")
    public Event create(@RequestBody Event event) {
        return eventService.create(event);
    }

    @GetMapping("/read/{eventId}")
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

    @GetMapping("/getAll")
    public ResponseEntity<List<Event>> getAll() {
        return ResponseEntity.ok(eventService.getAll());
    }

    @GetMapping("/upcoming")
    public ResponseEntity<List<Event>> getUpcoming() {
        return ResponseEntity.ok(eventService.getUpcomingEvents());
    }

    @GetMapping("/getByChurchSite/{churchSiteId}")
    public ResponseEntity<List<Event>> getByChurchSite(@PathVariable String churchSiteId) {
        return ResponseEntity.ok(eventService.getEventByChurchSite(churchSiteId));
    }
}
