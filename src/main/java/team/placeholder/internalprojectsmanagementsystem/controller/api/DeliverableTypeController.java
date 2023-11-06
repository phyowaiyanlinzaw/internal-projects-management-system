package team.placeholder.internalprojectsmanagementsystem.controller.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team.placeholder.internalprojectsmanagementsystem.dto.model.project.DeliverableTypeDto;
import team.placeholder.internalprojectsmanagementsystem.service.FakerService;
import team.placeholder.internalprojectsmanagementsystem.service.impl.project.DeliverableTypeServiceImpl;

import java.util.Set;

@RestController
@RequestMapping("/api/deliverable-type")
@RequiredArgsConstructor
public class DeliverableTypeController {

    private final DeliverableTypeServiceImpl deliverableTypeService;
    private final FakerService fakerService;

    @GetMapping("/generate-fake-deliverable-type/{count}")
    public ResponseEntity<String> generateFakeDeliverableType(@PathVariable int count) {
        fakerService.generateAndSaveDeliverableTypes(count);
        return ResponseEntity.ok("Fake deliverable types generated successfully");
    }
    @GetMapping
    public ResponseEntity<Set<DeliverableTypeDto>> getAll() {
        return new ResponseEntity<>(deliverableTypeService.getAll(), HttpStatus.OK);
    }

}
