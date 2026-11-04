package za.ac.domain;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="outreachProgram")
public class OutreachProgram {
    @Id
    @Column(name = "outreach_program_id")
    private String programId;
    private String programName;
    private String description;

    @ManyToOne
    @JoinColumn(name = "leader_id")
    private Leader leader;

    @ManyToMany
    @JoinTable(
            name = "member_outreach_program",
            joinColumns = @JoinColumn(name = "program_id"),
            inverseJoinColumns = @JoinColumn(name = "member_id")
    )
    private List<Member> members;

    protected OutreachProgram(){}

    public OutreachProgram(Builder builder){
        this.programId = builder.programId;
        this.programName = builder.programName;
        this.description = builder.description;
    }

    public String getProgramId(){ return programId;}
    public String getProgramName(){ return programName;}
    public String getDescription(){ return description;}

    public String toString(){
        return "Outreach Program { " + "\n" +
                "Program Id: " + programId + "\n" +
                "Program name: " + programName + "\n" +
                "Description: " + description + "}";
    }

    public static class Builder{
        private String programId;
        private String programName;
        private String description;
        private Leader leader;
        private List<Member> members;

        public Builder copy(OutreachProgram outreachProgram){
            this.programId = outreachProgram.programId;
            this.programName = outreachProgram.programName;
            this.description = outreachProgram.description;
            this.leader = outreachProgram.leader;
            this.members = outreachProgram.members;
            return this;
        }

        public Builder setProgramId(String programId){
            this.programId = programId;
            return this;
        }
        public Builder setProgramName(String programName){
            this.programName = programName;
            return this;
        }
        public Builder setDescription(String description){
            this.description = description;
            return this;
        }
        public Builder setLeader(Leader leader){
            this.leader = leader;
            return this;
        }
        public Builder setMembers(List<Member> members){
            this.members = members;
            return this;
        }

        public OutreachProgram build(){
            return new OutreachProgram(this);
        }
    }
}
