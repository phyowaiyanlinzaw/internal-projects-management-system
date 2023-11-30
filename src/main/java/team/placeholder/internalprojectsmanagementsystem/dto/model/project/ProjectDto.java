package team.placeholder.internalprojectsmanagementsystem.dto.model.project;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import team.placeholder.internalprojectsmanagementsystem.dto.model.department.DepartmentDto;
import team.placeholder.internalprojectsmanagementsystem.dto.model.issue.IssueDto;
import team.placeholder.internalprojectsmanagementsystem.dto.model.user.ClientDto;
import team.placeholder.internalprojectsmanagementsystem.dto.model.user.UserDto;
import team.placeholder.internalprojectsmanagementsystem.model.project.Project;
import team.placeholder.internalprojectsmanagementsystem.model.project.projectenums.DevelopmentPhase;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;
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
    private boolean closed;
    private List<UserDto> membersUserDto;

    private List<TasksDto> tasksDto;
    private List<IssueDto> issueDto;


    public ProjectDto(Long id, DepartmentDto departmentDto, String name) {
        this.id = id;
        this.departmentDto = departmentDto;
        this.name = name;
    }

    public ProjectDto(Project project) {

    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        ProjectDto other = (ProjectDto) obj;

        return id == other.id &&
                duration == other.duration &&
                start_date == other.start_date &&
                end_date == other.end_date &&
                current_phase == other.current_phase &&
                closed == other.closed &&
                Objects.equals(name, other.name) &&
                Objects.equals(objective, other.objective) &&
                Objects.equals(clientDto, other.clientDto) &&
                Objects.equals(projectManagerUserDto, other.projectManagerUserDto) &&
                Objects.equals(departmentDto, other.departmentDto) &&
                Objects.equals(amountDto, other.amountDto) &&
                Objects.equals(architectureDto, other.architectureDto) &&
                Objects.equals(reviewDto, other.reviewDto) &&
                Objects.equals(systemOutLineDto, other.systemOutLineDto) &&
                Objects.equals(completeTaskCount, other.completeTaskCount) &&
                Objects.equals(totalTaskCount, other.totalTaskCount) &&
                Objects.equals(membersUserDto, other.membersUserDto);
    }


    @Override
    public int hashCode() {
        return Objects.hash(id, name, duration, start_date, end_date, current_phase, objective, clientDto,
                projectManagerUserDto, departmentDto, amountDto, architectureDto, reviewDto, systemOutLineDto,
                completeTaskCount, totalTaskCount, closed, membersUserDto);
    }



}
