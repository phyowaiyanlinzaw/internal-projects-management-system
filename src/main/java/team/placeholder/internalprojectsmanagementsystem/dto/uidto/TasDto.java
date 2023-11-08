package team.placeholder.internalprojectsmanagementsystem.dto.uidto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import team.placeholder.internalprojectsmanagementsystem.dto.model.user.UserDto;
import team.placeholder.internalprojectsmanagementsystem.model.project.projectenums.TaskStatus;

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
}
