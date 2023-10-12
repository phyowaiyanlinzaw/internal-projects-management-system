package team.placeholder.internalprojectsmanagementsystem.model.project;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Date;

@Entity
@Table(name="deliverable")
@NoArgsConstructor
@Getter
@Setter
public class Deliverable implements Serializable {
    @Id
    @GeneratedValue
    private long id;
    private String description;
    private String name;
    private String type;
    private String status;
    private Date due_date;

    @ManyToOne
    @JoinTable(name="project_id")
    private Project project;
}
