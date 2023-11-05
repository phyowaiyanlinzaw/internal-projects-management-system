package team.placeholder.internalprojectsmanagementsystem.controller.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team.placeholder.internalprojectsmanagementsystem.dto.model.project.DeliverableTypeDto;
import team.placeholder.internalprojectsmanagementsystem.service.impl.project.DeliverableTypeServiceImpl;

import java.util.Set;

@RestController
@RequestMapping("/api/deliverabletype")
@RequiredArgsConstructor
public class DeliverableTypeController {

    private final DeliverableTypeServiceImpl deliverableTypeService;

    @GetMapping
    public ResponseEntity<Set<DeliverableTypeDto>> getAll() {
        return new ResponseEntity<>(deliverableTypeService.getAll(), HttpStatus.OK);
    }

}
