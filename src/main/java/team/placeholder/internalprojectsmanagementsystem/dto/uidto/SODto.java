package team.placeholder.internalprojectsmanagementsystem.dto.uidto;

import lombok.Getter;
import lombok.Setter;
import team.placeholder.internalprojectsmanagementsystem.model.project.Project;

@Getter
@Setter
public class SODto {
    private long id;
    private boolean analysis,sys_design,coding,testing,deploy,maintenance,document;

    private Project project;
    private long projectId;
}
