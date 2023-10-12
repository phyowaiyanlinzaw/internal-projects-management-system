package team.placeholder.internalprojectsmanagementsystem.model.project;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import team.placeholder.internalprojectsmanagementsystem.model.project.projectenums.Status;

import java.io.Serializable;
@Entity
@Table(name="project_status")
@NoArgsConstructor
@Getter
@Setter
public class ProjectStatus implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Enumerated(EnumType.STRING)
    private Status name;

    @ManyToOne
    @JoinColumn(name="project_id")
    private Project project;

}
