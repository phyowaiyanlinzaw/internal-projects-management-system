package team.placeholder.internalprojectsmanagementsystem.dto.model.issue;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import team.placeholder.internalprojectsmanagementsystem.dto.model.project.ProjectDto;
import team.placeholder.internalprojectsmanagementsystem.dto.model.user.UserDto;
import team.placeholder.internalprojectsmanagementsystem.model.issue.issueenum.Category;
import team.placeholder.internalprojectsmanagementsystem.model.issue.issueenum.ResponsibleType;
import team.placeholder.internalprojectsmanagementsystem.model.project.Project;
import team.placeholder.internalprojectsmanagementsystem.model.user.User;

import java.io.Serializable;


@Getter
@Setter
public class IssueDto implements Serializable {
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
    private String status;
    private String issue_category;
    private String responsible_type;
    private ProjectDto projectDto;
    private UserDto user_uploader;
    private UserDto user_pic;



}
