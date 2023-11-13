package team.placeholder.internalprojectsmanagementsystem.controller.api;

import ch.qos.logback.classic.Logger;
import groovy.util.logging.Slf4j;
import lombok.AllArgsConstructor;
import org.apache.coyote.Response;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team.placeholder.internalprojectsmanagementsystem.dto.model.user.UserDto;
import team.placeholder.internalprojectsmanagementsystem.model.project.AvailableUser;
import team.placeholder.internalprojectsmanagementsystem.model.user.User;
import team.placeholder.internalprojectsmanagementsystem.model.user.userenums.Role;
import team.placeholder.internalprojectsmanagementsystem.repository.project.AvailableUserRepo;
import team.placeholder.internalprojectsmanagementsystem.repository.user.UserRepository;
import team.placeholder.internalprojectsmanagementsystem.service.impl.user.UserServiceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/currentuser")
@AllArgsConstructor
@Slf4j
public class LoginUser {

    UserServiceImpl userService;
    UserRepository userRepository;
    AvailableUserRepo availableUserRepo;
    ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<Map<String, Object>> loginUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDto userDto = userService.getUserByEmail(authentication.getName());

        Map<String, Object> response = new HashMap<>();
        response.put("currentUser", userDto);
        System.out.println(userDto.getRole());

        if(userDto.getRole() == Role.PROJECT_MANAGER) {
            List<UserDto> employeeWrokUnderPm = userService.getAllUsersByPMId(userDto.getId());
            System.out.println("emplyee work under pm" + employeeWrokUnderPm);
            response.put("employees", employeeWrokUnderPm);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/available/employees")
    public ResponseEntity<List<UserDto>> getAvailableEmployees() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDto userDto = userService.getUserByEmail(authentication.getName());

        if (userDto.getRole() == Role.PROJECT_MANAGER) {

            List<User> employeeWrokUnderPm = userRepository.findAllByProjectManagerId(userDto.getId());
            List<Long> employeeIds = employeeWrokUnderPm.stream()
                    .map(User::getId)
                    .toList();
            List<AvailableUser> userAvailabilities = availableUserRepo.findByUserIdIn(employeeIds);

            List<UserDto> userDtos = new ArrayList<>();

            for (User employee : employeeWrokUnderPm) {
                // Find UserAvailability for the current employee
                AvailableUser availability = userAvailabilities.stream()
                        .filter(userAvailability -> userAvailability.getUser().getId() == employee.getId())
                        .findFirst()
                        .orElse(null);

                if (availability != null && availability.isAvaliable()) {
                    // The employee is available
                    userDtos.add(modelMapper.map(employee, UserDto.class));
                    System.out.println("Employee " + employee.getId() + " is available.");
                } else {
                    // The employee is not available or availability information not found
                    System.out.println("Employee " + employee.getId() + " is not available.");
                }

//        return new ResponseEntity<>(employeeWrokUnderPm, HttpStatus.OK);
            }
            return ResponseEntity.ok(userDtos);
        }
        return ResponseEntity.badRequest().build();
    }

}