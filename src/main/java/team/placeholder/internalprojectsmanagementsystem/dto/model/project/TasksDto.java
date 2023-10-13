package team.placeholder.internalprojectsmanagementsystem.dto.model.project;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.sql.Time;

@Getter
@Setter
public class TasksDto {
    private Long id;
    private String title,description,status;
    private Date start_time,end_time;
    private Time excepted_mm,actual_mm;
}
