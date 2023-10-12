package team.placeholder.internalprojectsmanagementsystem.controller.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import team.placeholder.internalprojectsmanagementsystem.dto.model.user.UserDto;
import team.placeholder.internalprojectsmanagementsystem.service.user.UserService;

@RestController
@RequestMapping("/api/user/")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("{id}")
    public ResponseEntity getUserById(@PathVariable Long id) {
        List<UserDto> users = userService.getUserById(id);
        if(users.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(users);
    }

    @PostMapping("create")
    public String create (@RequestBody String user)


}
