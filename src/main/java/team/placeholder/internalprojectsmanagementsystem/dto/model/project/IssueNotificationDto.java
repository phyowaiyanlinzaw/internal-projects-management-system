package team.placeholder.internalprojectsmanagementsystem.dto.model.project;


import lombok.Getter;
import lombok.Setter;
import team.placeholder.internalprojectsmanagementsystem.dto.model.issue.IssueDto;

@Getter
@Setter
public class IssueNotificationDto {
    private long id;

    private String description;

    private long noti_time;

    private IssueDto issue;
}
