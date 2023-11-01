package team.placeholder.internalprojectsmanagementsystem.service.user;


import team.placeholder.internalprojectsmanagementsystem.dto.model.user.UserDto;
import team.placeholder.internalprojectsmanagementsystem.model.user.User;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface UserService {
    UserDto save(UserDto userDto);

    UserDto getUserById(long id);

    UserDto getUserByEmail(String email);

    List<UserDto> getAllUsers();

    UserDto updateProfile(UserDto userDto);

    UserDto changePassword(UserDto userDto, String newPassword);

    void sendEmail(String to);

    long getMemberCount(long id);




}
