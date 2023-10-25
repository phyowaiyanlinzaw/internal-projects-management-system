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
        tasksDto.setPlan_start_time(tasks.getPlan_start_time());
        tasksDto.setPlan_end_time(tasks.getPlan_end_time());
        tasksDto.setActual_start_time(tasks.getActual_start_time());
        tasksDto.setActual_end_time(tasks.getActual_end_time());
        tasksDto.setUser(tasks.getUser());
        tasksDto.setProject(tasks.getProject());
        return tasksDto;
    }

    public static Tasks toTasks(TasksDto tasksDto) {
        if(tasksDto == null){
            return null;
        }

        Tasks tasks = new Tasks();
        tasks.setId(tasksDto.getId());
        tasks.setTitle(tasksDto.getTitle());
        tasks.setDescription(tasksDto.getDescription());
        tasks.setStatus(tasksDto.getStatus());
        tasks.setPlan_start_time(tasksDto.getPlan_start_time());
        tasks.setPlan_end_time(tasksDto.getPlan_end_time());
        tasks.setActual_start_time(tasksDto.getActual_start_time());
        tasks.setActual_end_time(tasksDto.getActual_end_time());
        tasks.setUser(tasksDto.getUser());
        tasks.setProject(tasksDto.getProject());
        return tasks;
    }
}
