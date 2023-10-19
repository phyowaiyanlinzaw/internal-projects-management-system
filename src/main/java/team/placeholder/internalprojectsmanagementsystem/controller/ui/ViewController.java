package team.placeholder.internalprojectsmanagementsystem.controller.ui;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

    @GetMapping("/accessDenied")
    public String accessDenied(){
        return "404";
    }

    @GetMapping("/login")
    public String loginFormView(){
        return "login";
    }

    @GetMapping("/")
    public String home(){
        return "dashboard";
    }

    @GetMapping("/profile")
    public String profile(){
        return "profile"; //TODO:Change to Profile Actual View
    }

    @GetMapping("/issues")
    public String issues(){
        return "issues";//TODO:Change to Issues View
    }

    @GetMapping("/project")
    public String task(){
        return "project";//TODO:Change to Task View
    }

    @GetMapping("/project/all")
    public String project(){
        return "projects";
    }

    @GetMapping("/department")
    public String department(){
        return "department";
    }

    @GetMapping("/dashboard")
    public String dashboard(){
        return "redirect:/";
    }


}
