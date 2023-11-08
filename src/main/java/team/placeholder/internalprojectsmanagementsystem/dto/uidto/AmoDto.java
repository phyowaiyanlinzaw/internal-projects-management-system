package team.placeholder.internalprojectsmanagementsystem.dto.uidto;


import lombok.Getter;
import lombok.Setter;
import team.placeholder.internalprojectsmanagementsystem.model.project.Project;

@Getter
@Setter
public class AmoDto {
    private long id;
    private int basic_design;
    private int detail_design;
    private int coding;
    private int unit_testing;
    private int integrated_testing;

    private Project project;

    private long projectId;
}
