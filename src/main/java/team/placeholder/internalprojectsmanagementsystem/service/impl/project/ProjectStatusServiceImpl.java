package team.placeholder.internalprojectsmanagementsystem.service.impl.project;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import team.placeholder.internalprojectsmanagementsystem.dto.mapper.project.ProjectStatusMapper;
import team.placeholder.internalprojectsmanagementsystem.dto.model.project.ProjectStatusDto;
import team.placeholder.internalprojectsmanagementsystem.model.project.ProjectStatus;
import team.placeholder.internalprojectsmanagementsystem.repository.project.ProjectStatusRepository;
import team.placeholder.internalprojectsmanagementsystem.service.project.ProjectStatusService;


@Service
@Slf4j
@RequiredArgsConstructor
public class ProjectStatusServiceImpl implements ProjectStatusService {

    private  final ProjectStatusRepository projectStatusRepository;

    @Override
    public ProjectStatusDto save(ProjectStatusDto projectStatusDto) {
       ProjectStatus projectStatus = new ProjectStatus();
       projectStatus.setName(projectStatusDto.getName());
       projectStatusRepository.save(projectStatus);
       return ProjectStatusMapper.toProjectStatusDto(projectStatusRepository.save(projectStatus));
    }
    @Override
    public ProjectStatusDto update(ProjectStatusDto projectStatusDto) {
        ProjectStatus projectStatus = projectStatusRepository.findById(projectStatusDto.getId());
        if (projectStatus != null) {
            projectStatus.setName(projectStatusDto.getName());
            projectStatusRepository.save(projectStatus);
            return ProjectStatusMapper.toProjectStatusDto(projectStatusRepository.save(projectStatus));
        } else {
            return null;

        }
    }

}
