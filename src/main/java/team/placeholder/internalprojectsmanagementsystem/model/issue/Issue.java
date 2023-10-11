package team.placeholder.internalprojectsmanagementsystem.model.issue;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name="issue_ledgar")
@NoArgsConstructor
@Getter
@Setter
public class Issue implements Serializable {
    @Id
    private Long id;
    private String title;
    private String description;



}
