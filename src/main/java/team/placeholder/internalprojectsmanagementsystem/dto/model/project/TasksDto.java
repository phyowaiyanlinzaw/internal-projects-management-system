package team.placeholder.internalprojectsmanagementsystem.dto.model.project;

import lombok.Getter;
import lombok.Setter;
import team.placeholder.internalprojectsmanagementsystem.dto.model.user.UserDto;
import team.placeholder.internalprojectsmanagementsystem.model.project.Project;
import team.placeholder.internalprojectsmanagementsystem.model.project.projectenums.TasksGroup;
import team.placeholder.internalprojectsmanagementsystem.model.project.projectenums.TaskStatus;
import team.placeholder.internalprojectsmanagementsystem.model.user.User;

@Getter
@Setter
public class TasksDto {
    private Long id;
    private TaskStatus status;
    private String title,description;
    private long plan_start_time;
    private long plan_end_time;
    private long actual_start_time;
    private long actual_end_time;
    private Double plan_hours;
    private Double actual_hours;
    private UserDto userDto;
    private TasksGroup tasksGroup;
    private ProjectDto projectDto;

}
