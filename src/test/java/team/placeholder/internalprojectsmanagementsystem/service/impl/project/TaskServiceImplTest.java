package team.placeholder.internalprojectsmanagementsystem.service.impl.project;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import static org.assertj.core.api.Assertions.assertThat;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import team.placeholder.internalprojectsmanagementsystem.dto.model.project.ProjectDto;
import team.placeholder.internalprojectsmanagementsystem.dto.model.project.TasksDto;
import team.placeholder.internalprojectsmanagementsystem.dto.model.user.UserDto;
import team.placeholder.internalprojectsmanagementsystem.dto.uidto.TaskRequestDto;
import team.placeholder.internalprojectsmanagementsystem.model.project.Project;
import team.placeholder.internalprojectsmanagementsystem.model.project.Tasks;
import team.placeholder.internalprojectsmanagementsystem.model.project.projectenums.TaskStatus;
import team.placeholder.internalprojectsmanagementsystem.model.user.User;
import team.placeholder.internalprojectsmanagementsystem.repository.project.ProjectRepository;
import team.placeholder.internalprojectsmanagementsystem.repository.project.TaskRepository;
import team.placeholder.internalprojectsmanagementsystem.repository.user.UserRepository;
import team.placeholder.internalprojectsmanagementsystem.service.impl.NotiServiceImpl.NotificationServiceImpl;

import java.util.ArrayList;
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

    @Mock
    ModelMapper modelMapper;

    @InjectMocks
    private TaskServiceImpl taskService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void save() {


        // Assert


        }



    @Test
    void getAllTasks() {
        List<Tasks> tasksList = new ArrayList<>();
        Tasks task1 = new Tasks();
        Tasks task2 = new Tasks();
        tasksList.add(task1);
        tasksList.add(task2);

        when(taskRepository.findAll()).thenReturn(tasksList);

        User user = new User();
        when(modelMapper.map(user, UserDto.class)).thenReturn(new UserDto());

        Project project = new Project();
        when(modelMapper.map(project, ProjectDto.class)).thenReturn(new ProjectDto());

        when(modelMapper.map(task1, TasksDto.class)).thenReturn(new TasksDto());
        when(modelMapper.map(task2, TasksDto.class)).thenReturn(new TasksDto());

        // Act
        List<TasksDto> result = taskService.getAllTasks();

        // Assert
        assertEquals(2, result.size());
    }

    @Test
    void updateTaskStatus() {
        long taskId = 1L;
        String status = "IN_PROGRESS";
        long startTime = System.currentTimeMillis();
        long endTime = System.currentTimeMillis() + 3600000; // 1 hour
        Double actualHours = 1.0;

        Tasks existingTask = new Tasks();
        existingTask.setId(taskId);
        existingTask.setStatus(TaskStatus.TODO);
        when(taskRepository.findById(taskId)).thenReturn(existingTask);

        // Act
        TasksDto result = taskService.updateTaskStatus(taskId, status, startTime, endTime, actualHours);

        // Assert
        verify(taskRepository).findById(taskId);
        verify(taskRepository).save(existingTask);
    }

    @Test
    void updateTaskData() {

    }

    @Test
    void getTasksByProjectId() {
        long projectId = 1L;

        List<Tasks> tasksList = new ArrayList<>();
        Tasks task1 = new Tasks();
        Tasks task2 = new Tasks();
        tasksList.add(task1);
        tasksList.add(task2);

        when(taskRepository.findByProjectId(projectId)).thenReturn(tasksList);

        User user = new User();
        when(modelMapper.map(user, UserDto.class)).thenReturn(new UserDto());

        Project project = new Project();
        when(modelMapper.map(project, ProjectDto.class)).thenReturn(new ProjectDto());

        when(modelMapper.map(task1, TasksDto.class)).thenReturn(new TasksDto());
        when(modelMapper.map(task2, TasksDto.class)).thenReturn(new TasksDto());

        // Act
        List<TasksDto> result = taskService.getTasksByProjectId(projectId);

        // Assert
        assertEquals(2, result.size());
    }

    @Test
    void getTasksByUserIdAndStatus() {
        long userId = 1L;
        TaskStatus status = TaskStatus.IN_PROGRESS;

        List<Tasks> tasksList = new ArrayList<>();
        Tasks task1 = new Tasks(/* provide necessary details */);
        Tasks task2 = new Tasks(/* provide necessary details */);
        tasksList.add(task1);
        tasksList.add(task2);

        when(taskRepository.findByUserIdAndStatus(userId,status)).thenReturn(tasksList);

        User user = new User(/* provide necessary details */);
        when(modelMapper.map(user, UserDto.class)).thenReturn(new UserDto(/* provide necessary details */));

        Project project = new Project(/* provide necessary details */);
        when(modelMapper.map(project, ProjectDto.class)).thenReturn(new ProjectDto(/* provide necessary details */));

        when(modelMapper.map(task1, TasksDto.class)).thenReturn(new TasksDto(/* provide necessary details */));
        when(modelMapper.map(task2, TasksDto.class)).thenReturn(new TasksDto(/* provide necessary details */));

        // Act
        List<TasksDto> result = taskService.getTasksByUserIdAndStatus(userId, status);

        // Assert
        assertEquals(2, result.size());

    }


    @Test
    void getTasksByProjectAndUserId() {
        long projectId = 1L;
        long userId = 1L;

        List<Tasks> tasksList = new ArrayList<>();
        Tasks task1 = new Tasks(/* provide necessary details */);
        Tasks task2 = new Tasks(/* provide necessary details */);
        tasksList.add(task1);
        tasksList.add(task2);

        when(taskRepository.findTasksByProjectIdAndUserId(projectId, userId)).thenReturn(tasksList);

        User user = new User(/* provide necessary details */);
        when(modelMapper.map(user, UserDto.class)).thenReturn(new UserDto(/* provide necessary details */));

        Project project = new Project(/* provide necessary details */);
        when(modelMapper.map(project, ProjectDto.class)).thenReturn(new ProjectDto(/* provide necessary details */));

        when(modelMapper.map(task1, TasksDto.class)).thenReturn(new TasksDto(/* provide necessary details */));
        when(modelMapper.map(task2, TasksDto.class)).thenReturn(new TasksDto(/* provide necessary details */));

        // Act
        List<TasksDto> result = taskService.getTasksByProjectAndUserId(projectId, userId);

        // Assert
        assertEquals(2, result.size());
    }

    @Test
    void countTaskByProjectIdAndStatus() {
        long projectId = 1L;
        TaskStatus status = TaskStatus.IN_PROGRESS;

        when(taskRepository.countByProjectIdAndStatus(projectId, status)).thenReturn(3L);

        // Act
        Long result = taskService.countTaskByProjectIdAndStatus(projectId, status);

        // Assert
        assertEquals(3L, result);
    }

    @Test
    void countByProjectId() {
        long projectId = 1L;

        when(taskRepository.countByProjectId(projectId)).thenReturn(5L);

        // Act
        Long result = taskService.countByProjectId(projectId);

        // Assert
        assertEquals(5L, result);

    }

   @Test
   void testCountByUserEmailAndStatus(){
       String userEmail = "user@example.com";
       TaskStatus status = TaskStatus.IN_PROGRESS;

       when(taskRepository.countByUserEmailAndDeletedFalseAndStatus(userEmail, status)).thenReturn(2L);

       // Act
       Long result = taskService.countByUserEmailAndStatus(userEmail, status);

       // Assert
       assertEquals(2L, result);

   }

    @Test
    void deleteById() {
        long taskId = 1L;

        Tasks task = new Tasks();
        when(taskRepository.findById(taskId)).thenReturn(task);

        // Act
        taskService.deleteById(taskId);

        // Assert
        verify(taskRepository, times(1)).findById(taskId);
        verify(taskRepository, times(1)).save(task);
    }
}