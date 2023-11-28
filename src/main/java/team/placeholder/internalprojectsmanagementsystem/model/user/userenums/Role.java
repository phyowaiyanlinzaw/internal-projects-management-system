package team.placeholder.internalprojectsmanagementsystem.model.user.userenums;

import java.util.stream.Stream;

public enum Role {

    PMO, SDQC, DEPARTMENT_HEAD, PROJECT_MANAGER, EMPLOYEE, CONTRACT, FOC;

    public static Stream<Role> stream() {
        return Stream.of(Role.values());
    }
}
