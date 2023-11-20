//package team.placeholder.internalprojectsmanagementsystem.service.project;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.MockitoAnnotations;
//import org.springframework.scheduling.config.Task;
//import team.placeholder.internalprojectsmanagementsystem.dto.model.project.TasksDto;
//import team.placeholder.internalprojectsmanagementsystem.dto.uidto.TaskRequestDto;
//import team.placeholder.internalprojectsmanagementsystem.model.project.projectenums.TaskStatus;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class TasksServiceTest {
//
//    @Mock
//    private TasksService tasksService;
//
//    @BeforeEach
//    public void setUp() {
//        MockitoAnnotations.initMocks(this);
//    }
//
//
//    @Test
//    void save() {
//        TaskRequestDto taskRequestDto=new TaskRequestDto();
//        TasksDto tasksDto = new TasksDto();
//        Mockito.when(tasksService.save(taskRequestDto)).thenReturn(tasksDto);
//        TasksDto savedTask = tasksService.save(taskRequestDto);
//        assertEquals(tasksDto, savedTask);
//    }
//
//    @Test
//    void getAllTasks() {
//        List<TasksDto> tasksDtoList = new ArrayList<>();
//        Mockito.when(tasksService.getAllTasks()).thenReturn(tasksDtoList);
//        List<TasksDto> allTasks = tasksService.getAllTasks();
//        assertEquals(tasksDtoList,allTasks);
//    }
//
//    @Test
//    void updateTaskStatus() {
//        long taskId = 1L;
//        String status = "InProgress";
//        long startTime = System.currentTimeMillis();
//        long endTime = System.currentTimeMillis();
//
//        TasksDto existingTask = new TasksDto();
//        existingTask.setId(taskId);
//        existingTask.setPlan_start_time(startTime);
//        existingTask.setPlan_end_time(endTime);
//
//        Mockito.when(tasksService.updateTaskStatus(taskId,status,startTime,endTime)).thenReturn(existingTask);
//        TasksDto updatedTask = tasksService.updateTaskStatus(taskId,status,startTime,endTime);
//        assertEquals(existingTask,updatedTask);
//
//    }
//
//    @Test
//    void updateTaskData() {
//        TaskRequestDto taskRequestDto = new TaskRequestDto();
//        TasksDto tasksDto = new TasksDto();
//        Mockito.when(tasksService.updateTaskData(taskRequestDto)).thenReturn(tasksDto);
//        TasksDto updateTask = tasksService.updateTaskData(taskRequestDto);
//        assertEquals(tasksDto,updateTask);
//    }
//
//    @Test
//    void getTasksByProjectId() {
//        long projectId = 1L;
//        List<TasksDto> tasksDtoList = new ArrayList<>();
//        Mockito.when(tasksService.getTasksByProjectId(projectId)).thenReturn(tasksDtoList);
//        List<TasksDto> tasksByProjectId = tasksService.getTasksByProjectId(projectId);
//        assertEquals(tasksDtoList,tasksByProjectId);
//
//    }
//
//    @Test
//    void getTasksByUserId() {
//        long userId = 1L;
//        List<TasksDto> tasksDtoList = new ArrayList<>();
//        Mockito.when(tasksService.getTasksByUserId(userId)).thenReturn(tasksDtoList);
//        List<TasksDto> tasksByUserId = tasksService.getTasksByUserId(userId);
//        assertEquals(tasksDtoList,tasksByUserId);
//    }
//
//    @Test
//    void getTasksByProjectAndUserId() {
//        long projectId = 1L;
//        long userId = 1L;
//        List<TasksDto> tasksDtoList = new ArrayList<>();
//        Mockito.when(tasksService.getTasksByProjectAndUserId(projectId,userId)).thenReturn(tasksDtoList);
//        List<TasksDto> tasksByProjectAndUserId = tasksService.getTasksByProjectAndUserId(projectId,userId);
//        assertEquals(tasksDtoList,tasksByProjectAndUserId);
//
//    }
//
//    @Test
//    void countTaskByProjectIdAndStatus() {
//        long projectId = 1L;
//        long count = 1L;
//        Mockito.when(tasksService.countTaskByProjectIdAndStatus(projectId, TaskStatus.TODO)).thenReturn(count);
//        Long taskCount = tasksService.countTaskByProjectIdAndStatus(projectId,TaskStatus.TODO);
//        assertEquals(count,taskCount);
//    }
//
//    @Test
//    void countByProjectId() {
//        long projectId = 1L;
//        long count = 1L;
//        Mockito.when(tasksService.countByProjectId(projectId)).thenReturn(count);
//        Long taskCount = tasksService.countByProjectId(projectId);
//        assertEquals(count,taskCount);
//    }
//
//
//    @Test
//    void deleteById() {
//        long taskId = 1L;
//        tasksService.deleteById(taskId);
//        Mockito.verify(tasksService).deleteById(taskId);
//
//    }
//}