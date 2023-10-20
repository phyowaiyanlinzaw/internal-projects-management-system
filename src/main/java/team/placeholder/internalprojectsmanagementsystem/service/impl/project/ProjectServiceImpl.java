package team.placeholder.internalprojectsmanagementsystem.service.impl.project;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import team.placeholder.internalprojectsmanagementsystem.dto.mapper.department.DepartmentMapper;
import team.placeholder.internalprojectsmanagementsystem.dto.mapper.project.ProjectMapper;
import team.placeholder.internalprojectsmanagementsystem.dto.model.project.ProjectDto;
import team.placeholder.internalprojectsmanagementsystem.model.project.Project;
import team.placeholder.internalprojectsmanagementsystem.repository.project.ProjectRepository;
import team.placeholder.internalprojectsmanagementsystem.service.project.ProjectService;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProjectServiceImpl  implements ProjectService {
    private final ProjectRepository projectRepository;


    @Override
    public ProjectDto save(ProjectDto projectDto) {
        Project project= new Project();
        project.setId(projectDto.getId());
        project.setName(projectDto.getName());
        project.setBackground(projectDto.getBackground());
        project.setStart_date(projectDto.getStart_date());
        project.setEnd_date(projectDto.getEnd_date());
        project.setObjective(projectDto.getObjective());
        project.setDuration(projectDto.getDuration());
        project.setCurrent_phase(projectDto.getCurrent_phase());
        project.setClient(projectDto.getClient());
        project.setDepartment(projectDto.getDepartment());
        project.setUser(projectDto.getUser());
        return ProjectMapper.toProjectDto(project);


    }

    @Override
    public List<ProjectDto> getAllProjects() {
        List<Project> projects = projectRepository.findAll();
        return projects.stream()
                .map(ProjectMapper::toProjectDto)
                .collect(java.util.stream.Collectors.toList());
    }

    @Override
    public ProjectDto getProjectById(long id) {
        Project project= projectRepository.findById(id);
        if(project != null){
            return ProjectMapper.toProjectDto(project);

        }else {
            return null;
        }
    }

    @Override
    public ProjectDto getProjectByName(String name) {
        Project project=projectRepository.findByName(name);
        if (project!= null){
            return ProjectMapper.toProjectDto(project);

        }else {
            return null;
        }
    }

    @Override
    public ProjectDto updateProject(ProjectDto projectDto) {
        Project project= projectRepository.findById(projectDto.getId());
        if(project!= null){
            project.setId(projectDto.getId());
            project.setName(projectDto.getName());
            project.setBackground(projectDto.getBackground());
            project.setStart_date(projectDto.getStart_date());
            project.setEnd_date(projectDto.getEnd_date());
            project.setObjective(projectDto.getObjective());
            project.setDuration(projectDto.getDuration());
            project.setCurrent_phase(projectDto.getCurrent_phase());
            project.setClient(projectDto.getClient());
            project.setDepartment(projectDto.getDepartment());
            project.setUser(projectDto.getUser());
            return ProjectMapper.toProjectDto(project);
        }else {
            return null;
        }
    }

    @Override
    public void deleteProject(long id) {

    }
}
