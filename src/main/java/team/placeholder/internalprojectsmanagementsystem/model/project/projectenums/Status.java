package team.placeholder.internalprojectsmanagementsystem.model.project.projectenums;

import java.util.stream.Stream;

public enum Status {
    STARTED , IN_PROGRESS, FINISHED;

    public static Stream<Status> stream(){
        return Stream.of(Status.values());
    }
}
