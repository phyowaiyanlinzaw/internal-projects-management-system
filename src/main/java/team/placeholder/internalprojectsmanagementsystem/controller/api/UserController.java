package team.placeholder.internalprojectsmanagementsystem.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import team.placeholder.internalprojectsmanagementsystem.dto.model.user.UserDto;
import team.placeholder.internalprojectsmanagementsystem.model.user.User;
import team.placeholder.internalprojectsmanagementsystem.repository.user.UserRepository;
import team.placeholder.internalprojectsmanagementsystem.service.user.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/user/")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("allUsers")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userRepository.selectAllUser();
        return new ResponseEntity<> (users, HttpStatus.OK);

    }


}
