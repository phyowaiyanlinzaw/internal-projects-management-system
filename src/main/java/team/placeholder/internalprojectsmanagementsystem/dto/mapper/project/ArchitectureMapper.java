package team.placeholder.internalprojectsmanagementsystem.dto.mapper.project;

import team.placeholder.internalprojectsmanagementsystem.dto.model.project.ArchitectureDto;
import team.placeholder.internalprojectsmanagementsystem.model.project.Architecture;

public class ArchitectureMapper {
    public static ArchitectureDto toArchitectureDto(Architecture architecture){
        if(architecture == null){
            return null;
        }
        ArchitectureDto architectureDto = new ArchitectureDto();
        architectureDto.setId(architecture.getId());
        architectureDto.setTech_name(architecture.getTech_name());
        return architectureDto;
    }
}
