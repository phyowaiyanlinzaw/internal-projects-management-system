package team.placeholder.internalprojectsmanagementsystem.model.project;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.security.SecureRandomParameters;
@Entity
@NoArgsConstructor
@Table(name="tasks")
@Getter
@Setter
public class Tasks implements Serializable {
    @Id

    private Long id;
    private String title;
    private String description;
    private String status;
}
