package team.placeholder.internalprojectsmanagementsystem.dto.model.project;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import team.placeholder.internalprojectsmanagementsystem.dto.model.department.DepartmentDto;
import team.placeholder.internalprojectsmanagementsystem.dto.model.issue.IssueDto;
import team.placeholder.internalprojectsmanagementsystem.dto.model.user.ClientDto;
import team.placeholder.internalprojectsmanagementsystem.dto.model.user.UserDto;
import team.placeholder.internalprojectsmanagementsystem.model.project.projectenums.DevelopmentPhase;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
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
    private UserDto projectManagerUserDto; // project manager
    private DepartmentDto departmentDto;
    private AmountDto amountDto;
    private Set<ArchitectureDto>  architectureDto;
    private ReviewDto reviewDto;
    private SystemOutLineDto systemOutLineDto;
    private List<DeliverableDto> deliverableDto;
    private Long completeTaskCount;
    private Long totalTaskCount;
    private String status;
    private List<UserDto> membersUserDto;

    private List<TasksDto> tasksDto;
    private List<IssueDto> issueDto;

}
