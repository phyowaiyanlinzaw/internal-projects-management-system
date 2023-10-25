package team.placeholder.internalprojectsmanagementsystem.dto.mapper.project;

import org.springframework.stereotype.Component;
import team.placeholder.internalprojectsmanagementsystem.dto.mapper.department.DepartmentMapper;
import team.placeholder.internalprojectsmanagementsystem.dto.mapper.user.ClientMapper;
import team.placeholder.internalprojectsmanagementsystem.dto.mapper.user.UserMapper;
import team.placeholder.internalprojectsmanagementsystem.dto.model.project.ProjectDto;
import team.placeholder.internalprojectsmanagementsystem.model.project.Project;

@Component
public class ProjectMapper {
    public static ProjectDto toProjectDto(Project project) {
        if (project == null) {
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
        projectDto.setClientDto(ClientMapper.toClientDto(project.getClient())); // Convert Client to ClientDto

        // Convert User to UserDto using UserMapper
        projectDto.setUserDto(UserMapper.toUserDto(project.getUser()));

        projectDto.setDepartmentDto(DepartmentMapper.toDepartmentDto(project.getDepartment())); // Convert Department to DepartmentDto
        return projectDto;
    }

    public static Project toProject(ProjectDto projectDto) {
        if (projectDto == null) {
            return null;
        }

        Project project = new Project();
        project.setId(projectDto.getId());
        project.setName(projectDto.getName());
        project.setBackground(projectDto.getBackground());
        project.setDuration(projectDto.getDuration());
        project.setStart_date(projectDto.getStart_date());
        project.setEnd_date(projectDto.getEnd_date());
        project.setCurrent_phase(projectDto.getCurrent_phase());
        project.setObjective(projectDto.getObjective());

        // Convert ClientDto to Client using ClientMapper
        project.setClient(ClientMapper.toClient(projectDto.getClientDto()));

        // Convert UserDto to User using UserMapper
        project.setUser(UserMapper.toUser(projectDto.getUserDto()));

        project.setDepartment(DepartmentMapper.toDepartment(projectDto.getDepartmentDto())); // Convert DepartmentDto to Department
        return project;
    }
}
