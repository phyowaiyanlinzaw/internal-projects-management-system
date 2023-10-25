package team.placeholder.internalprojectsmanagementsystem.model.project;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name="deliverable")
@NoArgsConstructor
@Getter
@Setter
public class Deliverable implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private boolean status;

    @ManyToMany(mappedBy = "deliverables")
    private Set<DeliverableType> deliverableTypes;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Deliverable other = (Deliverable) o;
        return id == other.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }



}
