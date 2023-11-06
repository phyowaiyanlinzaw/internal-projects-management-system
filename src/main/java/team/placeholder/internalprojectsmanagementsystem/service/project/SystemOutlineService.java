package team.placeholder.internalprojectsmanagementsystem.service.project;

import team.placeholder.internalprojectsmanagementsystem.dto.model.project.SystemOutLineDto;

import java.util.List;

public interface SystemOutlineService {

    SystemOutLineDto save(SystemOutLineDto systemOutLineDto);

    List<SystemOutLineDto> getAllSystemOutlines();
}
