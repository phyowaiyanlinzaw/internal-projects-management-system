package team.placeholder.internalprojectsmanagementsystem.controller.api;

import lombok.RequiredArgsConstructor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import team.placeholder.internalprojectsmanagementsystem.dto.model.project.ProjectDto;
import team.placeholder.internalprojectsmanagementsystem.dto.uidto.NewProDto;
import team.placeholder.internalprojectsmanagementsystem.dto.uidto.PrjDto;
import team.placeholder.internalprojectsmanagementsystem.service.impl.project.ProjectServiceImpl;
import team.placeholder.internalprojectsmanagementsystem.service.project.ProjectService;

import java.util.List;

@RestController
@RequestMapping("/api/project")
@RequiredArgsConstructor
public class ProjectController {
    private final ProjectServiceImpl projectService;


    @PostMapping("save")
    public ResponseEntity<String> save(@RequestBody ProjectDto project){
        projectService.save(project);
        return ResponseEntity.ok("User save successfully");
    }

    @GetMapping("projectlist")
    public ResponseEntity<List<ProjectDto>> getAllProjects(){
        List<ProjectDto> projects = projectService.getAllProjects();
        return new ResponseEntity<>(projects, HttpStatus.OK);
    }



}
