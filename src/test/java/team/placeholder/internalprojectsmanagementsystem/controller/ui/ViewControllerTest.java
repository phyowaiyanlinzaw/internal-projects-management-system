package team.placeholder.internalprojectsmanagementsystem.controller.ui;

import jakarta.servlet.http.HttpSession;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.scheduling.config.Task;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import team.placeholder.internalprojectsmanagementsystem.dto.model.department.DepartmentDto;
import team.placeholder.internalprojectsmanagementsystem.dto.model.user.UserDto;
import team.placeholder.internalprojectsmanagementsystem.model.project.Project;
import team.placeholder.internalprojectsmanagementsystem.model.user.userenums.Role;
import team.placeholder.internalprojectsmanagementsystem.service.impl.project.ProjectServiceImpl;
import team.placeholder.internalprojectsmanagementsystem.service.impl.project.TaskServiceImpl;
import team.placeholder.internalprojectsmanagementsystem.service.impl.user.UserServiceImpl;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;


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
        // Add more assertions based on your specific logic and expectations

        // Example assertion for demonstration
        assert(result.equals("dashboard"));
    }

    @Test
    void testProfile() {
        // Arrange
        Model model = Mockito.mock(Model.class);

        // Act
        String result = viewController.profile();

        // Assert
        Mockito.verifyNoInteractions(userServiceImpl, taskServiceImpl, projectServiceImpl); // Ensure no interactions with other services
        assert(result.equals("profile"));
    }


    @Test
    void testTask() {
        // Arrange
//        Long projectId = 1L;
//        Model model = Mockito.mock(Model.class);
//
//        // Mock task and project data
//        List<Task> mockTasks = Arrays.asList();
//        Project mockProject = new Project();
//
//        // Mock service calls
//        Mockito.when(taskServiceImpl.getTasksByProjectId(projectId)).thenReturn(mockTasks);
//        Mockito.when(projectServiceImpl.getProjectById(projectId)).thenReturn(mockProject);
//
//        // Act
//        String result = viewController.task(projectId, model);
//
//        // Assert
//        Mockito.verify(model).addAttribute("tasks", mockTasks);
//        Mockito.verify(model).addAttribute("projectId", projectId);
//        Mockito.verify(model).addAttribute("project", mockProject);
//        assert(result.equals("project"));
    }



    // Add more test methods for other controller methods as needed
}
