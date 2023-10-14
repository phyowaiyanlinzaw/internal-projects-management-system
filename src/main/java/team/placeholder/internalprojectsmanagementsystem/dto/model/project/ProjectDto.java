package team.placeholder.internalprojectsmanagementsystem.dto.model.project;

import lombok.Getter;
import lombok.Setter;
import team.placeholder.internalprojectsmanagementsystem.model.project.projectenums.Development_phase;

import java.sql.Date;

@Getter
@Setter
public class ProjectDto {
    private long id;
    private String name;
    private String background;
    private int duration;
    private Date start_date;
    private Date end_date;
    private Development_phase current_phase;
    private String objective;

}
