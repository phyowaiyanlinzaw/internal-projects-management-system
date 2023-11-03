package team.placeholder.internalprojectsmanagementsystem.service.user;


import team.placeholder.internalprojectsmanagementsystem.dto.model.user.UserDto;

import java.util.List;

import org.springframework.stereotype.Service;
import team.placeholder.internalprojectsmanagementsystem.model.user.User;
import team.placeholder.internalprojectsmanagementsystem.model.user.userenums.Role;

@Service
public interface UserService {
    UserDto save(UserDto userDto);

    UserDto getUserById(long id);

    UserDto getUserByEmail(String email);

    List<UserDto> getAllUsers();

    UserDto updateProfile(UserDto userDto);

    void resetPassword(String email);

    void sendEmail(String to, String subject, String text);

    UserDto registerUser(UserDto userDto);

    UserDto findByName(String name);

    List<UserDto> getAllByRole(Role role);

    Long countAllByRole(Role role);

    Long countAll();

    List<UserDto> getAllUsersByPMId(Long id);

    UserDto getUserByDepartmentIdAndRole(Long departmentId, Role role);

    Long countAllByDepartmentId(Long departmentId);

    List<UserDto> getAllUsersByProjectId(Long projectId);
}
