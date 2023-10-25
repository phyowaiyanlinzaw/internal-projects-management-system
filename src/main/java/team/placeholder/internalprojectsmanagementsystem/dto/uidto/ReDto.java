package team.placeholder.internalprojectsmanagementsystem.dto.uidto;

import lombok.Getter;
import lombok.Setter;
import team.placeholder.internalprojectsmanagementsystem.model.project.Project;
import team.placeholder.internalprojectsmanagementsystem.model.user.User;


@Getter
@Setter
public class ReDto {
    private Long id;
    private int internal_review_count;
    private int external_review_count;

    private Project project;
    private User user;
    private long projectId;
    private long userId;
}
