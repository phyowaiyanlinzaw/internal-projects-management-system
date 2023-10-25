package team.placeholder.internalprojectsmanagementsystem.dto.model.project;

import lombok.Getter;
import lombok.Setter;
import team.placeholder.internalprojectsmanagementsystem.model.department.Department;
import team.placeholder.internalprojectsmanagementsystem.model.project.projectenums.DevelopmentPhase;
import team.placeholder.internalprojectsmanagementsystem.model.user.Client;
import team.placeholder.internalprojectsmanagementsystem.model.user.User;

import java.sql.Date;

@Getter
@Setter
public class ProjectDto {
    private long id;
    private String name;
    private String background;
    private int duration;
    private Date start_date;
    private Date end_date;
    private DevelopmentPhase current_phase;
    private String objective;
    private Client client;
    private User user;
    private Department department;


}
