package team.placeholder.internalprojectsmanagementsystem.dto.uidto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterEmployeeDto {

    private String name;
    private String email;
    private String password;
    private String role;
    private long projectManagerId;
    private long departmentId;
}
