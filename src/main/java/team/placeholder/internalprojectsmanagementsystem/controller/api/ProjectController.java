package team.placeholder.internalprojectsmanagementsystem.controller.api;

import lombok.RequiredArgsConstructor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import team.placeholder.internalprojectsmanagementsystem.dto.mapper.project.ProjectMapper;
import team.placeholder.internalprojectsmanagementsystem.dto.model.project.ArchitectureDto;
import team.placeholder.internalprojectsmanagementsystem.dto.model.project.ProjectDto;
import team.placeholder.internalprojectsmanagementsystem.dto.uidto.NewProDto;
import team.placeholder.internalprojectsmanagementsystem.dto.uidto.PrjDto;
import team.placeholder.internalprojectsmanagementsystem.model.project.Architecture;
import team.placeholder.internalprojectsmanagementsystem.model.project.Project;
import team.placeholder.internalprojectsmanagementsystem.repository.project.ArchitectureRepository;
import team.placeholder.internalprojectsmanagementsystem.service.impl.project.ArchitectureServiceImpl;
import team.placeholder.internalprojectsmanagementsystem.service.impl.project.ProjectServiceImpl;
import team.placeholder.internalprojectsmanagementsystem.service.impl.user.UserServiceImpl;
import team.placeholder.internalprojectsmanagementsystem.service.project.ProjectService;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/api/project")
@RequiredArgsConstructor
public class ProjectController {
    private final ProjectServiceImpl projectService;
    private final UserServiceImpl userService;
    private final ArchitectureServiceImpl architectureService;
    private final ArchitectureRepository architectureRepository;


    @PostMapping(value = "/save")
    public ResponseEntity<String> save(@RequestBody ProjectDto project){

//        Set<Architecture> architecture = new HashSet<>();
//
//        for(ArchitectureDto architectureDto : project.getArchitectureDto()) {
//            if(architectureDto.getId() == null) {
//                architecture.add(architectureService.save(architectureDto));
//            } else {
//                System.out.println("arch exist so find it and than store in architecture");
//                architecture.add(architectureRepository.getReferenceById(architectureDto.getId()));
//            }
//        }
////        System.out.println(architecture);
//        Project project2 = ProjectMapper.toProject(project);
//        project2.setArchitectures(architecture);
        ProjectDto savedProject = projectService.save(project);
        if (savedProject!=null){
            return ResponseEntity.ok("Project save successfully");
        }else {
        return ResponseEntity.badRequest().body("User save failed");
    }}

    @GetMapping("/list")
    public ResponseEntity<List<ProjectDto>> getAllProjects(){
        List<ProjectDto> projects = projectService.getAllProjects();
        return new ResponseEntity<>(projects, HttpStatus.OK);
    }

    @GetMapping("/list/{id}")
    public ResponseEntity<ProjectDto> getProjectById(@PathVariable long id){
        ProjectDto projects = projectService.getProjectById(id);
        System.out.println(projects);
        if (projects!=null){
            return ResponseEntity.ok(projects);
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/list/user/{id}")
    public ResponseEntity<List<ProjectDto>> getAllProjectsByUserId(@PathVariable long id){
        List<ProjectDto> projects = projectService.getAllProjectsByUsersId(id);
        return new ResponseEntity<>(projects, HttpStatus.OK);
    }

    @GetMapping("/list/name/{name}")
    public ResponseEntity<ProjectDto> getProjectByName(@PathVariable String name){
        ProjectDto project = projectService.getProjectByName(name);
        System.out.println("Project Name :" +project);
        if (project!= null){
            return ResponseEntity.ok(project);
        }else {
            return ResponseEntity.notFound().build();
        }
    }


//    @GetMapping("/count/{id}")
//    public ResponseEntity<Map<String, Long>> getCount(@PathVariable long id) {
//        long pcount = projectService.getCountByDepartment(id);
//        long ecount = userService.getMemberCount(id);
//        Map<String, Long> counts = new HashMap<>();
//        counts.put("projectCount : ", pcount);
//        counts.put("memberCount : ", ecount);
//        return ResponseEntity.ok(counts);
//    }

    @PutMapping(value = "/update/{id}", consumes ="application/Json")
    public ResponseEntity<String> updatePrject(@PathVariable long id, @RequestBody ProjectDto projectDto){
        ProjectDto updateProject = projectService.updateProject(projectDto);
        if (updateProject!=null){
            return ResponseEntity.ok("Issue Updated success");



        }else {
            return ResponseEntity.badRequest().body("Failed Updated");
        }
    }

    @GetMapping(value = "/architecturelist")
    public ResponseEntity <List <ArchitectureDto>> getAllArchitecture(){
        List<ArchitectureDto> architectureDtos= architectureService.getAllArchitecture();
        return new ResponseEntity<>(architectureDtos, HttpStatus.OK);
    }

    @GetMapping(value = "/count/all")
    public ResponseEntity<Long> countAllProjects(){
        Long count = projectService.countAllProjects();
        return new ResponseEntity<>(count, HttpStatus.OK);
    }

    @GetMapping(value = "/count/user/{id}")
    public ResponseEntity<Long> countByUserId(@PathVariable long id){
        Long count = projectService.countAllProjectsByUsersId(id);
        return new ResponseEntity<>(count, HttpStatus.OK);
    }

    @GetMapping(value = "/count/departmentId/{departmentId}")
    public ResponseEntity<Long> countAllByDepartmentId(@PathVariable Long departmentId){
        return ResponseEntity.ok(projectService.countAllProjectsByDepartmentId(departmentId));
    }

}
