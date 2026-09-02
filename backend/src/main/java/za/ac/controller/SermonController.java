package za.ac.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.ac.domain.Sermon;
import za.ac.service.sermonService.SermonServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/sermon")
public class SermonController {
    private final SermonServiceImpl sermonService;

    @Autowired
    public SermonController(SermonServiceImpl sermonService){
        this.sermonService = sermonService;
    }

    @PostMapping("/create")
    public Sermon create(@RequestBody Sermon sermon){
        return sermonService.create(sermon);
    }

    @GetMapping("/read/{sermonId}")
    public Sermon read(@PathVariable String sermonId){
        return sermonService.read(sermonId);
    }

    @PutMapping("/update")
    public Sermon update(@RequestBody Sermon sermon){
        return sermonService.update(sermon);
    }

    @DeleteMapping("/delete/{sermonId}")
    public boolean delete(@PathVariable String sermonId){
        if(sermonService.delete(sermonId)){
            return true;
        }
        return false;
    }

    @GetMapping("/getAll")
    public List<Sermon> getAll(){
        return sermonService.getAll();
    }
}
