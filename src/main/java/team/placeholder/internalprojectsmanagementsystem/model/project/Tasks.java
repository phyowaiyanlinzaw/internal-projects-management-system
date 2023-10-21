package team.placeholder.internalprojectsmanagementsystem.model.project;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import team.placeholder.internalprojectsmanagementsystem.model.user.User;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="tasks")
@NoArgsConstructor
@Getter
@Setter
public class Tasks implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String status;
    private String description;
    private String title;
    private Date start_time;
    private Date end_time;
    private Time actual_mm;
    private Time expected_mm;

    @ManyToMany
    @JoinTable(name="tasks_notification",
            joinColumns = @JoinColumn(name="tasks_id"),
            inverseJoinColumns = @JoinColumn(name="notification_id"))
    private Set<Notification> notifications = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

}
