package team.placeholder.internalprojectsmanagementsystem.controller.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import team.placeholder.internalprojectsmanagementsystem.dto.model.user.UserDto;
import team.placeholder.internalprojectsmanagementsystem.dto.uidto.RegisterEmployeeDto;
import team.placeholder.internalprojectsmanagementsystem.model.user.User;
import team.placeholder.internalprojectsmanagementsystem.model.user.userenums.Role;
import team.placeholder.internalprojectsmanagementsystem.security.CustomerUserDetails;
import team.placeholder.internalprojectsmanagementsystem.service.FakerService;
import team.placeholder.internalprojectsmanagementsystem.service.department.DepartmentService;
import team.placeholder.internalprojectsmanagementsystem.service.impl.department.DepartmentServiceImpl;
import team.placeholder.internalprojectsmanagementsystem.service.impl.user.UserServiceImpl;
import team.placeholder.internalprojectsmanagementsystem.service.user.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/user/")
public class UserController {

    private final UserServiceImpl userService;
    private final FakerService fakerService;
    private final DepartmentServiceImpl departmentService;

    public UserController(UserServiceImpl userService,DepartmentServiceImpl departmentService, FakerService fakerService) {
        this.userService = userService;
        this.departmentService=departmentService;
        this.fakerService = fakerService;
    }

    @GetMapping("/generate-fake-users/{count}")
    public ResponseEntity<String> generateFakeUsers(@PathVariable("count") int count) {
        fakerService.generateAndSaveFakeUsers(count);
        return ResponseEntity.ok("Fake users generated successfully");
    }

    @GetMapping("/profile")
    public ResponseEntity<UserDto> getUserInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null) {
            return ResponseEntity.ok(userService.getUserByEmail(authentication.getName()));
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
        user.setPassword(newPassword);
        userService.save(user);
        return ResponseEntity.ok("Password reset successfully");
    }

    @PostMapping("register-employee")
    public ResponseEntity<String> registerEmployee(@RequestBody RegisterEmployeeDto data) {

        UserDto user = userService.registerUser(data);
        if (user == null) {
            return ResponseEntity.badRequest().body("User not found");
        }

        return ResponseEntity.ok("Employee registered successfully");
    }

    @PostMapping("change-password")
    public ResponseEntity<String> changePassword(@RequestParam("oldPassword") String oldPassword, @RequestParam("newPassword") String newPassword) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null) {
            UserDto user = userService.changePassword(authentication.getName(), oldPassword, newPassword);
            if (user == null) {
                return ResponseEntity.badRequest().body("User not found");
            }
        } else {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok("Password changed successfully");
    }

    @PostMapping("/update/departmentId/{departmentId}")
    public ResponseEntity<String> updateDepartment(@PathVariable Long departmentId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            UserDto user = userService.getUserByEmail(authentication.getName());
            if (user == null) {
                return ResponseEntity.badRequest().body("User not found");
            }
            user.setDepartmentdto(departmentService.getDepartmentById(departmentId));
            userService.save(user);
            return ResponseEntity.ok("Department updated successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/update/employee/{id}/status/{status}")
    public ResponseEntity<String> updateEmployeeStatus(@PathVariable Long id, @PathVariable boolean status) {
        UserDto user = userService.changeStatus(id, status);
        if (user == null) {
            return ResponseEntity.badRequest().body("User not found");
        }
        return ResponseEntity.ok("Employee status updated successfully");
    }

    @GetMapping("/get/id/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        UserDto user = userService.getUserById(id);
        if (user == null) {
            return ResponseEntity.badRequest().body(null);
        }
        return ResponseEntity.ok(user);
    }

    @GetMapping("/get/email/{email}")
    public ResponseEntity<UserDto> getUserByEmail(@PathVariable String email) {
        UserDto user = userService.getUserByEmail(email);
        if (user == null) {
            return ResponseEntity.badRequest().body(null);
        }
        return ResponseEntity.ok(user);
    }

    @GetMapping("list")
    public ResponseEntity<List<UserDto>> getAllUsers() {

        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("employee/list")
    public ResponseEntity<List<UserDto>> getAllEmployeesExceptPMOAndSDQC() {
        return ResponseEntity.ok(userService.getAllEmployeesExceptPMOAndSDQC());
    }

    @GetMapping("list/role/{role}")
    public ResponseEntity<List<UserDto>> getAllUsersByRole(@PathVariable Role role) {
        return ResponseEntity.ok(userService.getAllByRole(role));
    }

    @GetMapping("list/departmentId/{departmentId}")
    public ResponseEntity<List<UserDto>> getAllUsersByDepartmentId(@PathVariable Long departmentId) {
        return null;
    }

    @GetMapping("list/projectManagerId/{projectManagerId}")
    public ResponseEntity<List<UserDto>> getAllUsersByProjectManagerId(@PathVariable Long projectManagerId) {
        return ResponseEntity.ok(userService.getAllUsersByPMId(projectManagerId));
    }

    @GetMapping("list/projectId/{projectId}")
    public ResponseEntity<List<UserDto>> getAllUsersByProjectId(@PathVariable Long projectId) {
        return ResponseEntity.ok(userService.getEmployeeByProjectId(projectId));
    }

    @GetMapping("count/role/{role}")
    public ResponseEntity<Long> countAllByRole(@PathVariable Role role) {
        return ResponseEntity.ok(userService.countAllByRole(role));
    }

    @GetMapping("count/all")
    public ResponseEntity<Long> countAll() {
        return ResponseEntity.ok(userService.countAll());
    }

    @GetMapping("role/{role}/departmentId/{departmentId}")
    public ResponseEntity<UserDto> getUserByDepartmentIdAndRole(@PathVariable Role role, @PathVariable Long departmentId) {
        return ResponseEntity.ok(userService.getUserByDepartmentIdAndRole(departmentId, role));
    }

    @GetMapping("count/departmentId/{departmentId}")
    public ResponseEntity<Long> countAllByDepartmentId(@PathVariable Long departmentId) {
        return ResponseEntity.ok(userService.countAllByDepartmentId(departmentId));
    }

    @PostMapping("change-username")
    public ResponseEntity<String> changeUsername(@RequestBody UserDto userDto) {
        userService.changeUsername(userDto);
        return ResponseEntity.ok("Username changed successfully");
    }


}
