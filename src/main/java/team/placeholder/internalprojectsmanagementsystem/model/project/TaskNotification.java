package team.placeholder.internalprojectsmanagementsystem.model.project;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name="task_notification")
@NoArgsConstructor
@Getter
@Setter
public class TaskNotification implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String description;
    private long noti_time;

    //Many to one with tasks
    @ManyToOne
    @JoinColumn(name = "task_id")
    private Tasks tasks;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaskNotification noti = (TaskNotification) o;
        return id == noti.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }



}
