package team.placeholder.internalprojectsmanagementsystem.controller.api;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import team.placeholder.internalprojectsmanagementsystem.dto.model.user.UserDto;
import team.placeholder.internalprojectsmanagementsystem.dto.uidto.AvailableUserDto;
import team.placeholder.internalprojectsmanagementsystem.dto.uidto.RegisterEmployeeDto;
import team.placeholder.internalprojectsmanagementsystem.dto.uidto.UserUIDto;
import team.placeholder.internalprojectsmanagementsystem.model.project.AvailableUser;
import team.placeholder.internalprojectsmanagementsystem.model.user.userenums.Role;
import team.placeholder.internalprojectsmanagementsystem.repository.project.AvailableUserRepo;
import team.placeholder.internalprojectsmanagementsystem.service.FakerService;
import team.placeholder.internalprojectsmanagementsystem.service.impl.department.DepartmentServiceImpl;
import team.placeholder.internalprojectsmanagementsystem.service.impl.project.AESImpl;
import team.placeholder.internalprojectsmanagementsystem.service.impl.user.UserServiceImpl;
import team.placeholder.internalprojectsmanagementsystem.service.project.AvailableEmployeeService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/user/")
@AllArgsConstructor
public class UserController {
    private final UserServiceImpl userService;
    private final FakerService fakerService;
    private final DepartmentServiceImpl departmentService;
    private final AvailableUserRepo availableEmployeeService;
    private final ModelMapper modalMapper;

    @GetMapping("/report/list")
    public List<UserDto> getUsers() {


        return userService.getAllUsers();
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

    @GetMapping("send-otp/{email}")
    public ResponseEntity<String> sendEmail(@PathVariable String email) {
        UserDto user = userService.sendOtp(email);
        if (user == null) {
            return ResponseEntity.badRequest().body("User not found");
        }
        return ResponseEntity.ok("Email sent successfully");
    }

    @PostMapping("confirm-otp/{email}/{otp}")
    public ResponseEntity<String> confirmOtp(@PathVariable String email,@PathVariable String otp){
        boolean isConfirmed = userService.confirmOtp(email,otp);
        if (!isConfirmed) {
            return ResponseEntity.badRequest().body("OTP is not confirmed");
        }
        return ResponseEntity.ok("OTP has been confirmed.");
    }

    @PostMapping("reset-password/{email}/{newPassword}")
    public ResponseEntity<String> resetPassword(@PathVariable String email, @PathVariable String newPassword) {

        UserDto user = userService.resetPassword(email, newPassword);
        if (user == null) {
            return ResponseEntity.badRequest().body("User not found");
        }
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

    @PutMapping("change-password")
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
        List<UserDto> userDto = userService.getAllUsers();
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @GetMapping("employee/list")
    public ResponseEntity<List<UserDto>> getAllEmployeesExceptPMOAndSDQC() {
        return ResponseEntity.ok(userService.getAllEmployeesExceptPMOAndSDQC());
    }

    @GetMapping("list/role/{role}")
    public ResponseEntity<List<UserDto>> getAllUsersByRole(@PathVariable Role role) {
        return ResponseEntity.ok(userService.getAllByRole(role));
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

    @GetMapping("count/department/{departmentId}")
    public ResponseEntity<Long> countAllByDepartmentId(@PathVariable Long departmentId) {
        return ResponseEntity.ok(userService.countAllByDepartmentId(departmentId));
    }

    @PostMapping("change-username")
    public ResponseEntity<String> changeUsername(@RequestBody UserDto userDto) {
        userService.changeUsername(userDto);
        return ResponseEntity.ok("Username changed successfully");
    }


    @GetMapping("list/byId/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable long id) {
        UserDto user = userService.getUserById(id);
        if(user != null) {
            return ResponseEntity.ok(user);
        }else {
            return ResponseEntity.notFound().build();
        }
    }

//    @PutMapping("/update/{id}")
//    public ResponseEntity<UserDto> updateProject(@PathVariable long id, @RequestBody UserDto userDto) {
//
//        userDto.setId(id);
//        UserDto user = userService.updateUser(userDto);
//        if(user != null) {
//            System.out.println("User Update Successfully");
//            return ResponseEntity.ok(user);
//        }else{
//            System.out.println("Failed to update user");
//            return ResponseEntity.badRequest().body(null);
//        }
//    }

    @PutMapping("/update/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable long id, @RequestBody UserUIDto userDto) {
        userDto.setId(id);
        UserDto user = userService.updateUser(userDto);
        if(user != null) {
            System.out.println("User Update Successfully");
            return ResponseEntity.ok(user);
        }else{

            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/list/available/pmId/{pmId}")
    public ResponseEntity<List<AvailableUserDto>> getAllAvailableUsersByPMId(@PathVariable Long pmId) {

        List<UserDto> users = userService.getAllUsersByPMId(pmId);

        List<Long> idList = users.stream().map(UserDto::getId).toList();

        List<AvailableUser> availableUsers = availableEmployeeService.findByUserIdIn(idList);
        
        List<AvailableUserDto> availableUserDtos = new ArrayList<>();

        for (AvailableUser availableUser : availableUsers) {
            if (availableUser.isAvaliable()) {
                UserDto userDto = modalMapper.map(availableUser.getUser(), UserDto.class);
                AvailableUserDto availableUserDto = new AvailableUserDto(
                        availableUser.getId(),
                        userDto,
                        availableUser.isAvaliable()
                );
                availableUserDtos.add(availableUserDto);
            }
        }

        return new ResponseEntity<>(availableUserDtos, HttpStatus.OK);
    }

}
