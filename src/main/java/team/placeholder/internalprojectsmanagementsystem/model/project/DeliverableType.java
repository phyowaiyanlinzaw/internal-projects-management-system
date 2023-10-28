package team.placeholder.internalprojectsmanagementsystem.model.project;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Setter
@NoArgsConstructor
@Getter
@Table(name = "deliverable_type")
public class DeliverableType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String type;

    @ManyToMany
    @JoinTable(
            name = "deli_type_mtm",
            joinColumns = @JoinColumn(name = "deliverable_type_id"),
            inverseJoinColumns = @JoinColumn(name = "deliverable_id")
    )
    private Set<Deliverable> deliverables;
}
