package team.placeholder.internalprojectsmanagementsystem.controller.api;

import lombok.AllArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team.placeholder.internalprojectsmanagementsystem.dto.model.user.UserDto;
import team.placeholder.internalprojectsmanagementsystem.model.user.userenums.Role;
import team.placeholder.internalprojectsmanagementsystem.service.impl.user.UserServiceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/currentuser")
@AllArgsConstructor
public class LoginUser {

    UserServiceImpl userService;

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

}