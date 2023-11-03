package team.placeholder.internalprojectsmanagementsystem.model.project;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import team.placeholder.internalprojectsmanagementsystem.model.department.Department;
import team.placeholder.internalprojectsmanagementsystem.model.issue.Issue;
import team.placeholder.internalprojectsmanagementsystem.model.project.projectenums.DevelopmentPhase;
import team.placeholder.internalprojectsmanagementsystem.model.user.Client;
import team.placeholder.internalprojectsmanagementsystem.model.user.User;
import java.io.Serializable;
import java.sql.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@NoArgsConstructor
@Table(name="project")
@Getter
@Setter
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String background;
    private int duration;
    private long start_date;
    private long end_date;
    private DevelopmentPhase current_phase;
    @Column(length = 1000)
    private String objective;



    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> reviews;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Tasks> tasks;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SystemOutLine> systemOutLines ;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Deliverable> deliverables ;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "amount_id")
    private Amount amount;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Issue> issues;

    @ManyToMany(cascade = { CascadeType.PERSIST,
            CascadeType.MERGE })
    @JoinTable(name = "project_architecture", joinColumns = @JoinColumn(name = "project_id"), inverseJoinColumns = @JoinColumn(name = "architecture_id"))
    private Set<Architecture> architectures = new HashSet<>();

    @ManyToOne
    @JoinColumn(name="department_id")
    private Department department;

    @ManyToOne
    @JoinColumn(name="client_id")
    private Client client;

    @ManyToOne
    @JoinColumn(name="pm_id")
    private User projectManager;

    @ManyToMany
    @JoinTable(
            name = "user_project",
            joinColumns = @JoinColumn(name = "project_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> users = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Project other = (Project) o;
        return id == other.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", background='" + background + '\'' +
                ", duration=" + duration +
                ", start_date=" + start_date +
                ", end_date=" + end_date +
                ", current_phase=" + current_phase +
                ", objective='" + objective + '\'' +
                ", reviews=" + reviews +
                ", tasks=" + tasks +
                ", systemOutLines=" + systemOutLines +
                ", deliverables=" + deliverables +
                ", amount=" + amount +
                ", issues=" + issues +
                ", architectures=" + architectures +
                ", department=" + department +
                ", client=" + client +
                ", user=" + user +
                ", users=" + users +
                '}';
    }
}
