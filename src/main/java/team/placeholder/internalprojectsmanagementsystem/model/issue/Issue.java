package team.placeholder.internalprojectsmanagementsystem.model.issue;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import team.placeholder.internalprojectsmanagementsystem.model.issue.issueenum.Category;
import team.placeholder.internalprojectsmanagementsystem.model.issue.issueenum.ResponsibleType;
import team.placeholder.internalprojectsmanagementsystem.model.issue.issueenum.Status;
import team.placeholder.internalprojectsmanagementsystem.model.project.Project;
import team.placeholder.internalprojectsmanagementsystem.model.user.User;
import java.util.Objects;

@Entity
@Table(name="issue_ledgar")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Issue{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private String description;
    private String place;
    private String impact;
    private String root_cause;
    private String direct_cause;
    private String corrective_action;
    private String preventive_action;

    @JsonProperty("responsible_party")
    private long responsible_party;
    private boolean solved;
    private long created_date;
    private long updated_date;
    private long solved_date;

    @Enumerated(EnumType.STRING)
    private Status status;


    @Enumerated(EnumType.STRING)
    private Category issue_category;

    @Enumerated(EnumType.STRING)
    private ResponsibleType responsible_type;

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
