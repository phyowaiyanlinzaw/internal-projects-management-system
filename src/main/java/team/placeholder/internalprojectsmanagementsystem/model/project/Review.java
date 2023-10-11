package team.placeholder.internalprojectsmanagementsystem.model.project;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
@Entity
@Table(name="review")
@NoArgsConstructor
@Getter
@Setter
public class Review implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int internal_review_count;
    private int external_review_count;

    @ManyToOne
    @JoinColumn(name="project_id")
    private Project project;
}
