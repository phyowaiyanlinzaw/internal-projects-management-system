package team.placeholder.internalprojectsmanagementsystem.controller.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import team.placeholder.internalprojectsmanagementsystem.dto.model.user.UserDto;
import team.placeholder.internalprojectsmanagementsystem.model.user.User;
import team.placeholder.internalprojectsmanagementsystem.repository.user.UserRepository;
import team.placeholder.internalprojectsmanagementsystem.service.impl.user.UserServiceImpl;
import team.placeholder.internalprojectsmanagementsystem.service.user.UserService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user/")
public class UserController {


    private final UserRepository userRepository;

    private final UserServiceImpl userService;


    @GetMapping("allUsers")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<User> users = userRepository.findAll();
        List<UserDto> userDtos = users.stream()
                .map(UserDto::fromUser)
                .collect(Collectors.toList());
        return new ResponseEntity<>(userDtos, HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody UserDto userDto) {
        userService.save(userDto);
        return ResponseEntity.ok("User saved successfully");
    }


//    @GetMapping("/list/{id}")
//    public ResponseEntity<UserDto> getUserById(@PathVariable int id) {
//        UserDto user = userService.getUserById(id);
//
//        if (user != null) {
//            return ResponseEntity.ok(user);
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }



}
