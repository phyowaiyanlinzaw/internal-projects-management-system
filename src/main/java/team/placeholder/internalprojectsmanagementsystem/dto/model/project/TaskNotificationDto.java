package team.placeholder.internalprojectsmanagementsystem.dto.model.project;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskNotificationDto {
    private long id;
    private String description;
    private long noti_time;

    private TasksDto task;

}
