package team.placeholder.internalprojectsmanagementsystem.model.project.projectenums;


public enum Development_phase {
    PLANNING,DESIGN,CODING,TESTING,REVIEW,DEPLOYMENT,MAINTENANCE;

     public String getPhaseName(){
        return this.name();
    }

}
