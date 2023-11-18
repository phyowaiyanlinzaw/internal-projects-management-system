package team.placeholder.internalprojectsmanagementsystem.dto.model.user;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import team.placeholder.internalprojectsmanagementsystem.dto.model.department.DepartmentDto;
import team.placeholder.internalprojectsmanagementsystem.dto.model.project.ProjectDto;
import team.placeholder.internalprojectsmanagementsystem.model.project.Project;
import team.placeholder.internalprojectsmanagementsystem.model.user.userenums.Role;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class UserDto {
    private long id;
    private String name;
    private String email;
    @JsonIgnore
    String password;
    private Role role;
    private boolean enabled;

    @JsonProperty("department")
    private DepartmentDto departmentdto;

//    @JsonBackReference
//    @JsonProperty("project_manager")
    private UserDto projectManager;

    private List<ProjectDto> projectsByProjectManager;

    private Set<ProjectDto> projectsByUsers;


    public UserDto(long id) {
        this.id = id;
    }
}
