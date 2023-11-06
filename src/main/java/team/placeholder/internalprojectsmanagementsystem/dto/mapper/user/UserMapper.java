package team.placeholder.internalprojectsmanagementsystem.dto.mapper.user;

import org.springframework.security.core.parameters.P;
import team.placeholder.internalprojectsmanagementsystem.dto.mapper.department.DepartmentMapper;
import team.placeholder.internalprojectsmanagementsystem.dto.mapper.project.*;
import team.placeholder.internalprojectsmanagementsystem.dto.model.project.ProjectDto;
import team.placeholder.internalprojectsmanagementsystem.dto.model.user.ClientDto;
import team.placeholder.internalprojectsmanagementsystem.dto.model.user.UserDto;
import team.placeholder.internalprojectsmanagementsystem.model.project.Project;
import team.placeholder.internalprojectsmanagementsystem.model.user.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UserMapper {
    public static UserDto toUserDto(User user) {
        if (user == null) {
            return null;
        }
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        userDto.setRole(user.getRole());

        if(user.getProjectManager() != null) {
            UserDto userDto1 = new UserDto();
                    userDto1.setId(user.getProjectManager().getId());
                    userDto1.setName(user.getProjectManager().getName());
                    userDto1.setEmail(user.getProjectManager().getEmail());
            userDto.setProjectManager(userDto1);
        }

        List<ProjectDto> projectList = new ArrayList<>();

        if(user.getProject() != null) {
            for (Project project : user.getProject()) {
                ProjectDto projectDto = new ProjectDto();

                projectDto.setId(project.getId());
                projectDto.setName(project.getName());
                projectDto.setStart_date(project.getStart_date());
                projectDto.setEnd_date(project.getEnd_date());
                projectDto.setBackground(project.getBackground());
                projectDto.setCurrent_phase(project.getCurrent_phase());
                projectDto.setDuration(project.getDuration());
                projectDto.setObjective(project.getObjective());
                projectDto.setSystemOutLineDto(SystemOutLineMapper.toSystemOutLineDto(project.getSystemOutLine()));
                projectDto.setDeliverableDto(DeliverableMapper.toDeliverableDtos(project.getDeliverables()));
                ClientDto clientDto = new ClientDto();
                clientDto.setId(project.getClient().getId());
                clientDto.setName(project.getClient().getName());
                clientDto.setEmail(project.getClient().getEmail());
                clientDto.setPhone(project.getClient().getPhone());
                UserDto projectManager = new UserDto();
                projectManager.setId(project.getProjectManager().getId());
                projectManager.setName(project.getProjectManager().getName());
                projectManager.setEmail(project.getProjectManager().getEmail());
                projectManager.setRole(project.getProjectManager().getRole());
                projectDto.setUserDto(projectManager);
                projectDto.setClientDto(clientDto);

                projectDto.setAmountDto(AmountMapper.toAmountDto(project.getAmount()));
                projectDto.setDepartmentDto(DepartmentMapper.toDepartmentDto(project.getDepartment()));
                projectDto.setArchitectureDto(ArchitectureMapper.toArchitectureDtos(project.getArchitectures()));

                projectList.add(projectDto);
            }
        }

        userDto.setProjectList(projectList);

        // Convert Department to DepartmentDto
        userDto.setDepartmentdto(DepartmentMapper.toDepartmentDto(user.getDepartment()));

        return userDto;
    }

    public static User toUser(UserDto userDto) {
        if (userDto == null) {
            return null;
        }
        User user = new User();
        user.setId(userDto.getId());
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setRole(userDto.getRole());

        // Convert DepartmentDto to Department using DepartmentMapper
        if (userDto.getDepartmentdto() != null) {
            user.setDepartment(DepartmentMapper.toDepartment(userDto.getDepartmentdto()));
        } else {
            user.setDepartment(null); // or handle this case as needed
        }

        return user;
    }



}
