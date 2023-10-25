package team.placeholder.internalprojectsmanagementsystem.dto.uidto;


import lombok.Getter;
import lombok.Setter;
import team.placeholder.internalprojectsmanagementsystem.model.project.Project;

import java.sql.Date;

@Getter
@Setter
public class DeliDto {
    private long id;
    private String description;
    private String name;
    private String type;
    private String status;
    private Date due_date;

    private Project project;
    private long projectId;

}
