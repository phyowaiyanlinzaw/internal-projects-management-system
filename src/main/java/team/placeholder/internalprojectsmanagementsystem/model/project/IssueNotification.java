package team.placeholder.internalprojectsmanagementsystem.model.project;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import team.placeholder.internalprojectsmanagementsystem.model.issue.Issue;

@Entity
@Table(name="issue_notification")
@NoArgsConstructor
@Getter
@Setter
public class IssueNotification {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String description;

    private long noti_time;

    @ManyToOne
    @JoinColumn(name = "issue_id")
    private Issue issue;


}
