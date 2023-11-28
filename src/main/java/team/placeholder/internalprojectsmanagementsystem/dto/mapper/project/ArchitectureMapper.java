package team.placeholder.internalprojectsmanagementsystem.dto.mapper.project;

import team.placeholder.internalprojectsmanagementsystem.dto.model.project.ArchitectureDto;
import team.placeholder.internalprojectsmanagementsystem.model.project.Architecture;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    public static Architecture toArchitecture(ArchitectureDto architectureDto){
        if (architectureDto==null){
            return null;
        }

        Architecture architecture = new Architecture();
        architecture.setId(architectureDto.getId());
        architecture.setTech_name(architectureDto.getTech_name());
        return architecture;


    }

    public static Set<ArchitectureDto> toArchitectureDtos(Set<Architecture> architectures) {
        if (architectures == null || architectures.isEmpty()) {
            return null;
        }

        Set<ArchitectureDto> architectureDtoList = new HashSet<>();
        for (Architecture architecture : architectures) {
            architectureDtoList.add(toArchitectureDto(architecture));
        }

        return architectureDtoList;
    }

    public static Set<Architecture> toArchitectures(Set<ArchitectureDto> architectureDtos) {
        if (architectureDtos== null || architectureDtos.isEmpty()){
            return null;
        }
        Set<Architecture> architectures = new HashSet<>();
        for (ArchitectureDto architectureDto : architectureDtos){
            System.out.println(architectureDto);
            architectures.add(toArchitecture(architectureDto));
        }
        System.out.println(architectures);
        return architectures;
    }
}
