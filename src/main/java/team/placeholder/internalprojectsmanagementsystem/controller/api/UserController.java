package team.placeholder.internalprojectsmanagementsystem.controller.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import team.placeholder.internalprojectsmanagementsystem.dto.model.user.UserDto;
import team.placeholder.internalprojectsmanagementsystem.service.user.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/user/")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("lists")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> users = userService.getAllUsers();
        return new ResponseEntity<> (users, HttpStatus.OK);

    }

    @PostMapping("create")
    public ResponseEntity<String> save(@RequestBody UserDto user) {
        userService.save(user);
        return ResponseEntity.ok("User saved successfully");
    }


    @GetMapping("/list/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable int id) {
        UserDto user = userService.getUserById(id);

        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/lists/{email}")
    public ResponseEntity<UserDto> getUserByEmail(@PathVariable String email) {
        UserDto user = userService.getUserByEmail(email);

        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("update")
    public ResponseEntity<String> updateProfile(@RequestBody UserDto userDto) {
        UserDto updatedUser = userService.updateProfile(userDto);
        if (updatedUser != null) {
            return ResponseEntity.ok("User updated successfully");
        } else {
            return ResponseEntity.badRequest().body("Failed to update user");
        }
    }

    @PutMapping("changePassword")
    public ResponseEntity<String> changePassword(@RequestBody UserDto userDto, @RequestParam String newPassword) {
        UserDto updatedUser = userService.changePassword(userDto, newPassword);
        if (updatedUser != null) {
            return ResponseEntity.ok("Password changed successfully");
        } else {
            return ResponseEntity.badRequest().body("Failed to change password");
        }
    }




}
