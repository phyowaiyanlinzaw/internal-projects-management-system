package team.placeholder.internalprojectsmanagementsystem.model.project;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
@Entity
@Table(name="amount")
@NoArgsConstructor
@Getter
@Setter
public class Amount implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int basic_design;
    private int detail_design;
    private int coding;
    private int unit_testing;
    private int integrated_testing;

    @ManyToOne
    @JoinColumn(name="project_id")
    private Project project;

    
}
