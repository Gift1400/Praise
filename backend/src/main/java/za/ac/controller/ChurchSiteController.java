package za.ac.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.ac.domain.ChurchSite;
import za.ac.service.churchSiteService.ChurchSiteServiceImpl;

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
}
