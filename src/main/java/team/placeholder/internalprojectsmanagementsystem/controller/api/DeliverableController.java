package team.placeholder.internalprojectsmanagementsystem.controller.api;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import team.placeholder.internalprojectsmanagementsystem.dto.model.project.DeliverableDto;
import team.placeholder.internalprojectsmanagementsystem.dto.model.project.DeliverableTypeDto;
import team.placeholder.internalprojectsmanagementsystem.model.project.Deliverable;
import team.placeholder.internalprojectsmanagementsystem.service.FakerService;
import team.placeholder.internalprojectsmanagementsystem.service.impl.project.DeliverableServiceImpl;
import team.placeholder.internalprojectsmanagementsystem.service.impl.project.DeliverableTypeServiceImpl;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("api/deliverable")
@AllArgsConstructor
public class DeliverableController {

    private final DeliverableServiceImpl deliverableService;
    private final FakerService fakerService;

    @GetMapping("generate-fake-deliverable/{count}")
    public String generateFakeDeliverable(@PathVariable int count) {
        fakerService.generateAndSaveFakeDeliverable(count);
        return "Fake deliverables generated successfully";
    }

//    @PostMapping("/update-status")
//    public ResponseEntity<String> updateDeliverableStatus(@RequestBody List<Deliverable> deliverables) {
//        try {
//            deliverableService.updateDeliverableStatus(deliverables);
//            return ResponseEntity.ok("Status updated successfully");
//        } catch (Exception e) {
//            e.printStackTrace();
//            return ResponseEntity.status(500).body("Error updating status");
//        }
//    }

    @PutMapping("/update-status/{id}")
    public ResponseEntity<DeliverableDto> updateDeliverable(@PathVariable long id, @RequestBody DeliverableDto deliverableDto) {
      deliverableDto.setId(id);
        DeliverableDto updatedDeliverableDto = deliverableService.updateDeliverable(deliverableDto);

        if (updatedDeliverableDto != null) {
            return ResponseEntity.ok(updatedDeliverableDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }




}
