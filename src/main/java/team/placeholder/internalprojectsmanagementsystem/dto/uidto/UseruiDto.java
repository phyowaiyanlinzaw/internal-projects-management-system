package team.placeholder.internalprojectsmanagementsystem.dto.uidto;

import lombok.Getter;
import lombok.Setter;
import team.placeholder.internalprojectsmanagementsystem.model.user.userenums.Role;


@Getter
@Setter
public class UseruiDto {
    private long id;
    private String name;
    private String email;

    String password;
    private Role role;
    private boolean enabled;

    private long departmentId;

    private long projectManagerId;

    private long[] projectsByProjectManagerIds;
}
