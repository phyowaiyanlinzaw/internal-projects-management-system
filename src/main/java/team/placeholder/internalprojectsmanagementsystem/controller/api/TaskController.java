package team.placeholder.internalprojectsmanagementsystem.controller.api;

import lombok.AllArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import team.placeholder.internalprojectsmanagementsystem.dto.model.project.TasksDto;
import team.placeholder.internalprojectsmanagementsystem.dto.uidto.TaskRequestDto;
import team.placeholder.internalprojectsmanagementsystem.model.project.projectenums.TaskStatus;
import team.placeholder.internalprojectsmanagementsystem.service.impl.project.TaskServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/api/task/")
@AllArgsConstructor
@Slf4j
public class TaskController {

    private final TaskServiceImpl taskService;

    @GetMapping("/get/{id}")
    public ResponseEntity<TasksDto> getTaskById(@PathVariable long id) {
        
    	return ResponseEntity.ok(taskService.getTaskById(id));
        
    }

    @GetMapping("count/status/{status}")
    public ResponseEntity<Long> countTaskByUserIdAndStatus(@PathVariable String status) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return ResponseEntity.ok(taskService.countByUserEmailAndStatus(authentication.getName(), TaskStatus.valueOf(status)));
    }

    @GetMapping("/list")
    public ResponseEntity<List<TasksDto>> taskList() {

            return ResponseEntity.ok(taskService.getAllTasks());

    }

    @GetMapping("/list/project/{id}")
    public ResponseEntity<List<TasksDto>> taskListByProjectId(@PathVariable long id) {

            return ResponseEntity.ok(taskService.getTasksByProjectId(id));

    }

    @GetMapping("/list/user/{id}/status/{status}")
    public ResponseEntity<List<TasksDto>> taskListByUserIdAndStatus(@PathVariable long id, @PathVariable String status) {

            return ResponseEntity.ok(taskService.getTasksByUserIdAndStatus(id, TaskStatus.valueOf(status)));

    }

    @GetMapping("/list/project/{projectId}/user/{userId}")
    public ResponseEntity<List<TasksDto>> taskListByProjectIdAndUserId(@PathVariable long projectId, @PathVariable long userId) {

            return ResponseEntity.ok(taskService.getTasksByProjectAndUserId(projectId, userId));

    }

    @PostMapping("/save")
    public ResponseEntity<TasksDto> saveTask(@RequestBody TaskRequestDto tasksDto) {

            return ResponseEntity.ok(taskService.save(tasksDto));

    }

    @PostMapping("/update/status")
    public ResponseEntity<TasksDto> updateTaskStatus(
            @RequestParam("id") long id,
            @RequestParam("status") String status,
            @RequestParam("actualStartTime") long actual_start_time,
            @RequestParam("actualEndTime") long actual_end_time,
            @RequestParam("actualHours") Double actual_hours
            ) {
        log.info("id: "+id+" status: "+status+" actual_start_time: "+actual_start_time+" actual_end_time: "+actual_end_time);
            return ResponseEntity.ok(taskService.updateTaskStatus(id,status,actual_start_time,actual_end_time,actual_hours));
    }

    @PostMapping("/update/data")
    public ResponseEntity<TasksDto> updateTaskData(
            @RequestBody TaskRequestDto taskRequestDto
    ){
        return ResponseEntity.ok(taskService.updateTaskData(taskRequestDto));
    }

    @DeleteMapping("/delete")
    public void deleteTask(@RequestBody long id) {
        taskService.deleteById(id);
    }

}
