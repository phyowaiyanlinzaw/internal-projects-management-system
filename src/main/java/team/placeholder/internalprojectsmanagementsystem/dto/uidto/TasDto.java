package team.placeholder.internalprojectsmanagementsystem.dto.uidto;


import lombok.Getter;
import lombok.Setter;
import team.placeholder.internalprojectsmanagementsystem.model.project.TaskNotification;
import team.placeholder.internalprojectsmanagementsystem.model.project.Project;
import team.placeholder.internalprojectsmanagementsystem.model.user.User;

import java.sql.Date;
import java.sql.Time;

@Getter
@Setter
public class TasDto {
    private Long id;
    private String title,description,status;
    private Date start_time;
    private Date end_time;
    private Time excepted_mm;
    private Time actual_mm;
    private Project project;
    private TaskNotification taskNotification;
    private User user;

    private long projectId;
    private long notificationId;
    private long userId;
}
