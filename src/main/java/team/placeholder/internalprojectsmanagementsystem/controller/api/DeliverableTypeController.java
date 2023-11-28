package team.placeholder.internalprojectsmanagementsystem.controller.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team.placeholder.internalprojectsmanagementsystem.dto.model.project.DeliverableDto;
import team.placeholder.internalprojectsmanagementsystem.dto.model.project.DeliverableTypeDto;
import team.placeholder.internalprojectsmanagementsystem.model.project.Deliverable;
import team.placeholder.internalprojectsmanagementsystem.model.project.DeliverableType;
import team.placeholder.internalprojectsmanagementsystem.model.project.Project;
import team.placeholder.internalprojectsmanagementsystem.repository.project.ProjectRepository;
import team.placeholder.internalprojectsmanagementsystem.service.FakerService;
import team.placeholder.internalprojectsmanagementsystem.service.impl.project.DeliverableTypeServiceImpl;
import team.placeholder.internalprojectsmanagementsystem.service.impl.project.ProjectServiceImpl;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/deliverable-type")
@RequiredArgsConstructor
public class DeliverableTypeController {

    public final ProjectRepository projectRepository;

    private final DeliverableTypeServiceImpl deliverableTypeService;
    private final FakerService fakerService;

    @GetMapping("/generate-fake-deliverable-type/{count}")
    public ResponseEntity<String> generateFakeDeliverableType(@PathVariable int count) {
        fakerService.generateAndSaveDeliverableTypes(count);
        return ResponseEntity.ok("Fake deliverable types generated successfully");
    }
    @GetMapping("/list")
    public ResponseEntity<Set<DeliverableTypeDto>> getAll() {
        return new ResponseEntity<>(deliverableTypeService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/list/byProject/{id}")
    public ResponseEntity<Set<DeliverableTypeDto>> getAllByProject(@PathVariable long id) {

        Project project = projectRepository.findById(id);

        Set<Long> deliverableTypeIds = project.getDeliverables().stream()
                .map(deliverable -> deliverable.getDeliverableTypes().getId())
                .collect(Collectors.toSet());

        Set<DeliverableTypeDto> deliverableTypeDtos = deliverableTypeService.getAll().stream()
                .filter(deliverableTypeDto -> !deliverableTypeIds.contains(deliverableTypeDto.getId()))
                .collect(Collectors.toSet());

        return new ResponseEntity<>(deliverableTypeDtos, HttpStatus.OK);
    }

}
