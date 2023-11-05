package team.placeholder.internalprojectsmanagementsystem.controller.api;

import com.sun.mail.iap.Response;

import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import team.placeholder.internalprojectsmanagementsystem.dto.model.project.TasksDto;
import team.placeholder.internalprojectsmanagementsystem.dto.model.user.UserDto;
import team.placeholder.internalprojectsmanagementsystem.dto.uidto.TasDto;
import team.placeholder.internalprojectsmanagementsystem.dto.uidto.UrDto;
import team.placeholder.internalprojectsmanagementsystem.model.project.projectenums.TaskStatus;
import team.placeholder.internalprojectsmanagementsystem.model.user.User;
import team.placeholder.internalprojectsmanagementsystem.service.impl.project.TaskServiceImpl;
import team.placeholder.internalprojectsmanagementsystem.service.impl.user.UserServiceImpl;

import java.util.ArrayList;
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
    public ResponseEntity<TasksDto> saveTask(@RequestBody TasDto tasDto) {
        TasksDto taskDto = new TasksDto();
        taskDto.setDescription(tasDto.getDescription());
        taskDto.setStatus(tasDto.getStatus());
        //TODO: Discuss TasDTO
//        taskDto.setProject(tasDto.getProject());
//        taskDto.setUser(tasDto.getUser());
        taskDto.setActual_start_time(tasDto.getActual_start_time());
        taskDto.setActual_end_time(tasDto.getActual_end_time());
        return ResponseEntity.ok(taskService.save(taskDto));
    }

}
