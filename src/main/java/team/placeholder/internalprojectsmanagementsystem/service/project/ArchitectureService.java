package team.placeholder.internalprojectsmanagementsystem.service.project;

import team.placeholder.internalprojectsmanagementsystem.dto.model.project.ArchitectureDto;
import team.placeholder.internalprojectsmanagementsystem.dto.model.project.ProjectDto;
import team.placeholder.internalprojectsmanagementsystem.model.project.Architecture;

import java.util.List;

public interface ArchitectureService {
    List<ArchitectureDto> getAllArchitecture();
    Architecture save(ArchitectureDto architectureDto);
}
