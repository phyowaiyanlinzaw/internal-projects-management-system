package team.placeholder.internalprojectsmanagementsystem.controller.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import team.placeholder.internalprojectsmanagementsystem.dto.model.project.ProjectDto;
import team.placeholder.internalprojectsmanagementsystem.service.project.ProjectService;

import java.util.List;

@RestController
@RequestMapping("/api/project")
@RequiredArgsConstructor
public class ProjectController {
    private final ProjectService projectService;
    @GetMapping("/list")
    public List<ProjectDto> getAllProject(){
        return projectService.getAllProjects();
    }

    @GetMapping("/{id}/listone")
    public ResponseEntity<ProjectDto> getProjectById(@PathVariable int id) {
        ProjectDto projectDto = projectService.getProjectById(id);

        if (projectDto != null) {
            return ResponseEntity.ok(projectDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/save")
    public ResponseEntity<ProjectDto> saveProject(@RequestBody ProjectDto projectDto) {
        ProjectDto savedProjectDto = projectService.save(projectDto);
        return ResponseEntity.ok(savedProjectDto);
    }
}
