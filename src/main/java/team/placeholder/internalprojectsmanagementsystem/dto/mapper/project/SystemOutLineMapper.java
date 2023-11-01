package team.placeholder.internalprojectsmanagementsystem.dto.mapper.project;

import team.placeholder.internalprojectsmanagementsystem.dto.model.project.SystemOutLineDto;
import team.placeholder.internalprojectsmanagementsystem.model.project.SystemOutLine;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    public  static List<SystemOutLineDto> tosystemOutLineDtos(List<SystemOutLine> SystemOutLines){
        if (SystemOutLines == null || SystemOutLines.isEmpty()){
            return null;
        }
        List<SystemOutLineDto> systemOutLineDtoList = new ArrayList<>();
        for (SystemOutLine systemOutLine : SystemOutLines){
            systemOutLineDtoList.add(toSystemOutLineDto(systemOutLine));
        }
        return systemOutLineDtoList;
    }


    public static List<SystemOutLine> tosystemOutLines(List<SystemOutLineDto> systemOutLineDtos) {
        if (systemOutLineDtos == null || systemOutLineDtos.isEmpty()) {
            return null;
        }
        List<SystemOutLine> systemOutLines = new ArrayList<>();
        for (SystemOutLineDto systemOutLineDto : systemOutLineDtos) {
            systemOutLines.add(toSystemOutline(systemOutLineDto));
        }
        return systemOutLines;
    }
}
