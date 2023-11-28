package team.placeholder.internalprojectsmanagementsystem.model.user.userenums;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RoleTest {

    @Test
    public void testEnumValues() {
        for (Role value : Role.values()) {
            assertNotNull(value);
        }
    }

    @Test
    public void testEnumStream() {
        List<Role> roles = Role.stream().toList();
        List<Role> expectedRoles = Arrays.asList(Role.PMO, Role.SDQC, Role.DEPARTMENT_HEAD, Role.PROJECT_MANAGER, Role.EMPLOYEE, Role.CONTRACT, Role.FOC);
        assertEquals(expectedRoles, roles);
    }

}