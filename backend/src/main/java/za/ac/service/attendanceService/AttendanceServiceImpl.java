package za.ac.service.attendanceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.domain.Attendance;
import za.ac.repository.IAttendanceRepository;
import java.util.*;

@Service
public class AttendanceServiceImpl implements IAttendance{
    public final IAttendanceRepository repository;

    @Autowired
    public AttendanceServiceImpl(IAttendanceRepository repository){
        this.repository = repository;
    }

    @Override
    public Attendance create(Attendance attendance) {
        return repository.save(attendance);
    }

    @Override
    public Attendance read(String attendanceId) {
        return repository.findById(attendanceId).orElse(null);
    }

    @Override
    public Attendance update(Attendance attendance) {
        return repository.save(attendance);
    }

    @Override
    public boolean delete(String attendanceId) {
        if(repository.existsById(attendanceId)){
            repository.deleteById(attendanceId);
            return true;
        }
        return false;
    }

    @Override
    public Set<Attendance> getAll() {
        return Set.of();
    }

    @Override
    public Set<Attendance> getByMember(String memberId) {
        return Set.of();
    }

}
