package team.placeholder.internalprojectsmanagementsystem.dto.model.project;

import lombok.Getter;
import lombok.Setter;
import team.placeholder.internalprojectsmanagementsystem.model.project.Project;
import team.placeholder.internalprojectsmanagementsystem.model.project.projectenums.TaskStatus;
import team.placeholder.internalprojectsmanagementsystem.model.user.User;

import java.sql.Date;
import java.sql.Time;

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
    private User user;
    private Project project;


}
