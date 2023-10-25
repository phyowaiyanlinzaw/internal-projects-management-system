package team.placeholder.internalprojectsmanagementsystem.dto.uidto;

import lombok.Getter;
import lombok.Setter;
import team.placeholder.internalprojectsmanagementsystem.model.project.Project;
import team.placeholder.internalprojectsmanagementsystem.model.user.User;

import java.sql.Date;


@Getter
@Setter
public class IsuDto {
    private long id;
    private String title;
    private String description;
    private String place;
    private String impact;
    private String root_cause;
    private String direct_cause;
    private String corrective_action;
    private String preventive_action;
    private int clientId;
    private boolean solved;
    private Date created_date;
    private Date updated_date;

    private Project project;
    private User user;

    private long projectId;
    private long userId;
    private long pmId;



}
