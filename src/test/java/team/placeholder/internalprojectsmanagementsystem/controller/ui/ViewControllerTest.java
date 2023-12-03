package team.placeholder.internalprojectsmanagementsystem.controller.ui;

import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import team.placeholder.internalprojectsmanagementsystem.dto.model.department.DepartmentDto;
import team.placeholder.internalprojectsmanagementsystem.dto.model.project.ProjectDto;
import team.placeholder.internalprojectsmanagementsystem.dto.model.project.TasksDto;
import team.placeholder.internalprojectsmanagementsystem.dto.model.user.UserDto;
import team.placeholder.internalprojectsmanagementsystem.model.user.userenums.Role;
import team.placeholder.internalprojectsmanagementsystem.service.impl.project.ProjectServiceImpl;
import team.placeholder.internalprojectsmanagementsystem.service.impl.project.TaskServiceImpl;
import team.placeholder.internalprojectsmanagementsystem.service.impl.user.UserServiceImpl;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


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
    void testTaskWhenValidProjectIdThenReturnProjectAndAddAttributesToModel() {
        // Given
        Long projectId = 1L;
        List<TasksDto> tasks = new ArrayList<>();
        ProjectDto project = new ProjectDto();
        when(taskServiceImpl.getTasksByProjectId(projectId)).thenReturn(tasks);
        when(projectServiceImpl.getProjectById(projectId)).thenReturn(project);
        Model model = mock(Model.class);

        // When
        String result = viewController.task(projectId, model);

        // Then
        assertEquals("project", result);
        verify(model).addAttribute("tasks", tasks);
        verify(model).addAttribute("projectId", projectId);
        verify(model).addAttribute("project", project);
        verifyNoMoreInteractions(model);
    }


    @Test
    void homeTest() {
        // Mock Authentication
        Authentication authentication = Mockito.mock(Authentication.class);
        when(authentication.isAuthenticated()).thenReturn(true);
        when(authentication.getName()).thenReturn("test@example.com");

        // Mock UserDto
        UserDto userDto = new UserDto();
        userDto.setId(1L);
        userDto.setRole(Role.PMO); // Assuming Role.PMO is the correct enum value
        // Mock DepartmentDto
        DepartmentDto departmentDto = new DepartmentDto();
        departmentDto.setId(100L);
        userDto.setDepartmentdto(departmentDto);

        // Mock UserServiceImpl behavior
        when(userServiceImpl.getUserByEmail("test@example.com")).thenReturn(userDto);

        // Mock HttpSession
        HttpSession session = Mockito.mock(HttpSession.class);

        // Call the method to be tested
        String result = viewController.home(authentication, session);

        // Assertions
        assertEquals("dashboard", result);

        // Verify that the method in userServiceImpl is called with the expected argument
        verify(userServiceImpl).getUserByEmail("test@example.com");

        // Verify that the session.setAttribute is called with the expected parameters
        verify(session).setAttribute("login-user-id", 1L);
        verify(session).setAttribute("loing-user-role", "PMO");
        verify(session).setAttribute("login-user-dp-id", 100L);

        // Ensure that no other interactions occurred on the mock objects
        Mockito.verifyNoMoreInteractions(session, userServiceImpl);
    }

    @Test
    void testAccessDenied() {
        String result = viewController.accessDenied();
        assertEquals("404", result);
        verifyNoInteractions(userServiceImpl, taskServiceImpl, projectServiceImpl);
    }

    @Test
    void testLoginFormView() {
        String result = viewController.loginFormView();
        assertEquals("login", result);
        verifyNoInteractions(userServiceImpl, taskServiceImpl, projectServiceImpl);
    }

    @Test
    void testProfile() {
        String result = viewController.profile();
        assertEquals("profile", result);
        verifyNoInteractions(userServiceImpl, taskServiceImpl, projectServiceImpl);
    }

    @Test
    void testProject() {
        String result = viewController.project();
        assertEquals("projects", result);
        verifyNoInteractions(userServiceImpl, taskServiceImpl, projectServiceImpl);
    }

    @Test
    void testDashboard() {
        String result = viewController.dashboard();
        assertEquals("redirect:/dashboard", result);
        verifyNoInteractions(userServiceImpl, taskServiceImpl, projectServiceImpl);
    }

    @Test
    void testDepartmentWithSDQCRole() {
        String result = viewController.department();
        assertEquals("department", result);
        verifyNoInteractions(userServiceImpl, taskServiceImpl, projectServiceImpl);
    }

    @Test
    void testResetPassword() {
        String result = viewController.resetPassword();
        assertEquals("password-reset", result);
        verifyNoInteractions(userServiceImpl, taskServiceImpl, projectServiceImpl);
    }

    @Test
    void testIssue() {
        String result = viewController.issue();
        assertEquals("issue", result);
        verifyNoInteractions(userServiceImpl, taskServiceImpl, projectServiceImpl);
    }

    @Test
    void testEmployeesWithPMORole() {
        String result = viewController.employees();
        assertEquals("employees", result);
        verifyNoInteractions(userServiceImpl, taskServiceImpl, projectServiceImpl);
    }

    @Test
    void taskTest() {
        // Mock data
        Long projectId = 1L;
        List<TasksDto> mockTasks = new ArrayList<>(); // Assuming TasksDto is the type returned by getTasksByProjectId
        ProjectDto mockProject = new ProjectDto(); // Assuming ProjectDto is the type returned by getProjectById

        // Mock TaskServiceImpl behavior
        when(taskServiceImpl.getTasksByProjectId(projectId)).thenReturn(mockTasks);

        // Mock ProjectServiceImpl behavior
        when(projectServiceImpl.getProjectById(projectId)).thenReturn(mockProject);

        // Mock Model
        Model model = Mockito.mock(Model.class);

        // Call the method to be tested
        String result = viewController.task(projectId, model);

        // Assertions
        assertEquals("project", result);

        // Verify that the model attributes are set correctly
        Mockito.verify(model).addAttribute("tasks", mockTasks);
        Mockito.verify(model).addAttribute("projectId", projectId);
        Mockito.verify(model).addAttribute("project", mockProject);

        // Ensure that no other interactions occurred on the mock objects
        Mockito.verifyNoMoreInteractions(model, taskServiceImpl, projectServiceImpl);
    }
}
