package team.placeholder.internalprojectsmanagementsystem.model.project;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

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

    @ManyToOne
    @JoinColumn(name = "deliverable_type_id")
    private DeliverableType deliverableTypes;

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
