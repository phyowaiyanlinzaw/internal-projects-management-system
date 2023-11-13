package team.placeholder.internalprojectsmanagementsystem.service.project;

import team.placeholder.internalprojectsmanagementsystem.dto.model.project.TasksDto;
import team.placeholder.internalprojectsmanagementsystem.dto.uidto.ActualManMonthDto;
import team.placeholder.internalprojectsmanagementsystem.dto.uidto.PlanManMonthDto;
import team.placeholder.internalprojectsmanagementsystem.dto.uidto.TaskRequestDto;
import team.placeholder.internalprojectsmanagementsystem.model.project.projectenums.TaskStatus;

import java.util.List;

public interface TasksService {

    TasksDto save(TaskRequestDto taskRequestDto);

    List<TasksDto> getAllTasks();

    TasksDto updateTaskStatus(long taskId,String status,long startTime,long endTime);

    List<TasksDto> getTasksByProjectId(long id);

    List<TasksDto> getTasksByUserId(long id);

    Long countTaskByProjectIdAndStatus(Long id, TaskStatus x);

    Long countByProjectId(long id);
//

}
