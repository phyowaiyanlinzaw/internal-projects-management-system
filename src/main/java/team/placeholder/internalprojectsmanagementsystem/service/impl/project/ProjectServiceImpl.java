//package team.placeholder.internalprojectsmanagementsystem.service.impl.project;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import team.placeholder.internalprojectsmanagementsystem.dto.mapper.project.ProjectMapper;
//import team.placeholder.internalprojectsmanagementsystem.dto.model.project.ProjectDto;
//import team.placeholder.internalprojectsmanagementsystem.model.project.Project;
//import team.placeholder.internalprojectsmanagementsystem.repository.project.ProjectRepository;
//import team.placeholder.internalprojectsmanagementsystem.service.project.ProjectService;
//
//import java.util.List;
//import java.util.Optional;
//import java.util.stream.Collectors;
//
//@Service
//@RequiredArgsConstructor
//public class ProjectServiceImpl implements ProjectService {
//    private final ProjectRepository projectRepository;
//    private final ProjectMapper projectMapper;
//
//
//    @Override
//    public ProjectDto  save(ProjectDto projectDto) {
//        Project project = projectMapper.toProject(projectDto);
//        Project savedProject = projectRepository.save(project);
//        return projectMapper.toProjectDto(savedProject);
//    }
//
//    @Override
//    public List<ProjectDto> getAllProjects() {
//        List<Project> projects = projectRepository.findAll();
//        return projects.stream()
//                .map(projectMapper::toProjectDto)
//                .collect(Collectors.toList());
//
//    }
//
//    @Override
//    public ProjectDto getProjectById(long id) {
//        return null;
//    }
//
//
//    @Override
//    public ProjectDto getProjectById(int id) {
//        Optional<Project> optionalProject = Optional.ofNullable(projectRepository.findById(id));
//
//        if (optionalProject.isPresent()) {
//            Project project = optionalProject.get();
//            return projectMapper.toProjectDto(project);
//        } else {
//
//            return null;
//        }
//    }
//
//    @Override
//    public ProjectDto getProjectByName(String name) {
//        return null;
//    }
//
//    @Override
//    public ProjectDto updateProject(ProjectDto projectDto) {
//
//        Project project = projectMapper.toProject(projectDto);
//        if (projectRepository.existsById(project.getId())) {
//
//            Project updatedProject = projectRepository.save(project);
//
//            return projectMapper.toProjectDto(updatedProject);
//        } else {
//
//            return null;
//        }
//    }
//
//    @Override
//    public void deleteProject(long id) {
//
//    }
//
//}
