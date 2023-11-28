package team.placeholder.internalprojectsmanagementsystem.service.project;

import team.placeholder.internalprojectsmanagementsystem.dto.model.project.DeliverableTypeDto;
import team.placeholder.internalprojectsmanagementsystem.model.project.DeliverableType;
import team.placeholder.internalprojectsmanagementsystem.repository.project.DeliverableTypeRepo;

import java.util.Set;

public interface DeliverableTypeService {

    public DeliverableType save(DeliverableTypeDto deliverableTypeDto);

    public Set<DeliverableTypeDto> getAll();

}
