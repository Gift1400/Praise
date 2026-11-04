package za.ac.domain;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "attendance")
public class Attendance {
    @Id
    @Column(name = "attendance_id")
    private String attendanceId;

    private LocalDate date;
    @ManyToOne (fetch = FetchType.EAGER)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

    protected Attendance(){};

    public Attendance(Builder builder){
        this.attendanceId = builder.attendanceId;
        this.date = builder.date;
        this.member = builder.member;
        this.event = builder.event;
    }

    public String getAttendanceId(){ return attendanceId;}
    public LocalDate getDate(){ return date;}
    public Member getMember() { return member;}
    public Event getEvent(){ return event;}

    public String toString(){
        return "Attendance{ " + "\n" +
                "Attendance Id: " + attendanceId + "\n" +
                "Date: " + date + "\n" +
                "Member: " + member + "\n"+
                "Event: " + event + "}";
    }

    public static class Builder{
        private String attendanceId;
        private LocalDate date;
        private Member member;
        private Event event;

        public Builder copy(Attendance attendance){
            this.attendanceId = attendance.attendanceId;
            this.date = attendance.date;
            this.member = attendance.member;
            this.event = attendance.event;
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

        public Builder setEvent(Event event){
            this.event = event;
            return this;
        }

        public Attendance build(){
            return new Attendance(this);
        }
    }

}
