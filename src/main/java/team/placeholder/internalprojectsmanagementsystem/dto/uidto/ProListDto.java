package team.placeholder.internalprojectsmanagementsystem.dto.uidto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import team.placeholder.internalprojectsmanagementsystem.dto.model.project.TasksDto;
import team.placeholder.internalprojectsmanagementsystem.dto.model.user.UserDto;

@Getter
@Setter
public class ProListDto {
    
    private long id;
    private String projectName;
    private long percentage;
    private long startDate;
    private long endDate;
    private UserDto user;
    private List<TasksDto> tasks;
    private boolean closed;

    public ProListDto() {}

    public ProListDto(long id, String projectName, long percentage, long startDate, long endDate, UserDto user, List<TasksDto> tasks) {
        this.id = id;
        this.projectName = projectName;
        this.percentage = percentage;
        this.startDate = startDate;
        this.endDate = endDate;
        this.user = user;
        this.tasks = tasks;
    }
    
}
