package team.placeholder.internalprojectsmanagementsystem.dto.mapper.project;

import org.springframework.stereotype.Component;
import team.placeholder.internalprojectsmanagementsystem.dto.mapper.user.ClientMapper;
import team.placeholder.internalprojectsmanagementsystem.dto.mapper.user.UserMapper;
import team.placeholder.internalprojectsmanagementsystem.dto.model.project.ProjectDto;
import team.placeholder.internalprojectsmanagementsystem.model.project.Project;

public class ProjectMapper {
    public static ProjectDto toProjectDto(Project project) {
        if (project == null) {
            return null;
        }
        ProjectDto projectDto = new ProjectDto();
        System.out.println("Systemout" + project.getSystemOutLine());
        projectDto.setId(project.getId());
        projectDto.setName(project.getName());
        projectDto.setBackground(project.getBackground());
        projectDto.setStart_date(project.getStart_date());
        projectDto.setEnd_date(project.getEnd_date());
        projectDto.setObjective(project.getObjective());
        projectDto.setCurrent_phase(project.getCurrent_phase());
        projectDto.setUserDto(UserMapper.toUserDto(project.getProjectManager()));
        projectDto.setDuration(project.getDuration());
        projectDto.setReviewDto(ReviewMapper.toReviewDto(project.getReviews()));
        projectDto.setAmountDto(AmountMapper.toAmountDto(project.getAmount()));
        projectDto.setClientDto(ClientMapper.toClientDto(project.getClient()));
        projectDto.setArchitectureDto(ArchitectureMapper.toArchitectureDtos(project.getArchitectures()));
        projectDto.setAmountDto(AmountMapper.toAmountDto(project.getAmount()));
        projectDto.setDeliverableDto(DeliverableMapper.toDeliverableDtos(project.getDeliverables()));
        projectDto.setSystemOutLineDto(SystemOutlineMapper.toSystemOutLineDto(project.getSystemOutLine()));

        return projectDto;
    }

    public static Project toProject(ProjectDto projectDto) {
        if (projectDto == null) {
            return null;
        }
        Project project = new Project();
        project.setName(projectDto.getName());
        project.setBackground(projectDto.getBackground());
        project.setStart_date(projectDto.getStart_date());
        project.setEnd_date(projectDto.getEnd_date());
        project.setObjective(projectDto.getObjective());
        project.setCurrent_phase(projectDto.getCurrent_phase());
        project.setProjectManager(UserMapper.toUser(projectDto.getUserDto()));
        project.setDuration(projectDto.getDuration());
        project.setAmount(AmountMapper.toAmount(projectDto.getAmountDto()));
        project.setClient(ClientMapper.toClient(projectDto.getClientDto()));
        project.setArchitectures(ArchitectureMapper.toArchitectures(projectDto.getArchitectureDto()));
        project.setAmount(AmountMapper.toAmount(projectDto.getAmountDto()));
        project.setDeliverables(DeliverableMapper.toDeliverables(projectDto.getDeliverableDto()));
        project.setSystemOutLine(SystemOutlineMapper.toSystemOutline(projectDto.getSystemOutLineDto()));
        return project;
}}
