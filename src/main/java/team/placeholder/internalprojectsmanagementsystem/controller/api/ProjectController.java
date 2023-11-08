package team.placeholder.internalprojectsmanagementsystem.controller.api;

import lombok.extern.slf4j.Slf4j;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import team.placeholder.internalprojectsmanagementsystem.dto.model.project.ArchitectureDto;
import team.placeholder.internalprojectsmanagementsystem.dto.model.project.DeliverableTypeDto;
import team.placeholder.internalprojectsmanagementsystem.dto.model.project.ProjectDto;
import team.placeholder.internalprojectsmanagementsystem.dto.model.user.ClientDto;
import team.placeholder.internalprojectsmanagementsystem.dto.model.user.UserDto;
import team.placeholder.internalprojectsmanagementsystem.model.project.projectenums.TaskStatus;
import team.placeholder.internalprojectsmanagementsystem.service.FakerService;
import team.placeholder.internalprojectsmanagementsystem.service.impl.project.ArchitectureServiceImpl;
import team.placeholder.internalprojectsmanagementsystem.service.impl.project.DeliverableTypeServiceImpl;
import team.placeholder.internalprojectsmanagementsystem.service.impl.project.ProjectServiceImpl;
import team.placeholder.internalprojectsmanagementsystem.service.impl.project.TaskServiceImpl;
import team.placeholder.internalprojectsmanagementsystem.service.impl.user.ClientServiceImpl;
import team.placeholder.internalprojectsmanagementsystem.service.impl.user.UserServiceImpl;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/project")
@RequiredArgsConstructor
@Slf4j
public class ProjectController {
    private final ProjectServiceImpl projectService;
    private final UserServiceImpl userService;
    private final ArchitectureServiceImpl architectureService;
    private final DeliverableTypeServiceImpl deliverableTypeService;
    private final TaskServiceImpl taskService;
    private final FakerService fakerService;

    private final ClientServiceImpl clientService;

    @GetMapping("/generate-fake-project/{count}")
    public ResponseEntity<String> generateFakeProjects(@PathVariable int count) {
        fakerService.generateAndSaveFakeProjects(count);
        return ResponseEntity.ok("Projects generated successfully");
    }



    @PostMapping(value = "/save")
    public ResponseEntity<ProjectDto> save(@RequestBody ProjectDto project){
        log.info("Project: {}", project);
        ProjectDto savedProject = projectService.save(project);
        if (savedProject!=null){
            return new ResponseEntity<>(savedProject, HttpStatus.OK);
        }else {
        return new ResponseEntity<>(savedProject, HttpStatus.BAD_REQUEST);
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
        List<ProjectDto> projects = projectService.getAllProjectsByProjectManagerId(id);
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

    @GetMapping(value = "/deliverableType")
    public ResponseEntity<Set<DeliverableTypeDto>> getDeliverableType() {
        return ResponseEntity.ok(deliverableTypeService.getAll());
    }

    @GetMapping(value = "/list/{role}/{id}")
    public ResponseEntity<List<ProjectDto>> getAllProjectsByRole(@PathVariable String role, @PathVariable long id){

        if (role.equals("PROJECT_MANAGER")) {
            List<ProjectDto> projects = projectService.getAllProjectsByProjectManagerId(id);
            return getListResponseEntity(projects);
        } else if (role.equals("DEPARTMENT_HEAD")) {
            List<ProjectDto> projects = projectService.getAllProjectsByDepartmentId(id);
            return getListResponseEntity(projects);
        } else if (role.equals("MEMBER")) {
            return getListResponseEntity(projectService.findAllByUserId(id));
        } else {
            return new ResponseEntity<>(projectService.getAllProjects(), HttpStatus.OK);
        }
    }

    private ResponseEntity<List<ProjectDto>> getListResponseEntity(List<ProjectDto> projects) {
        for(ProjectDto projectDto : projects){
            if(projectDto.getUserDto() != null) {
                if(projectDto.getUserDto().getProjectList() != null) {
                    projectDto.getUserDto().getProjectList().clear();
                }
            }
            projectDto.setUnfinishedTaskCount(taskService.countTaskByProjectIdAndStatus(projectDto.getId(), TaskStatus.IN_PROGRESS));
            projectDto.setCompleteTaskCount(taskService.countTaskByProjectIdAndStatus(projectDto.getId(), TaskStatus.FINISHED));
            if(projectDto.getTasksDto() != null) {
                projectDto.getTasksDto().clear();
            }
            if(projectDto.getTasksDto() != null) {
                projectDto.getIssueDto().clear();
            }
        }
        return new ResponseEntity<>(projects, HttpStatus.OK);
    }

    @GetMapping("/list/clients")
    public ResponseEntity<List<ClientDto>> getAllClientsFromProjects() {
        List<ClientDto> clients = projectService.getAllClientsFromProjects();
        return new ResponseEntity<>(clients, HttpStatus.OK);
    }


    @GetMapping("/userLists")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> userDtos = projectService.getAllPM();
        return new ResponseEntity<>(userDtos, HttpStatus.OK);
    }

//    @GetMapping("/clientsAndusers")
//    public ResponseEntity<List<ProjectDto>> getAllClientsAndUsers(){
//        List<ProjectDto> projectDtos = projectService.getAllClientsAndUser();
//        return new ResponseEntity<>(projectDtos, HttpStatus.OK);
//    }



}
