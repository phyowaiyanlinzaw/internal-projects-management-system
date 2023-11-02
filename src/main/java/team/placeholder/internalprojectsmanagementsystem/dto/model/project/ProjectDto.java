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

import java.io.Serializable;
import java.sql.Date;
import java.util.List;
import java.util.Set;

@Getter
@Setter
public class ProjectDto implements Serializable {
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
    private Set<ArchitectureDto>  architectureDto;
    private ReviewDto reviewDto;
    private List<SystemOutLineDto> systemOutLineDto;
    private List <DeliverableDto> deliverableDto;


    private TasksDto tasksDto;
    private IssueDto issueDto;


    @Override
    public String toString() {
        return "ProjectDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", background='" + background + '\'' +
                ", duration=" + duration +
                ", start_date=" + start_date +
                ", end_date=" + end_date +
                ", current_phase=" + current_phase +
                ", objective='" + objective + '\'' +
                ", clientDto=" + clientDto +
                ", userDto=" + userDto +
                ", departmentDto=" + departmentDto +
                ", amountDto=" + amountDto +
                ", architectureDto=" + architectureDto +
                ", reviewDto=" + reviewDto +
                ", systemOutLineDto=" + systemOutLineDto +
                ", deliverableDto=" + deliverableDto +
                ", tasksDto=" + tasksDto +
                ", issueDto=" + issueDto +
                '}';
    }
}
