package team.placeholder.internalprojectsmanagementsystem.service.project;

import team.placeholder.internalprojectsmanagementsystem.dto.model.project.ProjectStatusDto;

public interface ProjectStatusService {

    ProjectStatusDto save(ProjectStatusDto projectStatusDto);

    ProjectStatusDto update(ProjectStatusDto projectStatusDto);


}
