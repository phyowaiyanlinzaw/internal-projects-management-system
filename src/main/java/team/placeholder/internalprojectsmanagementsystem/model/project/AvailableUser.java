package team.placeholder.internalprojectsmanagementsystem.model.project;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import team.placeholder.internalprojectsmanagementsystem.model.user.User;

@Entity
@Getter
@Setter
@Table(name = "available_user")
public class AvailableUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    private boolean avaliable;

}
