package team.placeholder.internalprojectsmanagementsystem.service.project;

import team.placeholder.internalprojectsmanagementsystem.dto.model.project.ProjectDto;
import java.util.List;

public interface ProjectService {

    ProjectDto save(ProjectDto projectDto);

    List<ProjectDto> getAllProjects();
    ProjectDto getProjectById(long id);
    ProjectDto getProjectByName(String name);

    ProjectDto updateProject(ProjectDto projectDto);

    long getCountByDepartment(long id);

    Long countAllProjects();
}
