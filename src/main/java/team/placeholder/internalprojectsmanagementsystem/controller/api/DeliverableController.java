package team.placeholder.internalprojectsmanagementsystem.controller.api;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team.placeholder.internalprojectsmanagementsystem.service.FakerService;
import team.placeholder.internalprojectsmanagementsystem.service.impl.project.DeliverableTypeServiceImpl;
import team.placeholder.internalprojectsmanagementsystem.service.project.DeliverableService;

@RestController
@RequestMapping("api/deliverable")
@AllArgsConstructor
public class DeliverableController {

    private final DeliverableTypeServiceImpl deliverableService;
    private final FakerService fakerService;

    @GetMapping("generate-fake-deliverable/{count}")
    public String generateFakeDeliverable(@PathVariable int count) {
        fakerService.generateAndSaveFakeDeliverable(count);
        return "Fake deliverables generated successfully";
    }
}
