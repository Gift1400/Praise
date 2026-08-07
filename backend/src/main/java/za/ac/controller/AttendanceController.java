package za.ac.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.support.SimpleTriggerContext;
import org.springframework.web.bind.annotation.*;
import za.ac.domain.Attendance;
import za.ac.service.attendanceService.AttendanceServiceImpl;
import za.ac.service.attendanceService.IAttendance;

import java.util.Set;

@RestController
@RequestMapping("api/attendance")
public class AttendanceController {

    public final AttendanceServiceImpl attendanceService;

    @Autowired
    public AttendanceController(AttendanceServiceImpl attendanceService){
        this.attendanceService = attendanceService;
    }

    @PostMapping("/create")
    public Attendance create(@RequestBody Attendance attendance){
        return attendanceService.create(attendance);
    }

    @GetMapping("/read/{attendanceId}")
    public Attendance read(@PathVariable String attendanceId){
        return attendanceService.read(attendanceId);
    }

    @PostMapping("/update")
    public Attendance update(@RequestBody Attendance attendance){
        return attendanceService.update(attendance);
    }

    @DeleteMapping("/delete/{attendanceId}")
    public boolean delete(@PathVariable String attendanceId){
        if(attendanceService.delete(attendanceId)){
            return true;
        }
        return false;
    }

    @GetMapping("/getAll")
    public Set<Attendance> getAll(){
        return attendanceService.getAll();
    }

    @GetMapping("/getByMember")
    public Set<Attendance> getByMemberId(@PathVariable String memberId){
        return attendanceService.getByMemberId(memberId);
    }
}
