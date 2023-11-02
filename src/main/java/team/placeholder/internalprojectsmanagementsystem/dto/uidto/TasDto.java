package team.placeholder.internalprojectsmanagementsystem.dto.uidto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import team.placeholder.internalprojectsmanagementsystem.dto.model.user.UserDto;
import team.placeholder.internalprojectsmanagementsystem.model.project.TaskNotification;
import team.placeholder.internalprojectsmanagementsystem.model.project.Project;
import team.placeholder.internalprojectsmanagementsystem.model.project.projectenums.TaskStatus;
import team.placeholder.internalprojectsmanagementsystem.model.user.User;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TasDto {
    private Long id;
    private String title,description;
    private TaskStatus status;
    private long start_time;
    private long end_time;
    private long actual_start_time;
    private long actual_end_time;
    private List<UserDto> user;

    @Override
    public String toString() {
        return "TasDto{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                ", start_time=" + start_time +
                ", end_time=" + end_time +
                ", actual_start_time=" + actual_start_time +
                ", actual_end_time=" + actual_end_time +
                ", user=" + user +
                '}';
    }
}
