package team.placeholder.internalprojectsmanagementsystem.model.issue;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.io.Serializable;
import java.sql.Date;

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




}
