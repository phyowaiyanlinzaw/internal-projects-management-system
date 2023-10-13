package team.placeholder.internalprojectsmanagementsystem.service.user;


import team.placeholder.internalprojectsmanagementsystem.dto.model.user.UserDto;
import team.placeholder.internalprojectsmanagementsystem.model.user.User;

import java.util.List;

public interface UserService {
    //TODO: Implement User Service
    UserDto save(UserDto userDto);

    UserDto getUserById(int id);

    UserDto getUserByEmail(String email);

    List<User> getAllUsers();

    UserDto updateProfile(UserDto userDto);

    UserDto changePassword(UserDto userDto, String newPassword);






}
