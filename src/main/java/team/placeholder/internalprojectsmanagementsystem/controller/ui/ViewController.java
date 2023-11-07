package team.placeholder.internalprojectsmanagementsystem.controller.ui;

import javax.swing.text.View;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import team.placeholder.internalprojectsmanagementsystem.dto.model.user.UserDto;
import team.placeholder.internalprojectsmanagementsystem.model.project.Tasks;
import team.placeholder.internalprojectsmanagementsystem.service.impl.project.TaskServiceImpl;
import team.placeholder.internalprojectsmanagementsystem.service.impl.user.UserServiceImpl;

@Controller
public class ViewController {

    UserServiceImpl userServiceImpl;
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    TaskServiceImpl taskServiceImpl;

    public ViewController(UserServiceImpl userServiceImpl, TaskServiceImpl taskServiceImpl) {
        this.userServiceImpl = userServiceImpl;
        this.taskServiceImpl = taskServiceImpl;
    }

    @GetMapping("/accessDenied")
    public String accessDenied(){
        return "404";
    }

    @GetMapping("/login")
    public String loginFormView(){

        if(authentication != null && authentication.isAuthenticated()) {
            return "/dashboard";
        }

        return "login";
    }

    @GetMapping("/dashboard")
    public String home(HttpSession session){

        if (authentication != null && authentication.isAuthenticated()) {

            String loginEmail = authentication.getName();

            UserDto userDto = userServiceImpl.getUserByEmail(loginEmail);

            session.setAttribute("login-user-id", userDto.getId());
            session.setAttribute("loing-user-role", userDto.getRole());
            session.setAttribute("login-user-dp-id", userDto.getDepartmentdto().getId());
            
            if(userDto.getProjectManager() != null) {
                session.setAttribute("login-user-pm-id", userDto);
            }

        }

        return "dashboard";
    }

    @GetMapping("/profile")
    public String profile(){
        return "profile";
    }

    @GetMapping("/issues")
    public String issues(){
        return "issues";
    }

    @GetMapping("/project/{projectId}")
    public String task(@PathVariable("projectId") Long projectId, Model model){
        model.addAttribute("tasks", taskServiceImpl.getTasksByProjectId(projectId));
        model.addAttribute("projectId", projectId);
        return "project";
    }

    @GetMapping("/project/all")
    public String project(){
        return "projects";
    }


    @GetMapping("/")
    public String dashboard(){
        return "redirect:/dashboard";
    }

    @GetMapping("/department")
    public String department() { return "department"; }

    @GetMapping("/reset-password")
    public String resetPassword() {return "password-reset";}

    @GetMapping("/issue")
    public String issue() {return "issue";}

    @GetMapping("/employees")
    public String employees() {return "employees";}

    @GetMapping("/issuesss")
    public String isuesss(){
        return "issuesss";
    }



}
