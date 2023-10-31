package team.placeholder.internalprojectsmanagementsystem.service.project;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import team.placeholder.internalprojectsmanagementsystem.dto.mapper.department.DepartmentMapper;
import team.placeholder.internalprojectsmanagementsystem.dto.mapper.project.DeliverableMapper;
import team.placeholder.internalprojectsmanagementsystem.dto.model.project.DeliverableDto;
import team.placeholder.internalprojectsmanagementsystem.model.department.Department;
import team.placeholder.internalprojectsmanagementsystem.model.project.Deliverable;
import team.placeholder.internalprojectsmanagementsystem.repository.project.DeliverableRepository;

@Service
@RequiredArgsConstructor
public class DeliverableServiceImpl implements DeliverableService{

    private final DeliverableRepository deliverableRepository;
    @Override
    public DeliverableDto createDeliverable(DeliverableDto deliverableDto) {

        Deliverable deliverable = new Deliverable();
        deliverable.setId(deliverableDto.getId());
        deliverable.setStatus(deliverableDto.isStatus());
        deliverable.setType(deliverableDto.getType());
        deliverable = deliverableRepository.save(deliverable);
        return DeliverableMapper.toDeliverableDto(deliverable);

    }

    @Override
    public DeliverableDto updateDeliverable(DeliverableDto deliverableDto) {
        return null;
    }
}
