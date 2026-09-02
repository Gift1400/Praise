package za.ac.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.ac.domain.OutreachProgram;
import za.ac.service.outreachProgramService.OutreachProgramServiceImpl;
import za.ac.domain.Member;

import java.util.List;

@RestController
@RequestMapping("/outreachProgram")
public class OutreachProgramController {
    private final OutreachProgramServiceImpl outreachProgramService;

    @Autowired
    public OutreachProgramController(OutreachProgramServiceImpl outreachProgramService){
        this.outreachProgramService = outreachProgramService;
    }

    @PostMapping("/create")
    public OutreachProgram create(@RequestBody OutreachProgram outreachProgram){
        return outreachProgramService.create(outreachProgram);
    }

    @GetMapping("/read/{outreachProgramId}")
    public OutreachProgram read(@PathVariable String outreachProgramId){
        return outreachProgramService.read(outreachProgramId);
    }

    @PutMapping("/update")
    public OutreachProgram update(@RequestBody OutreachProgram outreachProgram){
        return outreachProgramService.update(outreachProgram);
    }

    @DeleteMapping("/delete/{outreachProgramId}")
    public boolean delete(@PathVariable String outreachProgramId){
        if(outreachProgramService.delete(outreachProgramId)){
            return true;
        }
        return false;
    }

    @GetMapping("/getAll")
    public List<OutreachProgram> getAll(){
        return outreachProgramService.getAll();
    }

    @GetMapping("/getByMember/{memberId}")
    public List<OutreachProgram> getByMemberId(@PathVariable String memberId){
        return outreachProgramService.getProgramsByMember(memberId);
    }

    @GetMapping("/getByLeader/{leaderId}")
    public List<OutreachProgram> getByLeaderId(@PathVariable String leaderId){
        return outreachProgramService.getProgramsByLeader(leaderId);
    }
}
