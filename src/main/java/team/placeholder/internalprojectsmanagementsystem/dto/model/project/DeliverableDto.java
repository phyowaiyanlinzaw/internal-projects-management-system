package team.placeholder.internalprojectsmanagementsystem.dto.model.project;

import lombok.Getter;
import lombok.Setter;
import team.placeholder.internalprojectsmanagementsystem.model.project.DeliverableType;

import java.sql.Date;

@Getter
@Setter
public class DeliverableDto {
    private long id;
    private boolean status;
    private DeliverableTypeDto deliverableType;

    @Override
    public String toString() {
        return "DeliverableDto{" +
                "id=" + id +
                ", status=" + status +
                ", deliverableType=" + deliverableType +
                '}';
    }
}
