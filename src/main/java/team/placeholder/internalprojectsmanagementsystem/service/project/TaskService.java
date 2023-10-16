package team.placeholder.internalprojectsmanagementsystem.service.project;

import team.placeholder.internalprojectsmanagementsystem.dto.model.project.TasksDto;

public interface TaskService {

    TasksDto save(TasksDto taskDto);

    TasksDto getTaskById(int id);

    TasksDto getTaskByName(String name);

    TasksDto updateTask(TasksDto taskDto);

    void deleteTask(TasksDto taskDto);


}
