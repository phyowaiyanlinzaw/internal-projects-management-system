package team.placeholder.internalprojectsmanagementsystem.service.project;

import team.placeholder.internalprojectsmanagementsystem.dto.model.project.ProjectDto;
import team.placeholder.internalprojectsmanagementsystem.dto.model.user.ClientDto;

import java.util.List;

public interface ProjectService {

    ProjectDto save(ProjectDto projectDto);

    List<ProjectDto> getAllProjects();
    ProjectDto getProjectById(long id);
    ProjectDto getProjectByName(String name);

    ProjectDto updateProject(ProjectDto projectDto);

    long getCountByDepartment(long id);

    Long countAllProjects();

    List<ProjectDto> getAllProjectsByProjectManagerId(long id);

    Long countAllProjectsByUsersId(long id);

    List<ProjectDto> getAllProjectsByDepartmentId(long id);
    Long countAllProjectsByDepartmentId(long id);

    List<ProjectDto> findAllByUserId(long id);

    List<ProjectDto> getAllProjectsByDepartmentName(String name);

    long countTaskById(long id);

    ProjectDto getProjectByUsersIdAndStatus(long users, String status);

    //get start and end month of the projects




}
