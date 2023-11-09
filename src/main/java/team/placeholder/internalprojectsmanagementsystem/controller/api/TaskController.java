package team.placeholder.internalprojectsmanagementsystem.controller.api;

import lombok.AllArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import team.placeholder.internalprojectsmanagementsystem.dto.model.project.TasksDto;
import team.placeholder.internalprojectsmanagementsystem.service.impl.project.TaskServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/api/task/")
@AllArgsConstructor
public class TaskController {

    private final TaskServiceImpl taskService;

    @GetMapping("/list")
    public ResponseEntity<List<TasksDto>> taskList() {

            return ResponseEntity.ok(taskService.getAllTasks());

    }

    @GetMapping("/list/project/{id}")
    public ResponseEntity<List<TasksDto>> taskListByProjectId(@PathVariable long id) {

            return ResponseEntity.ok(taskService.getTasksByProjectId(id));

    }

    @GetMapping("/list/user/{id}")
    public ResponseEntity<List<TasksDto>> taskListByUserId(@PathVariable long id) {

            return ResponseEntity.ok(taskService.getTasksByUserId(id));

    }

    @PostMapping("/save")
    public ResponseEntity<TasksDto> saveTask(@RequestBody TaskRequestDto tasksDto) {

            return ResponseEntity.ok(taskService.save(tasksDto));

    }

}
