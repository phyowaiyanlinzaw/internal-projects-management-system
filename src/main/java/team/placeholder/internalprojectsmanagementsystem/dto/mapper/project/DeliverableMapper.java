package team.placeholder.internalprojectsmanagementsystem.dto.mapper.project;

import team.placeholder.internalprojectsmanagementsystem.dto.model.project.DeliverableDto;
import team.placeholder.internalprojectsmanagementsystem.model.project.Deliverable;

public class DeliverableMapper {
    public static DeliverableDto toDelivarableDto(Deliverable deliverable){
        if(deliverable == null){
            return null;
        }
        DeliverableDto deliverableDto = new DeliverableDto();
        deliverableDto.setId(deliverable.getId());
        deliverableDto.setDescription(deliverable.getDescription());
        deliverableDto.setName(deliverable.getName());
        deliverableDto.setType(deliverable.getType());
        deliverableDto.setStatus(deliverable.getStatus());
        deliverableDto.setDue_date(deliverable.getDue_date());
        return deliverableDto;
    }
}
