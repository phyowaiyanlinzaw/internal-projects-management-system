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
import team.placeholder.internalprojectsmanagementsystem.service.impl.user.UserServiceImpl;
import team.placeholder.internalprojectsmanagementsystem.service.project.ProjectService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/project")
@RequiredArgsConstructor
public class ProjectController {
    private final ProjectServiceImpl projectService;
    private final UserServiceImpl userService;


    @PostMapping("save")
    public ResponseEntity<String> save(@RequestBody ProjectDto project){
        projectService.save(project);
        return ResponseEntity.ok("User save successfully");
    }

    @GetMapping("/projectlist")
    public ResponseEntity<List<ProjectDto>> getAllProjects(){
        List<ProjectDto> projects = projectService.getAllProjects();
        return new ResponseEntity<>(projects, HttpStatus.OK);
    }

    @GetMapping("/lists/{id}")
    public ResponseEntity<ProjectDto> getProjectById(@PathVariable long id){
        ProjectDto projects = projectService.getProjectById(id);
        if (projects!=null){
            return ResponseEntity.ok(projects);
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/lists/byName/{name}")
    public ResponseEntity<ProjectDto> getProjectByName(@PathVariable String name){
        ProjectDto project = projectService.getProjectByName(name);
        System.out.println("Project Name :" +project);
        if (project!= null){
            return ResponseEntity.ok(project);
        }else {
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping("/count/{id}")
    public ResponseEntity<Map<String, Long>> getCount(@PathVariable long id) {
        long pcount = projectService.getCountByDepartment(id);
        long ecount = userService.getMemberCount(id);
        Map<String, Long> counts = new HashMap<>();
        counts.put("projectCount : ", pcount);
        counts.put("memberCount : ", ecount);
        return ResponseEntity.ok(counts);
    }



}
