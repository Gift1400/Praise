package za.ac.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.ac.domain.Attendance;
import za.ac.service.attendanceService.AttendanceServiceImpl;
import java.util.*;

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

    @PutMapping("/update")
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
    public List<Attendance> getAll(){
        return attendanceService.getAll();
    }

    @GetMapping("/getByMember/{memberId}")
    public ResponseEntity<List<Attendance>> getByMemberId(@PathVariable String memberId){
        return ResponseEntity.ok(attendanceService.getByMemberId(memberId));
    }

    @GetMapping("/getByEvent/{eventId}")
    public ResponseEntity<List<Attendance>> getByEvent(@PathVariable String eventId) {
        return ResponseEntity.ok(attendanceService.getByEventId(eventId));
    }
}
