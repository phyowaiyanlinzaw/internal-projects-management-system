package team.placeholder.internalprojectsmanagementsystem.controller.ui;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

    @GetMapping("/accessDenied")
    public String accessDenied(){
        return "dashboard";
    }

    @GetMapping("/login")
    public String loginFormView(){
        return "login";
    }

    @GetMapping("/")
    public String home(){
        return "dashboard";
    }

    @GetMapping("/department")
    public String department(){
        return "department"; //TODO:Change to Department Actual View
    }

    @GetMapping("/profile")
    public String profile(){
        return "login"; //TODO:Change to Profile Actual View
    }

    @GetMapping("/issues")
    public String issues(){
        return "login";//TODO:Change to Issues View
    }

    @GetMapping("/task")
    public String task(){
        return "login";//TODO:Change to Task View
    }

    @GetMapping("/project/all")
    public String project(){
        return "projects";
    }

    @GetMapping("/department")
    public String department(){
        return "department";
    }

    @GetMapping("/projects")
    public String projects(){
        return "project";
    }

    @GetMapping("/dashboard")
    public String dashboard(){
        return "redirect:/";
    }


}
