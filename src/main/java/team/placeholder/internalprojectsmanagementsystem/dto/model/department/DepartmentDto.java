package team.placeholder.internalprojectsmanagementsystem.dto.model.department;

import lombok.Getter;
import lombok.Setter;
import team.placeholder.internalprojectsmanagementsystem.dto.model.user.UserDto;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class DepartmentDto implements Serializable {
    private long id;
    private String name;
    private List<UserDto> users;

}
