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

    private long plan_start_time;
    private long plan_end_time;
    private Double plan_hours;

    private long actual_start_time;
    private long actual_end_time;
    private Double actual_hours;

    private boolean due;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "project_id")
    private Project project;

    @Enumerated(EnumType.STRING)
    private TasksGroup tasksGroup;

    @ManyToOne
    private User user;

    private boolean deleted;

}
