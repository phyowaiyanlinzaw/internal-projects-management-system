package team.placeholder.internalprojectsmanagementsystem.dto.uidto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.scheduling.config.Task;
import team.placeholder.internalprojectsmanagementsystem.model.department.Department;
import team.placeholder.internalprojectsmanagementsystem.model.issue.Issue;
import team.placeholder.internalprojectsmanagementsystem.model.project.Project;
import team.placeholder.internalprojectsmanagementsystem.model.project.Review;
import team.placeholder.internalprojectsmanagementsystem.model.user.userenums.Role;


@Getter
@Setter
public class UrDto {
    private long id;
    private String name;

    private String email;

    private String password;

    private Role role;

    private Department department;

    private Project project;

    private Task task;

    private Issue issue;

    private Review review;

    private long departmentId;



}
