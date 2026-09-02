package za.ac.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.ac.domain.Rsvp;
import za.ac.service.rsvpService.RsvpServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/rsvp")
public class RsvpController {
    private final RsvpServiceImpl rsvpService;

    @Autowired
    public RsvpController(RsvpServiceImpl rsvpService){
        this.rsvpService = rsvpService;
    }

    @PostMapping("/create")
    public Rsvp create(@RequestBody Rsvp rsvp){
        return rsvpService.create(rsvp);
    }

    @GetMapping("/read/{rsvpId}")
    public Rsvp read(@PathVariable String rsvpId){
        return rsvpService.read(rsvpId);
    }

    @PutMapping("/update")
    public Rsvp update(@RequestBody Rsvp rsvp){
        return rsvpService.update(rsvp);
    }

    @DeleteMapping("/delete/{rsvpId}")
    public boolean delete(@PathVariable String rsvpId){
        if(rsvpService.delete(rsvpId)){
            return true;
        }
        return false;
    }

    @GetMapping("/getAll")
    public List<Rsvp> getAll(){
        return rsvpService.getAll();
    }
}
