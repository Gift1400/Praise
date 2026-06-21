package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.Announcement;
import za.ac.cput.service.IAnnouncementService;

import java.util.Set;

@RestController
@RequestMapping("/church-announcements")
@CrossOrigin(origins = "*")

public class AnnouncementController {

        private final IAnnouncementService announcementService;

        @Autowired
        public AnnouncementController(IAnnouncementService announcementService) {
            this.announcementService = announcementService;
        }

        @PostMapping("/create")
        public ResponseEntity<Announcement> create(@RequestBody Announcement announcement) {

            Announcement createdAnnouncement =
                    announcementService.create(announcement);

            return ResponseEntity.ok(createdAnnouncement);
        }

        @GetMapping("/read/{announcementId}")
        public ResponseEntity<Announcement> read(
                @PathVariable String announcementId) {

            Announcement announcement =
                    announcementService.read(announcementId);

            if (announcement == null) {
                return ResponseEntity.notFound().build();
            }

            return ResponseEntity.ok(announcement);
        }

        @PutMapping("/update")
        public ResponseEntity<Announcement> update(
                @RequestBody Announcement announcement) {

            Announcement updatedAnnouncement =
                    announcementService.update(announcement);

            if (updatedAnnouncement == null) {
                return ResponseEntity.notFound().build();
            }

            return ResponseEntity.ok(updatedAnnouncement);
        }

        @DeleteMapping("/delete/{announcementId}")
        public ResponseEntity<String> delete(
                @PathVariable String announcementId) {

            boolean deleted =
                    announcementService.delete(announcementId);

            if (deleted) {
                return ResponseEntity.ok("Announcement deleted successfully");
            }

            return ResponseEntity.badRequest()
                    .body("Announcement not found");
        }

        @GetMapping("/getAll")
        public ResponseEntity<Set<Announcement>> getAll() {

            Set<Announcement> announcements =
                    announcementService.getAll();

            return ResponseEntity.ok(announcements);
        }
    }
}

