//package team.placeholder.internalprojectsmanagementsystem.service.project;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.MockitoAnnotations;
//import org.springframework.scheduling.config.Task;
//import team.placeholder.internalprojectsmanagementsystem.dto.model.project.ProjectDto;
//import team.placeholder.internalprojectsmanagementsystem.dto.model.project.TasksDto;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class TaskServiceTest {
//
//    @Mock
//    private TaskService taskService;
//
//    @BeforeEach
//    public void setUp() {
//        MockitoAnnotations.initMocks(this);
//    }
//
//    @Test
//    public void testSaveTask() {
//        TasksDto taskDto = new TasksDto();
//
//        Mockito.when(taskService.save(taskDto)).thenReturn(taskDto);
//        TasksDto saveTask = taskService.save(taskDto);
//        assertEquals(taskDto,saveTask);
//
//    }
//
//    @Test
//    public void testGetTaskById() {
//        long taskId = 1;
//        TasksDto tasksDto = new TasksDto();
//        Mockito.when(taskService.getTaskById(taskId)).thenReturn(tasksDto);
//        TasksDto taskById = taskService.getTaskById(1L);
//        assertEquals(tasksDto,taskById);
//
//    }
//
//    @Test
//    public void testUpdateTask() {
//        TasksDto tasksdto = new TasksDto();
//        Mockito.when(taskService.updateTask(tasksdto)).thenReturn(tasksdto);
//        TasksDto updateTask = taskService.updateTask(tasksdto);
//        assertEquals(tasksdto,updateTask);
//    }
//
//    @Test
//    public void testDeleteTask() {
//       long taskId = 1;
//        taskService.deleteTask(taskId);
//
//        Mockito.verify(taskService).deleteTask(taskId);
//    }
//
//
//}