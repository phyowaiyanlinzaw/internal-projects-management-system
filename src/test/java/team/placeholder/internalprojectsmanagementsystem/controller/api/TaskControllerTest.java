package team.placeholder.internalprojectsmanagementsystem.controller.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import team.placeholder.internalprojectsmanagementsystem.dto.model.project.TasksDto;
import team.placeholder.internalprojectsmanagementsystem.dto.uidto.TaskRequestDto;
import team.placeholder.internalprojectsmanagementsystem.service.impl.project.TaskServiceImpl;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TaskControllerTest {

    @Mock
    private TaskServiceImpl taskService;

    @InjectMocks
    private TaskController taskController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void taskList() {
        List<TasksDto> tasksDtoList = Arrays.asList(
                new TasksDto()
        );
        when(taskService.getAllTasks()).thenReturn(tasksDtoList);

        // Testing the taskList method
        ResponseEntity<List<TasksDto>> responseEntity = taskController.taskList();

        // Verifying the result and status code
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(tasksDtoList, responseEntity.getBody());
    }



    @Test
    void taskListByProjectId() {

        // Mocking the behavior of taskService for taskListByProjectId
        long projectId = 1L;
        List<TasksDto> tasksDtoList = Arrays.asList(
                new TasksDto()
        );
        when(taskService.getTasksByProjectId(projectId)).thenReturn(tasksDtoList);

        // Testing the taskListByProjectId method
        ResponseEntity<List<TasksDto>> responseEntity = taskController.taskListByProjectId(projectId);

        // Verifying the result and status code
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(tasksDtoList, responseEntity.getBody());
    }


    @Test
    void taskListByUserId() {
        long userId = 1L;
        List<TasksDto> tasksDtoList = Arrays.asList(
                new TasksDto()
        );
        when(taskService.getTasksByUserId(userId)).thenReturn(tasksDtoList);

        // Testing the taskListByUserId method
        ResponseEntity<List<TasksDto>> responseEntity = taskController.taskListByUserId(userId);

        // Verifying the result and status code
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(tasksDtoList, responseEntity.getBody());
    }

    @Test
    void taskListByProjectIdAndUserId() {
        long projectId = 1L;
        long userId = 1L;
        List<TasksDto> tasksDtoList = Arrays.asList(
                new TasksDto()
        );
        when(taskService.getTasksByProjectAndUserId(projectId, userId)).thenReturn(tasksDtoList);

        // Testing the taskListByProjectIdAndUserId method
        ResponseEntity<List<TasksDto>> responseEntity = taskController.taskListByProjectIdAndUserId(projectId, userId);

        // Verifying the result and status code
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(tasksDtoList, responseEntity.getBody());
    }

    @Test
    void saveTask() {
        TaskRequestDto taskRequestDto = new TaskRequestDto();
        taskRequestDto.setTitle("New Task");

        TasksDto savedTaskDto = new TasksDto();
        when(taskService.save(taskRequestDto)).thenReturn(savedTaskDto);

        // Testing the saveTask method
        ResponseEntity<TasksDto> responseEntity = taskController.saveTask(taskRequestDto);

        // Verifying the result and status code
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(savedTaskDto, responseEntity.getBody());
    }

    @Test
    void updateTaskStatus() {

        long taskId = 1L;
        String status = "Completed";
        long actualStartTime = System.currentTimeMillis();
        long actualEndTime = System.currentTimeMillis() + 1000;

        TasksDto updatedTaskDto = new TasksDto();
        when(taskService.updateTaskStatus(taskId, status, actualStartTime, actualEndTime)).thenReturn(updatedTaskDto);

        // Testing the updateTaskStatus method
        ResponseEntity<TasksDto> responseEntity = taskController.updateTaskStatus(taskId, status, actualStartTime, actualEndTime);

        // Verifying the result and status code
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(updatedTaskDto, responseEntity.getBody());
    }

    @Test
    void updateTaskData() {

        TaskRequestDto taskRequestDto = new TaskRequestDto();
        taskRequestDto.setTitle("Updated Task");

        TasksDto updatedTaskDto = new TasksDto();
        when(taskService.updateTaskData(taskRequestDto)).thenReturn(updatedTaskDto);

        // Testing the updateTaskData method
        ResponseEntity<TasksDto> responseEntity = taskController.updateTaskData(taskRequestDto);

        // Verifying the result and status code
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(updatedTaskDto, responseEntity.getBody());
    }

    @Test
    void deleteTask() {

        // Mocking the behavior of taskService for deleteTask
        long taskId = 1L;

        // Testing the deleteTask method
        taskController.deleteTask(taskId);

        // Verifying that taskService.deleteById is called once
        verify(taskService, times(1)).deleteById(taskId);
    }
}