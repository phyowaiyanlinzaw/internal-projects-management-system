package team.placeholder.internalprojectsmanagementsystem.model.project.projectenums;

public enum TaskStatus {
    TODO , IN_PROGRESS, FINISHED;
    String getStatus_Name(){
        return this.name();
    }
}
