package team.placeholder.internalprojectsmanagementsystem.dto.model.project;

import lombok.Getter;
import lombok.Setter;
import java.util.Objects;

@Getter
@Setter
public class TaskNotificationDto {
    private long id;
    private String description;
    private long noti_time;

    private TasksDto task;


    @Override
    public int hashCode() {
        int result = Objects.hash(id, description, noti_time);
        result = 31 * result + Objects.hash(task);
        return result;
    }


    @Override
    public String toString() {
        return "TaskNotificationDto{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", noti_time=" + noti_time +
                ", task=" + task +
                '}';
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaskNotificationDto that = (TaskNotificationDto) o;
        return id == that.id &&
                noti_time == that.noti_time &&
                Objects.equals(description, that.description) &&
                Objects.equals(task, that.task);
    }
}
