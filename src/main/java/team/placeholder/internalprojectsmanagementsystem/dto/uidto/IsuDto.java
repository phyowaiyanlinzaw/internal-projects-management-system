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
    private long created_date;
    private long updated_date;
    private long solved_date;
    private String responsible_type;
    private String issue_category;
    private long user_uploader;
    private long user_pic;
    private long responsible_party;
    private long project_id;






}
