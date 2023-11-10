package team.placeholder.internalprojectsmanagementsystem.model.project;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
public class Project implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String background;
    private int duration;
    private long start_date;
    private long end_date;

    @Enumerated(EnumType.STRING)
    private DevelopmentPhase current_phase;

    @Column(columnDefinition = "TEXT")
    private String objective;

    @OneToOne(cascade = CascadeType.ALL)
    private Review reviews;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private List<Tasks> tasks;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private SystemOutLine systemOutLine;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Deliverable> deliverables;

    private String status;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "amount_id")
    private Amount amount;

    @ManyToMany(cascade = { CascadeType.PERSIST,
            CascadeType.MERGE })
    @JoinTable(name = "project_architecture", joinColumns = @JoinColumn(name = "project_id"), inverseJoinColumns = @JoinColumn(name = "architecture_id"))
    private Set<Architecture> architectures = new HashSet<>();

    @ManyToOne
    @JoinColumn(name="client_id")
    private Client client;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="department_id")
    private Department department;

//
//    @JsonBackReference
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


}
