package za.ac.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.ac.domain.Announcement;
import za.ac.service.announcementsService.AnnouncementServiceImpl;
import java.util.*;

@RestController
@RequestMapping("api/announcement")
public class AnnouncementController {

    public final AnnouncementServiceImpl announcementService;

    @Autowired
    public AnnouncementController(AnnouncementServiceImpl announcementService) {
        this.announcementService = announcementService;
    }

    @PostMapping("/create")
    public Announcement create(@RequestBody Announcement announcement) {
        return announcementService.create(announcement);
    }

    @GetMapping("/read/{announcementId}")
    public Announcement read(@PathVariable String announcementId) {
        return announcementService.read(announcementId);
    }

    @PutMapping("/update")
    public Announcement update(@RequestBody Announcement announcement) {
        return announcementService.update(announcement);
    }

    @DeleteMapping("/delete/{announcementId}")
    public boolean delete(@PathVariable String announcementId) {
        if(announcementService.delete(announcementId)) {
            return true;
        }
        return false;
    }

    @GetMapping("/getAll")
    public List<Announcement> getAll() {
        return announcementService.getAll();
    }

}

