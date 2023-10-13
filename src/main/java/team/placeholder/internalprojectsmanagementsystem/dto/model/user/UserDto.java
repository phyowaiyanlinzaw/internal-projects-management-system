package team.placeholder.internalprojectsmanagementsystem.dto.model.user;

import lombok.Getter;
import lombok.Setter;
import team.placeholder.internalprojectsmanagementsystem.model.department.Department;
import team.placeholder.internalprojectsmanagementsystem.model.user.User;
import team.placeholder.internalprojectsmanagementsystem.model.user.userenums.Role;

@Getter
@Setter
public class UserDto {
    private long id;
    private String name,email,password;
    private Role role;
    private Department department;


    public static UserDto fromUser(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        userDto.setRole(user.getRole());
        userDto.setDepartment(user.getDepartment());
        return userDto;
    }
}
