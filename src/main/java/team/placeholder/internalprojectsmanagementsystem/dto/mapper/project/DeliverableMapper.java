package team.placeholder.internalprojectsmanagementsystem.dto.mapper.project;

import team.placeholder.internalprojectsmanagementsystem.dto.model.project.DeliverableDto;
import team.placeholder.internalprojectsmanagementsystem.model.project.Deliverable;

public class DeliverableMapper {
    public static DeliverableDto toDeliverableDto(Deliverable deliverable) {
        if (deliverable == null) {
            return null;
        }
        DeliverableDto deliverableDto = new DeliverableDto();
        deliverableDto.setId(deliverable.getId());
        deliverableDto.setStatus(deliverable.isStatus());
        deliverableDto.setType(deliverable.getType());

        return deliverableDto;
    }

    public static Deliverable toDeliverable(DeliverableDto deliverableDto) {
        if (deliverableDto == null) {
            return null;
        }
        Deliverable deliverable = new Deliverable();
        deliverable.setId(deliverableDto.getId());
        deliverable.setStatus(deliverableDto.isStatus());
        deliverable.setType(deliverableDto.getType());
        return deliverable;
    }
}
