package team.placeholder.internalprojectsmanagementsystem.dto.model.project;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class DeliverableDto {
    private long id;
    private String description;
    private String name;
    private String type;
    private String status;
    private Date due_date;
}
