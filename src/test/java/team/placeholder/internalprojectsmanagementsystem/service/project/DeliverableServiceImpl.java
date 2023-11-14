package team.placeholder.internalprojectsmanagementsystem.service.project;

import lombok.RequiredArgsConstructor;


import org.springframework.stereotype.Service;
import team.placeholder.internalprojectsmanagementsystem.dto.model.project.DeliverableDto;
import team.placeholder.internalprojectsmanagementsystem.repository.project.DeliverableRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DeliverableServiceImpl implements DeliverableService{

    private final DeliverableRepository deliverableRepository;


    @Override
    public DeliverableDto save(DeliverableDto deliverableDto) {
        return null;
    }

    @Override
    public List<DeliverableDto> getAll() {
        return null;
    }
}
