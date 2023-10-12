package team.placeholder.internalprojectsmanagementsystem.dto.model.project;

import lombok.Getter;
import lombok.Setter;
import team.placeholder.internalprojectsmanagementsystem.model.project.projectenums.Status;

@Getter
@Setter
public class ProjectStatusDto {
    private long id;
    private Status name;
}
