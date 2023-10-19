package team.placeholder.internalprojectsmanagementsystem.service.project;

import team.placeholder.internalprojectsmanagementsystem.dto.model.project.TasksDto;

public interface TaskService {

    TasksDto save(TasksDto taskDto);

    TasksDto getTaskById(long id);

    TasksDto getTaskByDescription(String description);

    TasksDto updateTask(TasksDto taskDto);

    void deleteTask(long id);


}
