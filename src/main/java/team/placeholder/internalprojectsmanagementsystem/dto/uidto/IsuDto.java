package team.placeholder.internalprojectsmanagementsystem.dto.uidto;

import lombok.Getter;
import lombok.Setter;



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
    private long responsible_party;
    private boolean solved;
    private long created_date;
    private long updated_date;
    private long solved_date;
    private String issue_category;
    private String responsible_type;
    private long project_id;
    private long user_uploader;
    private long user_pic;



}
