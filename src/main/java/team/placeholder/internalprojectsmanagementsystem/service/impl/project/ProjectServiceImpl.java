//package team.placeholder.internalprojectsmanagementsystem.service.impl.project;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import team.placeholder.internalprojectsmanagementsystem.dto.mapper.project.ProjectMapper;
//import team.placeholder.internalprojectsmanagementsystem.dto.model.project.ProjectDto;
//import team.placeholder.internalprojectsmanagementsystem.model.department.Department;
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
//        Project project = new Project();
//        project.setId(projectDto.getId());
//        project.setUser(projectDto.getUser());
//        project.setClient(projectDto.getClient());
//        project.setDepartment(projectDto.getDepartment());
//        project.setObjective(projectDto.getObjective());
//        project.setDuration(projectDto.getDuration());
//        project.setName(projectDto.getName());
//        project.setBackground(projectDto.getBackground());
//        project.setEnd_date(projectDto.getEnd_date());
//        project.setCurrent_phase(projectDto.getCurrent_phase());
//        project.setStart_date(projectDto.getStart_date());
//        project= projectRepository.save(project);
//        return ProjectMapper.toProjectDto(project);
//    }
//
//    @Override
//    public List<ProjectDto> getAllProjects() {
//        List<Project> projects = projectRepository.findAll();
//       return projects.stream().map(ProjectMapper::toProjectDto).collect(java.util.stream.Collectors.toList());
//
//    }
//
//    @Override
//    public ProjectDto getProjectById(long id) {
//
//        Project project = projectRepository.findById(id);
//        if(project!=null){
//            return ProjectMapper.toProjectDto(project);
//
//        }else {
//            return null;
//        }
//
//    }
//
//
//
//
//    @Override
//    public ProjectDto getProjectByName(String name) {
//        return null;
//    }
//
//    @Override
//    public ProjectDto updateProject(ProjectDto projectDto) {
//
//    Project project= projectRepository.findById(projectDto.getId()).orElse(null);
//    if(project != null){
//        project.setId(projectDto.getId());
//        project.setUser(projectDto.getUser());
//        project.setClient(projectDto.getClient());
//        project.setDepartment(projectDto.getDepartment());
//        project.setObjective(projectDto.getObjective());
//        project.setDuration(projectDto.getDuration());
//        project.setName(projectDto.getName());
//        project.setBackground(projectDto.getBackground());
//        project.setEnd_date(projectDto.getEnd_date());
//        project.setCurrent_phase(projectDto.getCurrent_phase());
//        project.setStart_date(projectDto.getStart_date());
//        project= projectRepository.save(project);
//        return ProjectMapper.toProjectDto(project);
//
//    }else{
//        return null;
//    }
//    }
//
//    @Override
//    public void deleteProject(long id) {
//        Project project= projectRepository.findById(id);
//
//            projectRepository.delete(project);
//
//    }
//
//}
