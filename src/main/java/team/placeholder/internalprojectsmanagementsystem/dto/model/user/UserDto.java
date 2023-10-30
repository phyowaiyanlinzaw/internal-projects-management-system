package team.placeholder.internalprojectsmanagementsystem.dto.model.user;

import lombok.Getter;
import lombok.Setter;
import team.placeholder.internalprojectsmanagementsystem.dto.model.department.DepartmentDto;
import team.placeholder.internalprojectsmanagementsystem.model.user.userenums.Role;

@Getter
@Setter
public class UserDto {
    private long id;
    private String name,email,password;
    private Role role;

    private DepartmentDto departmentdto;

    public DepartmentDto getDepartmentdto() {
        return departmentdto;
    }

    public void setDepartmentdto(DepartmentDto departmentdto) {
        this.departmentdto = departmentdto;
    }

}
