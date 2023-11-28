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
    private Double actualHours;
    private long userId;
    private String tasksGroup;
    private long planStartTime;
    private long planEndTime;
    private Double planHours;
    private String status;
}
