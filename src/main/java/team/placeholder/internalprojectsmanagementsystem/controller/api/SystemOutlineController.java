package team.placeholder.internalprojectsmanagementsystem.controller.api;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team.placeholder.internalprojectsmanagementsystem.service.FakerService;
import team.placeholder.internalprojectsmanagementsystem.service.impl.project.SystemOutlineServiceImpl;

@RestController
@RequestMapping("/api/system-outline")
@AllArgsConstructor
public class SystemOutlineController {

    private final FakerService fakerService;
    private final SystemOutlineServiceImpl systemOutlineService;

    @GetMapping("/generate-fake-system-outline/{count}")
    public ResponseEntity<String> generateFakeSystemOutline(@PathVariable("count") int count) {
        fakerService.generateAndSaveFakeSystemOutline(count);
        return ResponseEntity.ok("Fake system outline generated");
    }
}
