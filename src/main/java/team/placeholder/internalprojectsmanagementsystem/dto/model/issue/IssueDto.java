package team.placeholder.internalprojectsmanagementsystem.dto.model.issue;
import lombok.Getter;
import lombok.Setter;
import team.placeholder.internalprojectsmanagementsystem.dto.model.project.ProjectDto;
import team.placeholder.internalprojectsmanagementsystem.dto.model.user.UserDto;
import team.placeholder.internalprojectsmanagementsystem.model.issue.issueenum.Category;
import team.placeholder.internalprojectsmanagementsystem.model.project.Project;


@Getter
@Setter
public class IssueDto {
    private long id;
    private String title;
    private String description;
    private String place;
    private String impact;
    private String root_cause;
    private String direct_cause;
    private String corrective_action;
    private String preventive_action;
    private int responsible_party;
    private boolean solved;
    private long created_date;
    private long updated_date;
    private long solved_date;

    private Category issue_category;

    private ProjectDto projectDto;
    private UserDto user_uploader;
    private UserDto user_pic;




}
