package team.placeholder.internalprojectsmanagementsystem.dto.mapper.user;

import team.placeholder.internalprojectsmanagementsystem.dto.mapper.department.DepartmentMapper;
import team.placeholder.internalprojectsmanagementsystem.dto.model.user.UserDto;
import team.placeholder.internalprojectsmanagementsystem.model.user.User;

public class UserMapper {
    public static UserDto toUserDto(User user) {
        if (user == null) {
            return null;
        }
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        userDto.setRole(user.getRole());

        // Convert Department to DepartmentDto
        userDto.setDepartmentdto(DepartmentMapper.toDepartmentDto(user.getDepartment()));

        return userDto;
    }

    public static User toUser(UserDto userDto) {
        if (userDto == null) {
            return null;
        }
        User user = new User();
        user.setId(userDto.getId());
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setRole(userDto.getRole());

        // Convert DepartmentDto to Department using DepartmentMapper
        if (userDto.getDepartmentdto() != null) {
            user.setDepartment(DepartmentMapper.toDepartment(userDto.getDepartmentdto()));
        } else {
            user.setDepartment(null); // or handle this case as needed
        }

        return user;
    }



}
