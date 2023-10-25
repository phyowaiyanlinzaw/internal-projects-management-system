package team.placeholder.internalprojectsmanagementsystem.dto.mapper.project;

import team.placeholder.internalprojectsmanagementsystem.dto.model.project.DeliverableTypeDto;
import team.placeholder.internalprojectsmanagementsystem.model.project.DeliverableType;

public class DeliverableTypeMapper {

    public static DeliverableTypeDto toDeliverableTypeDto(DeliverableType deliverableType) {
        if(deliverableType == null) {
            return null;
        }
        DeliverableTypeDto deliverableTypeDto = new DeliverableTypeDto();
        deliverableTypeDto.setId(deliverableType.getId());
        deliverableTypeDto.setType(deliverableType.getType());
        return deliverableTypeDto;
    }

    public static DeliverableType toDeliverableType(DeliverableTypeDto deliverableTypeDto) {
        if(deliverableTypeDto == null) {
            return null;
        }
        DeliverableType deliverableType = new DeliverableType();
        deliverableType.setId(deliverableTypeDto.getId());
        deliverableType.setType(deliverableTypeDto.getType());
        return deliverableType;
    }

}
