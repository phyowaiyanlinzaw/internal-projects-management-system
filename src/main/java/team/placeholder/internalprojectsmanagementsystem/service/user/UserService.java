package team.placeholder.internalprojectsmanagementsystem.service.user;


import team.placeholder.internalprojectsmanagementsystem.dto.model.user.UserDto;

import java.util.List;

import org.springframework.stereotype.Service;
import team.placeholder.internalprojectsmanagementsystem.dto.uidto.RegisterEmployeeDto;
import team.placeholder.internalprojectsmanagementsystem.dto.uidto.UserUIDto;
import team.placeholder.internalprojectsmanagementsystem.model.user.userenums.Role;

@Service
public interface UserService {
    UserDto save(UserDto userDto);
    UserDto changePassword(String email, String oldPassword, String newPassword);
    UserDto getUserById(long id);
    UserDto getUserByEmail(String email);
    List<UserDto> getAllUsers();
    UserDto updateProfile(UserDto userDto);
    UserDto sendOtp(String email);
    boolean confirmOtp(String email,String otp);
    UserDto resetPassword(String email, String newPassword);
    void sendEmail(String to, String subject, String text);
    UserDto registerUser(RegisterEmployeeDto registerEmployeeDto);
    UserDto findByName(String name);
    List<UserDto> getAllByRole(Role role);
    Long countAllByRole(Role role);
    Long countAll();
    List<UserDto> getAllUsersByPMId(Long id);
    UserDto getUserByDepartmentIdAndRole(Long departmentId, Role role);
    Long countAllByDepartmentId(Long departmentId);
    List<UserDto> getAllEmployeesExceptPMOAndSDQC();
    List<UserDto> getEmployeeByProjectId(Long projectId);
    void changeUsername(UserDto userDto);
    UserDto changeStatus(long id, boolean status);
    UserDto updateUser(UserUIDto userDto);
}
