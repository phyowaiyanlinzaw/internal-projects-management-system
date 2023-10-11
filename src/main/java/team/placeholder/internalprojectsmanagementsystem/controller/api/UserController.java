package team.placeholder.internalprojectsmanagementsystem.controller.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping("/")
    public String index() {
        return "Hello World!";
    }
}
