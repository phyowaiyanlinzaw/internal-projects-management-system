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

import java.util.ArrayList;
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


    @PutMapping("/update-status")
    public ResponseEntity<List<DeliverableDto>> updateDeliverable(@RequestBody List<DeliverableDto> deliverableDtoList) {
        List<DeliverableDto> updatedDeliverables = new ArrayList<>();
        System.out.println("----------" +updatedDeliverables);
        for (DeliverableDto deliverableDto : deliverableDtoList) {


            DeliverableDto updatedDeliverableDto = deliverableService.updateDeliverable(deliverableDto);

            if (updatedDeliverableDto != null) {
                updatedDeliverables.add(updatedDeliverableDto);
            }
        }

        if (!updatedDeliverables.isEmpty()) {
            return ResponseEntity.ok(updatedDeliverables);
        } else {
            return ResponseEntity.notFound().build();
        }
    }





}
