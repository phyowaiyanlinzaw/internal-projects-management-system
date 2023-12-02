package team.placeholder.internalprojectsmanagementsystem.controller.ui;

import jakarta.servlet.http.HttpSession;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.config.Task;
import org.springframework.security.core.Authentication;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.ui.Model;
import team.placeholder.internalprojectsmanagementsystem.dto.model.department.DepartmentDto;
import team.placeholder.internalprojectsmanagementsystem.dto.model.user.UserDto;
import team.placeholder.internalprojectsmanagementsystem.model.project.Project;
import team.placeholder.internalprojectsmanagementsystem.model.project.Tasks;
import team.placeholder.internalprojectsmanagementsystem.model.user.userenums.Role;
import team.placeholder.internalprojectsmanagementsystem.service.impl.project.ProjectServiceImpl;
import team.placeholder.internalprojectsmanagementsystem.service.impl.project.TaskServiceImpl;
import team.placeholder.internalprojectsmanagementsystem.service.impl.user.UserServiceImpl;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(MockitoExtension.class)
class ViewControllerTest {

    @Mock
    private UserServiceImpl userServiceImpl;

    @Mock
    private TaskServiceImpl taskServiceImpl;

    @Mock
    private ProjectServiceImpl projectServiceImpl;

    @InjectMocks
    private ViewController viewController;





    @Test
    void testHome() {
        // Arrange
        HttpSession session = Mockito.mock(HttpSession.class);
        Model model = Mockito.mock(Model.class);

        // Mock authentication
        Authentication authentication = Mockito.mock(Authentication.class);
        Mockito.when(authentication.getName()).thenReturn("test@example.com");

        // Set authentication in the controller
        viewController.authentication = authentication;

        // Assuming you have a user with a known email
        String userEmail = "test@example.com";
        UserDto mockUserDto = new UserDto();
        mockUserDto.setId(1L);
        mockUserDto.setRole(Role.PMO);
        mockUserDto.setDepartmentdto(new DepartmentDto());
        mockUserDto.setProjectManager(null);

        Mockito.when(userServiceImpl.getUserByEmail(userEmail)).thenReturn(mockUserDto);
        Mockito.when(session.getAttribute("login-user-id")).thenReturn(1L);
        Mockito.when(session.getAttribute("loing-user-role")).thenReturn("USER");
        Mockito.when(session.getAttribute("login-user-dp-id")).thenReturn(mockUserDto.getDepartmentdto().getId());
        Mockito.when(session.getAttribute("login-user-pm-id")).thenReturn(null);

        // Mock the behavior of taskServiceImpl.getTasksByProjectId
        Mockito.when(taskServiceImpl.getTasksByProjectId(Mockito.anyLong())).thenReturn(Collections.emptyList());

        // Act
        String result = viewController.home(session);

        // Assert
        Mockito.verify(model).addAttribute("tasks", Collections.emptyList());
        // Verify that other expected interactions occur
        Mockito.verify(model).addAttribute("projectId", 1L);
        Mockito.verify(model).addAttribute("project", null);

        // Add more assertions based on your specific logic and expectations

        // Example assertion for demonstration
        assert (result.equals("dashboard"));
    }

    @Test
    void testProfile() {
        // Arrange
        Model model = Mockito.mock(Model.class);

        // Act
        String result = viewController.profile();

        // Assert
        Mockito.verifyNoInteractions(userServiceImpl, taskServiceImpl, projectServiceImpl); // Ensure no interactions with other services
        assert (result.equals("profile"));
    }



    @Test
    void testProject() {
        // Arrange
        Model model = Mockito.mock(Model.class);

        // Act
        String result = viewController.project();

        // Assert
        Mockito.verifyNoInteractions(userServiceImpl, taskServiceImpl, projectServiceImpl); // Ensure no interactions with other services
        assert(result.equals("projects"));
    }
    @Test
    void testDashboard() {
        // Act
        String result = viewController.dashboard();

        // Assert
        assertEquals("redirect:/dashboard", result);
        Mockito.verifyNoInteractions(userServiceImpl, taskServiceImpl, projectServiceImpl); // Ensure no interactions with other services
    }

    @Test
    @WithMockUser(roles = {"SDQC"})
    void testDepartmentWithSDQCRole() {
        // Act
        String result = viewController.department();

        // Assert
        assertEquals("department", result);
        Mockito.verifyNoInteractions(userServiceImpl, taskServiceImpl, projectServiceImpl); // Ensure no interactions with other services
    }

    @Test
    @WithMockUser(roles = {"SOME_OTHER_ROLE"})
    void testDepartmentWithoutRequiredRole() {
        // Act
        String result = viewController.department();

        // Assert
        assertEquals("accessDenied", result); // Assuming you have an accessDenied view for unauthorized access
        Mockito.verifyNoInteractions(userServiceImpl, taskServiceImpl, projectServiceImpl); // Ensure no interactions with other services
    }
    @Test
    void testResetPassword() {
        // Act
        String result = viewController.resetPassword();

        // Assert
        assertEquals("password-reset", result);
        Mockito.verifyNoInteractions(userServiceImpl, taskServiceImpl, projectServiceImpl); // Ensure no interactions with other services
    }


    @Test
    void testIssue() {
        // Act
        String result = viewController.issue();

        // Assert
        assertEquals("issue", result);
        Mockito.verifyNoInteractions(userServiceImpl, taskServiceImpl, projectServiceImpl); // Ensure no interactions with other services
    }

    @Test
    @WithMockUser(roles = {"PMO"})
    void testEmployeesWithPMORole() {
        // Act
        String result = viewController.employees();

        // Assert
        assertEquals("employees", result);
        Mockito.verifyNoInteractions(userServiceImpl, taskServiceImpl, projectServiceImpl); // Ensure no interactions with other services
    }

    @Test
    @WithMockUser(roles = {"SOME_OTHER_ROLE"})
    void testEmployeesWithoutRequiredRole() {
        // Act
        String result = viewController.employees();

        // Assert
        assertEquals("accessDenied", result); // Assuming you have an accessDenied view for unauthorized access
        Mockito.verifyNoInteractions(userServiceImpl, taskServiceImpl, projectServiceImpl); // Ensure no interactions with other services
    }

    @Test
    void testReportDepartment() {
        // Act
        String result = viewController.reportDepartment();

        // Assert
        assertEquals("issueTable", result);
        Mockito.verifyNoInteractions(userServiceImpl, taskServiceImpl, projectServiceImpl); // Ensure no interactions with other services
    }



}
