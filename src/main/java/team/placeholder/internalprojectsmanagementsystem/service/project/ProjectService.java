package team.placeholder.internalprojectsmanagementsystem.service.project;

import team.placeholder.internalprojectsmanagementsystem.dto.model.project.ProjectDto;
import team.placeholder.internalprojectsmanagementsystem.model.project.Project;

import java.util.List;

public interface ProjectService {

    Project save(Project project);

    List<ProjectDto> getAllProjects();
    ProjectDto getProjectById(long id);
    ProjectDto getProjectByName(String name);

    ProjectDto updateProject(ProjectDto projectDto);

    long getCountByDepartment(long id);

    Long countAllProjects();

    List<ProjectDto> getAllProjectsByUsersId(long id);

    Long countAllProjectsByUsersId(long id);

    List<ProjectDto> getAllProjectsByDepartmentId(long id);

//    List<ProjectDto> getAllProjectsByClientId(long id);
//
//    List<ProjectDto> getAllProjectsByProjectManagerId(long id);
//
//    List<ProjectDto> getAllProjectsByStatus(String status);

}
