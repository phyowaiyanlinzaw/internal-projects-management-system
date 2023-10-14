package team.placeholder.internalprojectsmanagementsystem.service.project;

import team.placeholder.internalprojectsmanagementsystem.dto.model.project.TaskDto;

public interface TaskService {

    TaskDto save(TaskDto taskDto);

    TaskDto getTaskById(int id);

    TaskDto getTaskByName(String name);

    TaskDto updateTask(TaskDto taskDto);

    void deleteTask(TaskDto taskDto);


}
