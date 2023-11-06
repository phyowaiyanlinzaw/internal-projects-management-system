package team.placeholder.internalprojectsmanagementsystem.service.project;

import team.placeholder.internalprojectsmanagementsystem.dto.model.project.TasksDto;
import team.placeholder.internalprojectsmanagementsystem.model.project.projectenums.TaskStatus;

import java.util.List;

public interface TasksService {

    TasksDto save(TasksDto taskDto);

    List<TasksDto> getAllTasks();

    TasksDto getTaskById(long id);

    TasksDto updateTask(TasksDto taskDto);

    void deleteTask(long id);

    List<TasksDto> getTasksByProjectId(long id);

    List<TasksDto> getTasksByUserId(long id);

    Long countTaskByProjectIdAndStatus(Long id, TaskStatus x);

}
