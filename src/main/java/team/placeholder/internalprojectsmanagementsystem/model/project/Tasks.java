package team.placeholder.internalprojectsmanagementsystem.model.project;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import team.placeholder.internalprojectsmanagementsystem.model.project.projectenums.TasksGroup;
import team.placeholder.internalprojectsmanagementsystem.model.project.projectenums.TaskStatus;
import team.placeholder.internalprojectsmanagementsystem.model.user.User;
import java.io.Serializable;

@Entity
@Table(name="tasks")
@NoArgsConstructor
@Getter
@Setter
public class Tasks implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Enumerated(EnumType.STRING)
    private TaskStatus status;

    private String description;
    private String title;

    private long planStartTime;
    private long planEndTime;
    private Double planHours;

    private long actualStartTime;
    private long actualEndTime;
    private Double actualHours;

    private boolean due;

    public Tasks(Long id, String description) {
        this.id = id;
        this.description = description;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "project_id")
    private Project project;

    @Enumerated(EnumType.STRING)
    private TasksGroup tasksGroup;

    @ManyToOne
    private User user;

    private boolean deleted;

}
