package team.placeholder.internalprojectsmanagementsystem.dto.model.department;

import lombok.Getter;
import lombok.Setter;
import team.placeholder.internalprojectsmanagementsystem.dto.model.project.ProjectDto;
import team.placeholder.internalprojectsmanagementsystem.dto.model.user.UserDto;

import java.io.Serializable;
import java.util.List;
import lombok.ToString;
import team.placeholder.internalprojectsmanagementsystem.model.project.Project;

@Getter
@Setter
public class DepartmentDto implements Serializable {
    private long id;
    private String name;
    private List<UserDto> users;
    private List<ProjectDto> projects;

    public DepartmentDto(){

    }


    public DepartmentDto(long id) {
        this.id = id;
    }

}
