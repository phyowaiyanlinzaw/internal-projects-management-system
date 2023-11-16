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

    TasksDto updateTaskData(TaskRequestDto taskRequestDto);

    List<TasksDto> getTasksByProjectId(long id);

    List<TasksDto> getTasksByUserId(long id);

    List<TasksDto> getTasksByProjectAndUserId(long projectId, long userId);

    Long countTaskByProjectIdAndStatus(Long id, TaskStatus x);

    Long countByProjectId(long id);

//    List<TasksDto> findTasksByStartAndEndMonth(long projectId, String startMonth, String endMonth);


//    List<TasksDto> filterTasksByMonthRange(List<TasksDto> tasks, String startMonth, String endMonth);
//
    List<ActualManMonthDto> calculateMonthlyActualManHoursFromTasks(long projectId);

    List<PlanManMonthDto> calculateMonthlyPlanManHoursFromTasks(long projectId);

    void deleteById(long id);

}
