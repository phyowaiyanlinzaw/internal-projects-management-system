package team.placeholder.internalprojectsmanagementsystem.dto.model.project;

import lombok.Getter;
import lombok.Setter;
import team.placeholder.internalprojectsmanagementsystem.model.project.projectenums.TaskStatus;

@Getter
@Setter
public class ProjectStatusDto {
    private long id;
    private TaskStatus name;
}
