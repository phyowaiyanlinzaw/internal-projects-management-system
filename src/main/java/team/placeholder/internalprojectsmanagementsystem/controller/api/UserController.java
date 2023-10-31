package team.placeholder.internalprojectsmanagementsystem.controller.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import team.placeholder.internalprojectsmanagementsystem.dto.model.user.UserDto;
import team.placeholder.internalprojectsmanagementsystem.service.impl.user.UserServiceImpl;
import team.placeholder.internalprojectsmanagementsystem.service.user.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/user/")
public class UserController {

    private final UserServiceImpl userService;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping("reset-password/{email}")
    public ResponseEntity<String> sendEmail(@PathVariable String email) {
        userService.resetPassword(email);
        return ResponseEntity.ok("Email sent successfully");
    }

    @PostMapping("reset-password/{email}/{newPassword}")
    public ResponseEntity<String> resetPassword(@PathVariable String email, @PathVariable String newPassword) {

        UserDto user = userService.getUserByEmail(email);

        if (user == null) {
            return ResponseEntity.badRequest().body("User not found");
        }
        System.out.println(user.getName());
        user.setPassword(newPassword);
        userService.save(user);
        return ResponseEntity.ok("Password reset successfully");
    }

    @PostMapping("register-employee")
    public ResponseEntity<String> registerEmployee(@RequestBody UserDto data) {
        try{
            userService.registerUser(data);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

        return ResponseEntity.ok("Employee registered successfully");
    }

    @GetMapping("list")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }


}
