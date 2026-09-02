package za.ac.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.ac.domain.PrayerRequest;
import za.ac.service.prayerRequestService.PrayerRequestServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/prayerRequest")
public class PrayerRequestController {
    private final PrayerRequestServiceImpl prayerRequestService;

    @Autowired
    public PrayerRequestController(PrayerRequestServiceImpl prayerRequestService){
        this.prayerRequestService = prayerRequestService;
    }

    @PostMapping("/create")
    public PrayerRequest create(@RequestBody PrayerRequest prayerRequest){
        return prayerRequestService.create(prayerRequest);
    }

    @GetMapping("/read/{prayerRequestId}")
    public PrayerRequest read(@PathVariable String prayerRequestId){
        return prayerRequestService.read(prayerRequestId);
    }

    @PutMapping("/update")
    public PrayerRequest update(@RequestBody PrayerRequest prayerRequest){
        return prayerRequestService.update(prayerRequest);
    }

    @DeleteMapping("/delete/{prayerRequestId}")
    public boolean delete(@PathVariable String prayerRequestId){
        if(prayerRequestService.delete(prayerRequestId)){
            return true;
        }
        return false;
    }

    @GetMapping("/getAll")
    public List<PrayerRequest> getAll(){
        return prayerRequestService.getAll();
    }
}
