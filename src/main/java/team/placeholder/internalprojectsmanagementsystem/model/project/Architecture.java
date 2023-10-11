package team.placeholder.internalprojectsmanagementsystem.model.project;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
@Entity
@Table(name="architecture")
@NoArgsConstructor
@Getter
@Setter
public class Architecture implements Serializable {
    @Id
    private Long id;
    private String tech_name;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;
}
