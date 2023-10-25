package team.placeholder.internalprojectsmanagementsystem.dto.model.project;

import lombok.Getter;
import lombok.Setter;
import team.placeholder.internalprojectsmanagementsystem.dto.model.department.DepartmentDto;
import team.placeholder.internalprojectsmanagementsystem.dto.model.issue.IssueDto;
import team.placeholder.internalprojectsmanagementsystem.dto.model.user.ClientDto;
import team.placeholder.internalprojectsmanagementsystem.dto.model.user.UserDto;
import team.placeholder.internalprojectsmanagementsystem.model.department.Department;
import team.placeholder.internalprojectsmanagementsystem.model.project.Project;
import team.placeholder.internalprojectsmanagementsystem.model.project.projectenums.DevelopmentPhase;
import team.placeholder.internalprojectsmanagementsystem.model.user.Client;
import team.placeholder.internalprojectsmanagementsystem.model.user.User;

import java.sql.Date;

@Getter
@Setter
public class ProjectDto {
    private long id;
    private String name;
    private String background;
    private int duration;
    private long start_date;
    private long end_date;
    private DevelopmentPhase current_phase;
    private String objective;
    private ClientDto clientDto;
    private UserDto userDto;
    private DepartmentDto departmentDto;
    private AmountDto amountDto;
    private ArchitectureDto architectureDto;
    private ReviewDto reviewDto;
    private SystemOutLineDto systemOutLineDto;
    private DeliverableDto deliverableDto;
    private TasksDto tasksDto;
    private IssueDto issueDto;




}
