package team.placeholder.internalprojectsmanagementsystem.controller.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import team.placeholder.internalprojectsmanagementsystem.dto.model.user.UserDto;
import team.placeholder.internalprojectsmanagementsystem.model.user.User;
import team.placeholder.internalprojectsmanagementsystem.model.user.userenums.Role;
import team.placeholder.internalprojectsmanagementsystem.security.CustomerUserDetails;
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

    @GetMapping("/profile")
    public ResponseEntity<UserDto> getUserInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null) {
            return ResponseEntity.ok(userService.findByName(authentication.getName()));
        } else {
            return ResponseEntity.notFound().build();
        }
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
        System.out.println("project manager id" + data.getProjectManager().getId());
        try {
            UserDto user = userService.getUserById(data.getProjectManager().getId());
            data.setProjectManager(user);
            System.out.println(data);
            userService.registerUser(data);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

        return ResponseEntity.ok("Employee registered successfully");
    }

    @PostMapping("change-password")
    public ResponseEntity<String> changePassword(@RequestParam("oldPassword") String oldPassword, @RequestParam("newPassword") String newPassword) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            UserDto user = userService.findByName(authentication.getName());
            if (user == null) {
                return ResponseEntity.badRequest().body("User not found");
            }
            //check if old password is correct
            if (!new BCryptPasswordEncoder().matches(oldPassword, user.getPassword())) {
                return ResponseEntity.badRequest().body("Old password is incorrect");
            }
//set new password
            user.setPassword(new BCryptPasswordEncoder().encode(newPassword));
            userService.save(user);
            return ResponseEntity.ok("Password changed successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("list")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("list/{role}")
    public ResponseEntity<List<UserDto>> getAllUsersByRole(@PathVariable Role role) {
        return ResponseEntity.ok(userService.getAllByRole(role));
    }


}
