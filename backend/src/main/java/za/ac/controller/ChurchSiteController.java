package za.ac.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.ac.domain.ChurchSite;
import za.ac.service.churchSiteService.ChurchSiteServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/churchSite")
public class ChurchSiteController {

    private final ChurchSiteServiceImpl churchSiteService;

    @Autowired
    public ChurchSiteController(ChurchSiteServiceImpl churchSiteService) {
        this.churchSiteService = churchSiteService;
    }

    @PostMapping("/create")
    public ChurchSite create(@RequestBody ChurchSite churchSite){
        return churchSiteService.create(churchSite);
    }

    @GetMapping("/read/{churchSiteId}")
    public ChurchSite read(@PathVariable String churchSiteId){
        return churchSiteService.read(churchSiteId);
    }

    @PutMapping("/update")
    public ChurchSite update(@RequestBody ChurchSite churchSite){
        return churchSiteService.update(churchSite);
    }

    @DeleteMapping("/delete/{churchSiteId}")
    public boolean delete(@PathVariable String churchSiteId){
        if(churchSiteService.delete(churchSiteId)){
            return true;
        }
        return false;
    }

    @GetMapping("/getAll")
    public List<ChurchSite> getAll(){
        return churchSiteService.getAll();
    }
}
