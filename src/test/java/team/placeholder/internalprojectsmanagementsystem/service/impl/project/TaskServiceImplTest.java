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
import team.placeholder.internalprojectsmanagementsystem.model.project.Tasks;
import team.placeholder.internalprojectsmanagementsystem.model.user.User;
import team.placeholder.internalprojectsmanagementsystem.repository.project.TaskRepository;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TaskServiceImplTest {

    @Mock
    private TaskRepository tasksRepository;

    @Mock
    private ModelMapper modelMapper;
    @InjectMocks
    private TaskServiceImpl tasksService;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.
                openMocks(this);
    }

    @Test
    void save() {

    }

    @Test
    void getAllTasks() {
        Tasks task1 = new Tasks();
        task1.setId(1L);
        task1.setDescription("Task 1");

        Tasks task2 = new Tasks();
        task2.setId(2L);
        task2.setDescription("Task 2");

        List<Tasks> taskList = Arrays.asList(task1, task2);

        when(tasksRepository.findAll()).thenReturn(taskList);

        // Mock modelMapper
        User user1 = new User();
        user1.setId(1L);
        User user2 = new User();
        user2.setId(2L);

        when(modelMapper.map(task1.getUser(), UserDto.class)).thenReturn(new UserDto(user1.getId()));
        when(modelMapper.map(task2.getUser(), UserDto.class)).thenReturn(new UserDto(user2.getId()));

        // Act
        List<TasksDto> resultDtos = tasksService.getAllTasks();

        // Assert
        assertEquals(taskList.size(), resultDtos.size());

        // Verify that the findAll method of the repository was called
        verify(tasksRepository, times(1)).findAll();

        // Verify that the map method of the modelMapper was called for each task's user
        verify(modelMapper, times(1)).map(task1.getUser(), UserDto.class);
        verify(modelMapper, times(1)).map(task2.getUser(), UserDto.class);
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