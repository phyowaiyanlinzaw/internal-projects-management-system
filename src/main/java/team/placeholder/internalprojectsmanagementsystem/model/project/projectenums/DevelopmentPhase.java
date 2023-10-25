package team.placeholder.internalprojectsmanagementsystem.model.project.projectenums;


public enum DevelopmentPhase {
    PLANNING,DESIGN,CODING,TESTING,REVIEW,DEPLOYMENT,MAINTENANCE;

     public String getPhaseName(){
        return this.name();
    }

}
