package team.placeholder.internalprojectsmanagementsystem.dto.mapper.project;

import team.placeholder.internalprojectsmanagementsystem.dto.model.project.SystemOutLineDto;
import team.placeholder.internalprojectsmanagementsystem.model.project.SystemOutLine;

import java.util.ArrayList;
import java.util.List;

public class SystemOutlineMapper {
    public static SystemOutLineDto toSystemOutLineDto(SystemOutLine systemOutLine){
        if(systemOutLine == null){
            return null;
        }
        SystemOutLineDto systemOutLineDto = new SystemOutLineDto();
        systemOutLineDto.setId(systemOutLine.getId());
        systemOutLineDto.setAnalysis(systemOutLine.isAnalysis());
        systemOutLineDto.setSys_design(systemOutLine.isSys_design());
        systemOutLineDto.setCoding(systemOutLine.isCoding());
        systemOutLineDto.setTesting(systemOutLine.isTesting());
        systemOutLineDto.setDeploy(systemOutLine.isDeploy());
        systemOutLineDto.setMaintenance(systemOutLine.isMaintenance());
        systemOutLineDto.setDocument(systemOutLine.isDocument());
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
