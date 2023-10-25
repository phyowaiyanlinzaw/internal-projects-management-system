package team.placeholder.internalprojectsmanagementsystem.dto.mapper.project;

import team.placeholder.internalprojectsmanagementsystem.dto.model.project.ProjectStatusDto;

public class ProjectStatusMapper {
    public static ProjectStatusDto toProjectStatusDto(ProjectStatus projectStatus){
        if(projectStatus == null){
            return  null;
        }
        ProjectStatusDto projectStatusDto = new ProjectStatusDto();
        projectStatusDto.setId(projectStatus.getId());
        projectStatusDto.setName(projectStatus.getName());
        return projectStatusDto;
    }
}
