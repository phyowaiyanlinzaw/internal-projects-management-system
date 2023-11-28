package team.placeholder.internalprojectsmanagementsystem.service.project;

import team.placeholder.internalprojectsmanagementsystem.dto.model.project.DeliverableDto;
import team.placeholder.internalprojectsmanagementsystem.dto.model.project.DeliverableTypeDto;

import java.util.List;
import java.util.Set;

public interface DeliverableService {

    DeliverableDto save(DeliverableDto deliverableDto);



    DeliverableDto updateDeliverable(DeliverableDto deliverableDto);

}
