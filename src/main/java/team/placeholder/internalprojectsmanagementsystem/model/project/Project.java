package team.placeholder.internalprojectsmanagementsystem.model.project;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import team.placeholder.internalprojectsmanagementsystem.model.user.Client;

import java.io.Serializable;
import java.sql.Date;

@Entity
@Table(name="project")
@Getter
@Setter
@NoArgsConstructor
public class Project implements Serializable {
    @Id
    private Long id;
    private String name;
    private String backgroung;
    private int duration;
    private Date start_date;
    private Date end_date;
    private String objective;

    @ManyToOne
    @JoinColumn(name="client_id")
    private Client client;

}
