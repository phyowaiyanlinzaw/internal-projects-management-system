package team.placeholder.internalprojectsmanagementsystem.dto.mapper.project;

import org.springframework.stereotype.Component;
import team.placeholder.internalprojectsmanagementsystem.dto.model.project.ProjectDto;
import team.placeholder.internalprojectsmanagementsystem.model.project.Project;

@Component
public class ProjectMapper {
    public static ProjectDto toProjectDto(Project project){
        if(project == null){
            return null;
        }
        ProjectDto projectDto = new ProjectDto();
        projectDto.setId(project.getId());
        projectDto.setName(project.getName());
        projectDto.setBackground(project.getBackground());
        projectDto.setDuration(project.getDuration());
        projectDto.setStart_date(project.getStart_date());
        projectDto.setEnd_date(project.getEnd_date());
        projectDto.setCurrent_phase(project.getCurrent_phase());
        projectDto.setObjective(project.getObjective());
        projectDto.setClient(project.getClient());
        projectDto.setUser(project.getUser());
        projectDto.setDepartment(project.getDepartment());
        return projectDto;
    }


}
