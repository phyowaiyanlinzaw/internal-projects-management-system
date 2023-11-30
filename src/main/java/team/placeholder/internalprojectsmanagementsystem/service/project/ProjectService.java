package team.placeholder.internalprojectsmanagementsystem.service.project;

import team.placeholder.internalprojectsmanagementsystem.dto.model.project.ProjectDto;
import team.placeholder.internalprojectsmanagementsystem.dto.model.user.ClientDto;
import team.placeholder.internalprojectsmanagementsystem.dto.model.user.UserDto;
import team.placeholder.internalprojectsmanagementsystem.dto.uidto.KPIDto;
import team.placeholder.internalprojectsmanagementsystem.model.user.User;

import java.util.List;

public interface ProjectService {

    ProjectDto save(ProjectDto projectDto);

    List<ProjectDto> getAllProjects();
    ProjectDto getProjectById(long id);
    ProjectDto getProjectByName(String name);

    ProjectDto updateProject(ProjectDto projectDto);

    Long countAllProjects();

    List<ProjectDto> getAllProjectsByProjectManagerId(long id);


    Long countAllProjectsByUsersId(long id);

    Long countAllProjectsByProjectManagerId(long id);

    Long countAllProjectsByProjectManagerIdAndClosed(long id, boolean closed);

    List<ProjectDto> getAllProjectsByDepartmentId(long id);
    Long countAllProjectsByDepartmentId(long id);

    List<ProjectDto> findAllByUserId(long id);

    List<ProjectDto> getAllProjectsByDepartmentNameAndClosed(String name, boolean closed);



//    ProjectDto getProjectByUsersIdAndStatus(long userId, boolean status);

    public void updateProjectClosed(long id, boolean condition);


    
    public void updateUserListInProject(long id , List<UserDto> users);


}
