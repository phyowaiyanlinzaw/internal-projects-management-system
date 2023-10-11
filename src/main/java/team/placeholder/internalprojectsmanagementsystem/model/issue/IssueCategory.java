package team.placeholder.internalprojectsmanagementsystem.model.issue;

import jakarta.persistence.*;
import jdk.jfr.Enabled;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="issue_category")
@NoArgsConstructor
@Getter
@Setter
public class IssueCategory implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;

    @OneToMany(mappedBy = "issueCategory", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Issue> issues;
}
