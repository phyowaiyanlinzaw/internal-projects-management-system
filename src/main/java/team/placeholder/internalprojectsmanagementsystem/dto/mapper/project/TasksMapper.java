package team.placeholder.internalprojectsmanagementsystem.dto.mapper.project;

import team.placeholder.internalprojectsmanagementsystem.dto.model.project.TasksDto;
import team.placeholder.internalprojectsmanagementsystem.model.project.Tasks;

public class TasksMapper {
    public static TasksDto toTasksDto (Tasks tasks){
        if(tasks == null){
            return null;
        }
        TasksDto tasksDto = new TasksDto();
        tasksDto.setId(tasks.getId());
        tasksDto.setTitle(tasks.getTitle());
        tasksDto.setDescription(tasks.getDescription());
        tasksDto.setStatus(tasks.getStatus());
        tasksDto.setStart_time(tasks.getStart_time());
        tasksDto.setEnd_time(tasks.getEnd_time());
        tasksDto.setActual_mm(tasks.getActual_mm());
        tasksDto.setExcepted_mm(tasks.getExpected_mm());
        return tasksDto;
    }
}
