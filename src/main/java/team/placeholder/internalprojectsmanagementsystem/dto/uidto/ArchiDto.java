package team.placeholder.internalprojectsmanagementsystem.dto.uidto;


import lombok.Getter;
import lombok.Setter;
import team.placeholder.internalprojectsmanagementsystem.model.project.Project;

@Getter
@Setter
public class ArchiDto {
    private Long id;
    private String tech_name;
    private Project project;
    private long projectId;



}
