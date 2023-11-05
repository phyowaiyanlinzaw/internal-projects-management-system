package team.placeholder.internalprojectsmanagementsystem.dto.mapper.project;

import team.placeholder.internalprojectsmanagementsystem.dto.model.project.DeliverableDto;
import team.placeholder.internalprojectsmanagementsystem.model.project.Deliverable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DeliverableMapper {
    public static DeliverableDto toDeliverableDto(Deliverable deliverable) {
        if (deliverable == null) {
            return null;
        }
        DeliverableDto deliverableDto = new DeliverableDto();
        deliverableDto.setId(deliverable.getId());
        deliverableDto.setStatus(deliverable.isStatus());
        deliverableDto.setDeliverableType(DeliverableTypeMapper.toDeliverableTypeDto(deliverable.getDeliverableTypes()));

        return deliverableDto;
    }

    public static Deliverable toDeliverable(DeliverableDto deliverableDto) {
        if (deliverableDto == null) {
            return null;
        }
        Deliverable deliverable = new Deliverable();
        deliverable.setId(deliverableDto.getId());
        deliverable.setStatus(deliverableDto.isStatus());
        deliverable.setDeliverableTypes(DeliverableTypeMapper.toDeliverableType(deliverableDto.getDeliverableType()));
        return deliverable;
    }


    public static List<DeliverableDto> toDeliverableDtos(List<Deliverable> deliverables) {
        if (deliverables == null || deliverables.isEmpty()) {
            return null;
        }
        List<DeliverableDto> deliverableDtoList = new ArrayList<>();
        for (
                Deliverable deliverable : deliverables
        ) {
            DeliverableDto deliverable1 = new DeliverableDto();
            deliverable1.setId(deliverable.getId());
            deliverable1.setStatus(deliverable.isStatus());
            deliverable1.setDeliverableType(DeliverableTypeMapper.toDeliverableTypeDto(deliverable.getDeliverableTypes()));
            deliverableDtoList.add(deliverable1);
        }
        return deliverableDtoList;
    }

    public static List<Deliverable> toDeliverables(List<DeliverableDto> deliverableDtos) {
        if (deliverableDtos==null || deliverableDtos.isEmpty()){
            return null;
        }
        List<Deliverable> deliverables = new ArrayList<>();
        for (DeliverableDto deliverableDto: deliverableDtos){
            deliverables.add(toDeliverable(deliverableDto));
        }
        return deliverables;
    }
}
