package team.placeholder.internalprojectsmanagementsystem.dto.model.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import team.placeholder.internalprojectsmanagementsystem.dto.model.department.DepartmentDto;
import team.placeholder.internalprojectsmanagementsystem.model.user.userenums.Role;

@Getter
@Setter
public class UserDto {
    private long id;
    private String name,email;
    @JsonIgnore
    String password;
    private Role role;

    @JsonProperty("department")
    private DepartmentDto departmentdto;


}
