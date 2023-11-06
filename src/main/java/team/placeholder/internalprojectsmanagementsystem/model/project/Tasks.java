package team.placeholder.internalprojectsmanagementsystem.model.project;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import team.placeholder.internalprojectsmanagementsystem.model.project.projectenums.Group;
import team.placeholder.internalprojectsmanagementsystem.model.project.projectenums.TaskStatus;
import team.placeholder.internalprojectsmanagementsystem.model.user.User;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import java.util.List;

@Entity
@Table(name="tasks")
@NoArgsConstructor
@Getter
@Setter
public class Tasks implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private TaskStatus status;
    private String description;
    private String title;
    private long plan_start_time;
    private long plan_end_time;
    private long actual_start_time;
    private long actual_end_time;

    @Enumerated(EnumType.STRING)
    private Group group;

    @OneToMany(mappedBy = "tasks")
    private List<TaskNotification> taskNotifications;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

}
