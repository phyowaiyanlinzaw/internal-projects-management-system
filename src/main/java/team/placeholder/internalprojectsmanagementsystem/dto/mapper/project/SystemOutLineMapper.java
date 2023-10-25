package team.placeholder.internalprojectsmanagementsystem.dto.mapper.project;

import team.placeholder.internalprojectsmanagementsystem.dto.model.project.SystemOutLineDto;
import team.placeholder.internalprojectsmanagementsystem.model.project.SystemOutLine;

public class SystemOutLineMapper {
    public static SystemOutLineDto toSystemOutLineDto(SystemOutLine systemOutLine){
        if(systemOutLine == null){
            return null;
        }
        SystemOutLineDto systemOutLineDto = new SystemOutLineDto();
        systemOutLineDto.setId(systemOutLine.getId());
        systemOutLineDto.setAnalysis(false);
        systemOutLineDto.setSys_design(false);
        systemOutLineDto.setCoding(false);
        systemOutLineDto.setTesting(false);
        systemOutLineDto.setDeploy(false);
        systemOutLineDto.setMaintenance(false);
        systemOutLineDto.setDocument(false);
        return systemOutLineDto;
    }

    public static SystemOutLine toSystemOutline(SystemOutLineDto systemOutlineDto) {
        if(systemOutlineDto == null){
            return null;
        }
        SystemOutLine systemOutline = new SystemOutLine();
        systemOutline.setId(systemOutlineDto.getId());
        systemOutline.setAnalysis(systemOutlineDto.isAnalysis());
        systemOutline.setSys_design(systemOutlineDto.isSys_design());
        systemOutline.setCoding(systemOutlineDto.isCoding());
        systemOutline.setTesting(systemOutlineDto.isTesting());
        systemOutline.setDeploy(systemOutlineDto.isDeploy());
        systemOutline.setMaintenance(systemOutlineDto.isMaintenance());
        systemOutline.setDocument(systemOutlineDto.isDocument());
        return systemOutline;
    }
}
