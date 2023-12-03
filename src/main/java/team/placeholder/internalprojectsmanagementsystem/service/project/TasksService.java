package team.placeholder.internalprojectsmanagementsystem.service.project;

import team.placeholder.internalprojectsmanagementsystem.dto.model.project.TasksDto;
import team.placeholder.internalprojectsmanagementsystem.dto.uidto.TaskRequestDto;
import team.placeholder.internalprojectsmanagementsystem.model.project.projectenums.TaskStatus;

import java.util.List;

public interface TasksService {

    TasksDto getTaskById(long id);

    TasksDto save(TaskRequestDto taskRequestDto);

    List<TasksDto> getAllTasks();

    TasksDto updateTaskStatus(long taskId,String status,long startTime,long endTime,Double actual_hours);

    TasksDto updateTaskData(TaskRequestDto taskRequestDto);

    List<TasksDto> getTasksByProjectId(long id);

    List<TasksDto> getTasksByUserIdAndStatus(long id, TaskStatus status);

    List<TasksDto> getTasksByProjectAndUserId(long projectId, long userId);

    Long countTaskByProjectIdAndStatus(Long id, TaskStatus x);

    Long countByProjectId(long id);

    Long countByUserEmailAndStatus(String email, TaskStatus status);

    void deleteById(long id);

    void updateTasksDueStatus();

}
