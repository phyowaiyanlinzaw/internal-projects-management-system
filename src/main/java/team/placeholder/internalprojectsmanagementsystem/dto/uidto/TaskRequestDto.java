package team.placeholder.internalprojectsmanagementsystem.dto.uidto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TaskRequestDto {

    private long id;
    private String title;
    private String description;
    private long projectId;
    private long userId;
    private String tasksGroup;
    private long plan_start_time;
    private long plan_end_time;
    private Double plan_hours;
    private String status;
}
