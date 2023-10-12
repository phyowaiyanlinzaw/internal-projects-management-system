package team.placeholder.internalprojectsmanagementsystem.model.project.projectenums;

import java.util.stream.Stream;

public enum Status {
    STARTED , IN_PROGRESS, FINISHED;
    String getStatus_Name(){
        return this.name();
    }
}
