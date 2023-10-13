package team.placeholder.internalprojectsmanagementsystem.controller.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import team.placeholder.internalprojectsmanagementsystem.dto.model.user.UserDto;
import team.placeholder.internalprojectsmanagementsystem.model.user.User;
import team.placeholder.internalprojectsmanagementsystem.repository.user.UserRepository;
import team.placeholder.internalprojectsmanagementsystem.service.user.UserService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user/")
public class UserController {

   // private final UserService userService;


    private final UserRepository userRepository;


    @GetMapping("allUsers")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userRepository.findAll();
        return new ResponseEntity<> (users, HttpStatus.OK);

    }

//    @PostMapping("create")
//    public ResponseEntity<String> save(@RequestBody UserDto user) {
//        userRepository.save(user);
//        return ResponseEntity.ok("User saved successfully");
//    }


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
