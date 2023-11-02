package team.placeholder.internalprojectsmanagementsystem.service.impl.project;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import team.placeholder.internalprojectsmanagementsystem.dto.mapper.project.AmountMapper;
import team.placeholder.internalprojectsmanagementsystem.dto.mapper.project.ArchitectureMapper;
import team.placeholder.internalprojectsmanagementsystem.dto.mapper.project.DeliverableMapper;
import team.placeholder.internalprojectsmanagementsystem.dto.mapper.project.ProjectMapper;
import team.placeholder.internalprojectsmanagementsystem.dto.mapper.user.ClientMapper;
import team.placeholder.internalprojectsmanagementsystem.dto.model.project.ProjectDto;
import team.placeholder.internalprojectsmanagementsystem.model.project.Architecture;
import team.placeholder.internalprojectsmanagementsystem.model.project.Project;
import team.placeholder.internalprojectsmanagementsystem.repository.project.ProjectRepository;
import team.placeholder.internalprojectsmanagementsystem.service.project.ProjectService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {
    private final ProjectRepository projectRepository;


    @Override
    public ProjectDto save(ProjectDto projectDto) {
        Project project = ProjectMapper.toProject(projectDto);
        project.setName(projectDto.getName());
        project.setClient(ClientMapper.toClient(projectDto.getClientDto()));
        project.setAmount(AmountMapper.toAmount(projectDto.getAmountDto()));
        project.setStart_date(projectDto.getStart_date());
        project.setEnd_date(projectDto.getEnd_date());
        project.setBackground(projectDto.getBackground());
        project.setCurrent_phase(projectDto.getCurrent_phase());
        project.setDuration(projectDto.getDuration());
        project.setDeliverables(DeliverableMapper.toDeliverables(projectDto.getDeliverableDto()));
        project.setObjective(projectDto.getObjective());

        Set<Architecture> arList = new HashSet<>();


        project.setArchitectures(ArchitectureMapper.toArchitectures(projectDto.getArchitectureDto()));

        Project savedProject = projectRepository.save(project);
        return ProjectMapper.toProjectDto(savedProject);
    }

    @Override
    public List<ProjectDto> getAllProjects() {
        List<Project> projectList = projectRepository.findAll();
        return projectList.stream()
                .map(ProjectMapper::toProjectDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProjectDto getProjectById(long id) {
        Project project = projectRepository.findById(id);
        if (project != null) {
            return ProjectMapper.toProjectDto(project);
        } else {
            return null;
        }
    }

    @Override
    public ProjectDto getProjectByName(String name) {
        Project project = projectRepository.findByName(name);
        System.out.println(project);
        if (project != null) {
            return ProjectMapper.toProjectDto(project);
        } else {
            return null;
        }
    }

    @Override
    public ProjectDto updateProject(ProjectDto projectDto) {
        Project project = projectRepository.findById(projectDto.getId());

        if (project != null) {
            project.setName(projectDto.getName());
            project.setClient(ClientMapper.toClient(projectDto.getClientDto()));
            project.setAmount(AmountMapper.toAmount(projectDto.getAmountDto()));
            project.setStart_date(projectDto.getStart_date());
            project.setEnd_date(projectDto.getEnd_date());
            project.setBackground(projectDto.getBackground());
            project.setCurrent_phase(projectDto.getCurrent_phase());
            project.setDuration(projectDto.getDuration());
            project.setArchitectures(ArchitectureMapper.toArchitectures(projectDto.getArchitectureDto()));
            project.setDeliverables(DeliverableMapper.toDeliverables(projectDto.getDeliverableDto()));
            project.setObjective(projectDto.getObjective());
            projectRepository.save(project);
            return ProjectMapper.toProjectDto(project);
        } else {
            return null;
        }

    }

    @Override
    public long getCountByDepartment(long id) {

        return projectRepository.countByDepartmentId(id);
    }

    @Override
    public Long countAllProjects() {
        return projectRepository.count();
    }

    @Override
    public Long countByUserId(long id) {
        return projectRepository.countByUserId(id);
    }

    @Override
    public List<ProjectDto> getAllProjectsByUserId(long id) {
        return projectRepository.findAllByUserId(id).stream()
                .map(ProjectMapper::toProjectDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProjectDto> getAllProjectsByDepartmentId(long id) {
        return projectRepository.findAllByDepartmentId(id).stream()
                .map(ProjectMapper::toProjectDto)
                .collect(Collectors.toList());
    }


}
