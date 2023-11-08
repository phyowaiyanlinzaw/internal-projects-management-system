package team.placeholder.internalprojectsmanagementsystem.dto.mapper.user;

import team.placeholder.internalprojectsmanagementsystem.dto.mapper.project.*;
import team.placeholder.internalprojectsmanagementsystem.dto.model.project.ProjectDto;
import team.placeholder.internalprojectsmanagementsystem.dto.model.user.ClientDto;
import team.placeholder.internalprojectsmanagementsystem.dto.model.user.UserDto;
import team.placeholder.internalprojectsmanagementsystem.model.project.Project;
import team.placeholder.internalprojectsmanagementsystem.model.user.User;

import java.util.ArrayList;
import java.util.List;

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
            userDto.setProjectManager(toUserDto(user.getProjectManager()));
        }

        List<ProjectDto> projectList = new ArrayList<>();

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
            projectDto.setSystemOutLineDto(SystemOutlineMapper.toSystemOutLineDto(project.getSystemOutLine()));
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
            projectDto.setArchitectureDto(ArchitectureMapper.toArchitectureDtos(project.getArchitectures()));

            projectList.add(projectDto);
        }


        // Convert Department to DepartmentDto

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

        // Convert DepartmentDto to Department using DepartmentMappe
        return user;
    }



}
