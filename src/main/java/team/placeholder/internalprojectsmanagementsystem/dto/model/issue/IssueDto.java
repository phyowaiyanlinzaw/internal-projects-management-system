package team.placeholder.internalprojectsmanagementsystem.dto.model.issue;

import lombok.Getter;
import lombok.Setter;
import team.placeholder.internalprojectsmanagementsystem.model.issue.IssueCategory;
import team.placeholder.internalprojectsmanagementsystem.model.project.Project;
import team.placeholder.internalprojectsmanagementsystem.model.user.User;

import java.sql.Date;

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
    private int client_or_user;
    private boolean solved;
    private Date created_date;
    private Date updated_date;

    public IssueCategory getIssueCategory() {
        return null;
    }

    public Project getProject() {
        return null;
    }

    public User getUser_uploader() {
        return null;
    }

    public User getUser_pic() {
        return null;
    }
}
