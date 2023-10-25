package team.placeholder.internalprojectsmanagementsystem.dto.uidto;


import lombok.Getter;
import lombok.Setter;
import team.placeholder.internalprojectsmanagementsystem.model.department.Department;
import team.placeholder.internalprojectsmanagementsystem.model.project.*;
import team.placeholder.internalprojectsmanagementsystem.model.project.projectenums.DevelopmentPhase;
import team.placeholder.internalprojectsmanagementsystem.model.user.Client;
import team.placeholder.internalprojectsmanagementsystem.model.user.User;

import java.sql.Date;

@Getter
@Setter
public class PrjDto {
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
    private Review review;
    private Tasks tasks;
    private Amount amount;
    private SystemOutLine systemOutLine;
    private Architecture architecture;
    private Deliverable deliverable;
    private ProjectStatus projectStatus;

    private long pmId;
    private long clientId;
    private String clientName;

    private long departmentId;



    private int totalTasks;
    private int doneTasks;
    private int internalReviews;
    private int externalReviews;






}
