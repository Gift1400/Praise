package za.ac.factory;

import za.ac.domain.Attendance;
import za.ac.domain.Member;
import za.ac.util.Helper;

import java.time.LocalDate;

public class AttendanceFactory {
    public static Attendance createAttendance(String attendanceId, LocalDate date,  Member member){

        if(Helper.isNullOrEmpty(attendanceId)){
            return null;
        }

        return new Attendance.Builder()
                .setAttendanceId(attendanceId)
                .setDate(date)
                .setMember(member)
                .build();
    }
}
