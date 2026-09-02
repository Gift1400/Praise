package za.ac.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.ac.domain.Testimony;
import za.ac.service.tesimonyService.TestimonyServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/testimony")
public class TestimonyController {
    private final TestimonyServiceImpl testimonyService;

    @Autowired
    public TestimonyController(TestimonyServiceImpl testimonyService){
        this.testimonyService = testimonyService;
    }

    @PostMapping("/create")
    public Testimony create(@RequestBody Testimony testimony){
        return testimonyService.create(testimony);
    }

    @GetMapping("/read/{testimonyId}")
    public Testimony read(@PathVariable String testimonyId){
        return testimonyService.read(testimonyId);
    }

    @PutMapping("/update")
    public Testimony update(@RequestBody Testimony testimony){
        return testimonyService.update(testimony);
    }

    @DeleteMapping("/delete/{testimonyId}")
    public boolean delete(@PathVariable String testimonyId){
        if(testimonyService.delete(testimonyId)){
            return true;
        }
        return false;
    }

    @GetMapping("/getAll")
    public List<Testimony> getAll(){
        return testimonyService.getAll();
    }
}
