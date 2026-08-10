package za.ac.domain;

import jakarta.persistence.*;

@Entity
@Table(name="outreachProgram")
public class OutreachProgram {
    @Id
    private String programId;
    private String programName;
    private String description;

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

        public Builder copy(OutreachProgram outreachProgram){
            this.programId = outreachProgram.programId;
            this.programName = outreachProgram.programName;
            this.description = outreachProgram.description;
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

        public OutreachProgram build(){
            return new OutreachProgram(this);
        }
    }
}
