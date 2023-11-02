package team.placeholder.internalprojectsmanagementsystem.service.impl.project;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import team.placeholder.internalprojectsmanagementsystem.dto.mapper.project.ArchitectureMapper;
import team.placeholder.internalprojectsmanagementsystem.dto.model.project.ArchitectureDto;
import team.placeholder.internalprojectsmanagementsystem.model.project.Architecture;
import team.placeholder.internalprojectsmanagementsystem.repository.project.ArchitectureRepository;
import team.placeholder.internalprojectsmanagementsystem.service.project.ArchitectureService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ArchitectureServiceImpl implements ArchitectureService {
    private final ArchitectureRepository architectureRepository;
    @Override
    public List<ArchitectureDto> getAllArchitecture() {
       List<Architecture> architectureList = architectureRepository.findAll();
       return architectureList.stream()
               .map(ArchitectureMapper :: toArchitectureDto)
               .collect(Collectors.toList());
    }
    @Override
    public Architecture save(ArchitectureDto architecture) {
        // TODO Auto-generated method stub
        return(architectureRepository.save(ArchitectureMapper.toArchitecture(architecture)));
    }
    
}
