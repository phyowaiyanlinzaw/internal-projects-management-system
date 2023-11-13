package team.placeholder.internalprojectsmanagementsystem.service.impl.project;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import team.placeholder.internalprojectsmanagementsystem.dto.model.project.DeliverableDto;
import team.placeholder.internalprojectsmanagementsystem.dto.model.project.DeliverableTypeDto;
import team.placeholder.internalprojectsmanagementsystem.model.project.Deliverable;
import team.placeholder.internalprojectsmanagementsystem.model.project.DeliverableType;
import team.placeholder.internalprojectsmanagementsystem.repository.project.DeliverableRepository;
import team.placeholder.internalprojectsmanagementsystem.service.project.DeliverableService;
import team.placeholder.internalprojectsmanagementsystem.service.project.DeliverableTypeService;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class DeliverableServiceImpl implements DeliverableService {

    private final DeliverableRepository deliverableRepository;
    private final ModelMapper modelMapper;

    @Override
    public DeliverableDto save(DeliverableDto deliverableDto) {
        return modelMapper.map(deliverableRepository.save(modelMapper.map(deliverableDto, Deliverable.class)), DeliverableDto.class);
    }

    @Override
    public List<DeliverableDto> getAll() {
        return null;
    }

}
