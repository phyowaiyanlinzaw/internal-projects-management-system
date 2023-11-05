package team.placeholder.internalprojectsmanagementsystem.service.project;

import team.placeholder.internalprojectsmanagementsystem.dto.model.project.TasksDto;

import java.util.List;

public interface TaskService {

    TasksDto save(TasksDto taskDto);

    List<TasksDto> getAllTasks();

    TasksDto getTaskById(long id);

    TasksDto updateTask(TasksDto taskDto);

    void deleteTask(long id);

    List<TasksDto> getTasksByProjectId(long id);

    List<TasksDto> getTasksByUserId(long id);


}
