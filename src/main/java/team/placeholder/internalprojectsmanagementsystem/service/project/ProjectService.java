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

    Long countByUserId(long id);

    List<ProjectDto> getAllProjectsByUserId(long id);

    List<ProjectDto> getAllProjectsByDepartmentId(long id);

//    List<ProjectDto> getAllProjectsByClientId(long id);
//
//    List<ProjectDto> getAllProjectsByProjectManagerId(long id);
//
//    List<ProjectDto> getAllProjectsByStatus(String status);

}
