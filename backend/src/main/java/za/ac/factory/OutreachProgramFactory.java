package za.ac.factory;

import za.ac.domain.OutreachProgram;
import za.ac.util.Helper;

public class OutreachProgramFactory {

    public static OutreachProgram createOutreachProgram(String programId, String programName, String description){

        if(Helper.isNullOrEmpty(programId) ||
        Helper.isNullOrEmpty(programName) ||
        Helper.isNullOrEmpty(description)){
            return null;
        }

        return new OutreachProgram.Builder()
                .setProgramId(programId)
                .setProgramName(programName)
                .setDescription(description)
                .build();
    }

}
