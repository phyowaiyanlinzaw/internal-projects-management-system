package team.placeholder.internalprojectsmanagementsystem.service.impl.project;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import team.placeholder.internalprojectsmanagementsystem.dto.model.project.TasksDto;
import team.placeholder.internalprojectsmanagementsystem.dto.model.user.UserDto;
import team.placeholder.internalprojectsmanagementsystem.dto.uidto.TaskRequestDto;
import team.placeholder.internalprojectsmanagementsystem.model.project.Project;
import team.placeholder.internalprojectsmanagementsystem.model.project.Tasks;
import team.placeholder.internalprojectsmanagementsystem.model.user.User;
import team.placeholder.internalprojectsmanagementsystem.repository.project.ProjectRepository;
import team.placeholder.internalprojectsmanagementsystem.repository.project.TaskRepository;
import team.placeholder.internalprojectsmanagementsystem.repository.user.UserRepository;
import team.placeholder.internalprojectsmanagementsystem.service.impl.NotiServiceImpl.NotificationServiceImpl;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TaskServiceImplTest {

    @Mock
    private TaskRepository taskRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private ProjectRepository projectRepository;

    @Mock
    private NotificationServiceImpl notificationService;

    @InjectMocks
    private TaskServiceImpl taskService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void save() {

    TaskRequestDto tasksDto = new  TaskRequestDto();
    tasksDto.setTitle("title");
    Tasks tasks = new Tasks();
    tasks.setTitle("title");
    taskService.save(tasksDto);
    verify(taskRepository, times(1)).save(tasks);
        }



    @Test
    void getAllTasks() {

    }

    @Test
    void updateTaskStatus() {
    }

    @Test
    void updateTaskData() {
    }

    @Test
    void getTasksByProjectId() {
    }

    @Test
    void getTasksByUserId() {
    }

    @Test
    void getTasksByProjectAndUserId() {
    }

    @Test
    void countTaskByProjectIdAndStatus() {
    }

    @Test
    void countByProjectId() {
    }

    @Test
    void calculateMonthlyActualManHoursFromTasks() {
    }

    @Test
    void calculateMonthlyPlanManHoursFromTasks() {
    }

    @Test
    void deleteById() {
    }
}