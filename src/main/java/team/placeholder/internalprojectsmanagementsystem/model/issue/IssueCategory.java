package team.placeholder.internalprojectsmanagementsystem.model.issue;

import jakarta.persistence.*;
import jdk.jfr.Enabled;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import team.placeholder.internalprojectsmanagementsystem.model.project.Tasks;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IssueCategory other = (IssueCategory) o;
        return id == other.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
