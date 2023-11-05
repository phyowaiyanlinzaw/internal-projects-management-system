package team.placeholder.internalprojectsmanagementsystem.service.impl.project;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import team.placeholder.internalprojectsmanagementsystem.dto.mapper.project.DeliverableTypeMapper;
import team.placeholder.internalprojectsmanagementsystem.dto.model.project.DeliverableTypeDto;
import team.placeholder.internalprojectsmanagementsystem.model.project.DeliverableType;
import team.placeholder.internalprojectsmanagementsystem.repository.project.DeliverableTypeRepo;
import team.placeholder.internalprojectsmanagementsystem.service.project.DeliverableTypeService;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DeliverableTypeServiceImpl  implements DeliverableTypeService {

    private final DeliverableTypeRepo deliverableTypeRepo;

    @Override
    public DeliverableType save(DeliverableTypeDto deliverableTypeDto) {

        return deliverableTypeRepo.save(DeliverableTypeMapper.toDeliverableType(deliverableTypeDto));
    }

    @Override
    public Set<DeliverableTypeDto> getAll() {
        return deliverableTypeRepo.findAll().stream().map(DeliverableTypeMapper::toDeliverableTypeDto).collect(Collectors.toSet());
    }

}
