package team.placeholder.internalprojectsmanagementsystem.model.issue;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import team.placeholder.internalprojectsmanagementsystem.model.project.Project;
import team.placeholder.internalprojectsmanagementsystem.model.user.User;
import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name="issue_ledgar")
@NoArgsConstructor
@Getter
@Setter
public class Issue implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private String place;
    private String impact;
    private String root_cause;
    private String direct_cause;
    private String corrective_action;
    private String preventive_action;
    private int clint_or_user;
    private boolean solved;
    private Date created_date;
    private Date updated_date;

    @ManyToOne
    @JoinColumn(name="issueCategory_id")
    private IssueCategory issueCategory;

    @ManyToOne
    @JoinColumn(name="project_id")
    private Project project;

    @ManyToOne
    @JoinColumn(name="uploader_id")
    private User user_uploader;

    @ManyToOne
    @JoinColumn(name="pic_id")
    private User user_pic;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Issue other = (Issue) o;
        return id == other.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }


}
