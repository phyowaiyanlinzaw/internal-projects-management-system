package team.placeholder.internalprojectsmanagementsystem.dto.uidto;


import lombok.Getter;
import lombok.Setter;
import team.placeholder.internalprojectsmanagementsystem.model.project.Project;
import team.placeholder.internalprojectsmanagementsystem.model.project.projectenums.TaskStatus;

@Getter
@Setter
public class Prj_statusDto {
    private long id;
    private TaskStatus name;
    private Project project;
    private long projectId;
}
