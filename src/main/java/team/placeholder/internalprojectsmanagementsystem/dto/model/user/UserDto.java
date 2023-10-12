package team.placeholder.internalprojectsmanagementsystem.dto.model.user;

import lombok.Getter;
import lombok.Setter;
import team.placeholder.internalprojectsmanagementsystem.model.user.userenums.Role;

@Getter
@Setter
public class UserDto {
    private long id;
    private String name,email,password,confirmPassword;
    private Role role;
}
