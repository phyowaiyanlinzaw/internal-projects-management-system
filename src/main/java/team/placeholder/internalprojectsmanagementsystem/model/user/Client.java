package team.placeholder.internalprojectsmanagementsystem.model.user;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import team.placeholder.internalprojectsmanagementsystem.model.project.Project;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="client")
@Getter
@Setter
@NoArgsConstructor
public class Client implements Serializable {
    @Id
    private long id;
    private String name;
    private String phone;
    private String email;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Project> project;

}
