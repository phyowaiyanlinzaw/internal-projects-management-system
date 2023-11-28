package team.placeholder.internalprojectsmanagementsystem.service.impl.project;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import team.placeholder.internalprojectsmanagementsystem.dto.model.project.DeliverableDto;
import team.placeholder.internalprojectsmanagementsystem.model.project.Deliverable;
import team.placeholder.internalprojectsmanagementsystem.repository.project.DeliverableRepository;
import team.placeholder.internalprojectsmanagementsystem.service.project.DeliverableService;

import java.util.List;

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
    public DeliverableDto updateDeliverable(DeliverableDto deliverableDto) {
        Deliverable deliverable = deliverableRepository.findById(deliverableDto.getId());
        if (deliverable !=null){
            deliverable.setStatus(deliverableDto.isStatus());
            deliverableRepository.save(deliverable);

            return modelMapper.map(deliverable,DeliverableDto.class);
        }else {
            return null;
        }
    }
    }
