package za.ac.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.ac.domain.Leader;
import za.ac.service.leaderService.LeaderServiceImpl;
import java.util.*;

@RestController
@RequestMapping("/leader")
public class LeaderController {
    private final LeaderServiceImpl leaderService;

    @Autowired
    public LeaderController(LeaderServiceImpl leaderService){
        this.leaderService = leaderService;
    }

    @PostMapping("/create")
    public Leader create(@RequestBody Leader leader){
        return leaderService.create(leader);
    }

    @GetMapping("/read/{leaderId}")
    public Leader read(@PathVariable String leaderId){
        return leaderService.read(leaderId);
    }

    @PutMapping("/update")
    public Leader update(@RequestBody Leader leader){
        return leaderService.update(leader);
    }

    @DeleteMapping("/delete/{leaderId}")
    public boolean delete(@PathVariable String leaderId){
        if(leaderService.delete(leaderId)){
            return true;
        }
        return false;
    }

    @GetMapping("/getAll")
    public List<Leader> getAll(){
        return leaderService.getAll();
    }
}
