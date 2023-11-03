package team.placeholder.internalprojectsmanagementsystem.dto.mapper.project;

import org.springframework.stereotype.Component;
import team.placeholder.internalprojectsmanagementsystem.dto.mapper.department.DepartmentMapper;
import team.placeholder.internalprojectsmanagementsystem.dto.mapper.user.ClientMapper;
import team.placeholder.internalprojectsmanagementsystem.dto.mapper.user.UserMapper;
import team.placeholder.internalprojectsmanagementsystem.dto.model.project.ArchitectureDto;
import team.placeholder.internalprojectsmanagementsystem.dto.model.project.ProjectDto;
import team.placeholder.internalprojectsmanagementsystem.model.project.Project;

import java.util.Set;

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
        projectDto.setStart_date(project.getStart_date());
        projectDto.setEnd_date(project.getEnd_date());
        projectDto.setObjective(project.getObjective());
        projectDto.setCurrent_phase(project.getCurrent_phase());
        projectDto.setDuration(project.getDuration());

        projectDto.setClientDto(ClientMapper.toClientDto(project.getClient()));
        // Convert Client to ClientDto


        projectDto.setArchitectureDto(ArchitectureMapper.toArchitectureDtos(project.getArchitectures()));
        //Convert Architecture to ArchitectureDto


        projectDto.setDeliverableDto(DeliverableMapper.toDeliverableDtos(project.getDeliverables()));
        //Deliverable to DeliverableDto


        projectDto.setSystemOutLineDto(SystemOutLineMapper.toSystemOutLineDto(project.getSystemOutLine()));
        // Convert SystemOutLinesDto to SystemOutLines using UserMapper




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
        project.setDuration(projectDto.getDuration());

        project.setAmount(AmountMapper.toAmount(projectDto.getAmountDto()));

        project.setClient(ClientMapper.toClient(projectDto.getClientDto()));
        // Convert Client to ClientDto

        project.setArchitectures(ArchitectureMapper.toArchitectures(projectDto.getArchitectureDto()));
        //Convert Architecture to ArchitectureDto
        project.setAmount(AmountMapper.toAmount(projectDto.getAmountDto()));

        project.setDeliverables(DeliverableMapper.toDeliverables(projectDto.getDeliverableDto()));
        //Deliverable to DeliverableDto

        project.setSystemOutLine(SystemOutLineMapper.toSystemOutline(projectDto.getSystemOutLineDto()));
        // Convert SystemOutLinesDto to SystemOutLines using UserMapper
        return project;
}}
