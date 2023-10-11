package team.placeholder.internalprojectsmanagementsystem.model.project;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import team.placeholder.internalprojectsmanagementsystem.model.project.projectenums.Status;

import java.io.Serializable;
@Entity
@Table(name="architecture")
@NoArgsConstructor
@Getter
@Setter
public class Architecture implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Status tech_name;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;
}
