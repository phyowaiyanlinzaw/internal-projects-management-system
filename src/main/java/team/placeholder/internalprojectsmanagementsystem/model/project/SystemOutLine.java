package team.placeholder.internalprojectsmanagementsystem.model.project;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
@Entity
@Table(name="system_outline")
@NoArgsConstructor
@Getter
@Setter
public class SystemOutLine implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private boolean analysis;
    private boolean sys_design;
    private boolean coding;
    private boolean testing;
    private boolean deploy;
    private boolean maintenence;
    private boolean document;

    @ManyToOne
    @JoinColumn(name="project_id")
    private Project project;
}
