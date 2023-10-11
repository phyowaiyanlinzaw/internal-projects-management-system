package team.placeholder.internalprojectsmanagementsystem.model.project;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import team.placeholder.internalprojectsmanagementsystem.model.user.User;

import java.io.Serializable;
import java.security.SecureRandomParameters;
import java.sql.Time;
import java.time.DateTimeException;

@Entity
@NoArgsConstructor
@Table(name="tasks")
@Getter
@Setter
public class Tasks implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private String status;
    private DateTimeException start_time;
    private DateTimeException end_time;
    private Time excepted_mm;
    private Time actual_mm;

    @ManyToOne
    @JoinColumn(name="project_id")
    private Project project;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

}
