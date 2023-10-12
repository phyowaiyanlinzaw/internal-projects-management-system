package team.placeholder.internalprojectsmanagementsystem.model.project;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import team.placeholder.internalprojectsmanagementsystem.model.user.User;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="notification")
@NoArgsConstructor
@Getter
@Setter
public class Notification implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String description;

    @ManyToMany(mappedBy = "notifications",cascade = CascadeType.ALL)
    private Set<Tasks> tasks = new HashSet<>();

}
