package team.placeholder.internalprojectsmanagementsystem.service.impl.project;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import team.placeholder.internalprojectsmanagementsystem.dto.mapper.project.SystemOutlineMapper;
import team.placeholder.internalprojectsmanagementsystem.dto.model.project.SystemOutLineDto;
import team.placeholder.internalprojectsmanagementsystem.model.project.SystemOutLine;
import team.placeholder.internalprojectsmanagementsystem.repository.project.SystemOutlineRepository;
import team.placeholder.internalprojectsmanagementsystem.service.project.SystemOutlineService;

import java.util.List;

@Service
@AllArgsConstructor
public class SystemOutlineServiceImpl implements SystemOutlineService {

    private final SystemOutlineRepository systemOutlineRepository;
    @Override
    public SystemOutLineDto save(SystemOutLineDto systemOutLineDto) {
        SystemOutLine systemOutLine = SystemOutlineMapper.toSystemOutline(systemOutLineDto);
        return SystemOutlineMapper.toSystemOutLineDto(systemOutlineRepository.save(systemOutLine));
    }

    @Override
    public List<SystemOutLineDto> getAllSystemOutlines() {
        return systemOutlineRepository.findAll().stream().map(SystemOutlineMapper::toSystemOutLineDto).toList();
    }
}
