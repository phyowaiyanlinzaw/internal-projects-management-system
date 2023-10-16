package team.placeholder.internalprojectsmanagementsystem.controller.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import team.placeholder.internalprojectsmanagementsystem.dto.model.user.UserDto;
import team.placeholder.internalprojectsmanagementsystem.service.impl.user.UserServiceImpl;


import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user/")
public class UserController {

    private final UserServiceImpl userService;


    @GetMapping("lists")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> userDtos = userService.getAllUsers();
        return new ResponseEntity<>(userDtos, HttpStatus.OK);
    }

    @PostMapping("save")
    public ResponseEntity<String> save(@RequestBody UserDto userDto) {
        UserDto savedUser = userService.save(userDto);
        if (savedUser != null) {
            return ResponseEntity.ok("User saved successfully");
        } else {
            return ResponseEntity.badRequest().body("Failed to save user");
        }
    }


    @GetMapping("/lists/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable long id) {
        UserDto user = userService.getUserById((int) id);

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
