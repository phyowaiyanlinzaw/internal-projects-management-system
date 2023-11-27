package team.placeholder.internalprojectsmanagementsystem.dto.model.project;

import lombok.Getter;
import lombok.Setter;
import team.placeholder.internalprojectsmanagementsystem.dto.model.user.UserDto;
import team.placeholder.internalprojectsmanagementsystem.model.project.projectenums.TasksGroup;
import team.placeholder.internalprojectsmanagementsystem.model.project.projectenums.TaskStatus;

@Getter
@Setter
public class TasksDto {
    private Long id;
    private TaskStatus status;
    private String title,description;
    private long planStartTime;
    private long planEndTime;

    private long actualStartTime;
    private long actualEndTime;


    private Double planHours;
    private Double actualHours;

    private boolean due;

    private UserDto userDto;
    private TasksGroup tasksGroup;
    private ProjectDto projectDto;

}
