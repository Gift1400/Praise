package za.ac.domain;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Attendance {
    @Id
    @GeneratedValue
    private String attendanceId;

    private LocalDate date;
    @ManyToOne
    private Member member;

    protected Attendance(){};

    public Attendance(Builder builder){
        this.attendanceId = builder.attendanceId;
        this.date = builder.date;
        this.member = builder.member;
    }

    public String getAttendanceId(){ return attendanceId;}
    public LocalDate getDate(){ return date;}
    public Member getMember() { return member;}

    public String toString(){
        return "Attendance{ " + "\n" +
                "Attendance Id: " + attendanceId + "\n" +
                "Date: " + date + "\n" +
                "Member: " + member + "}";
    }

    public static class Builder{
        private String attendanceId;
        private LocalDate date;
        private Member member;

        public Builder copy(Attendance attendance){
            this.attendanceId = attendance.attendanceId;
            this.date = attendance.date;
            this.member = attendance.member;
            return this;
        }

        public Builder setAttendanceId(String attendanceId){
            this.attendanceId = attendanceId;
            return this;
        }
        public Builder setDate(LocalDate date){
            this.date = date;
            return this;
        }
        public Builder setMember(Member member){
            this.member = member;
            return this;
        }

        public Attendance build(){
            return new Attendance(this);
        }
    }

}
