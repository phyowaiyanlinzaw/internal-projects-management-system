package team.placeholder.internalprojectsmanagementsystem.controller.api;

import lombok.extern.slf4j.Slf4j;
import lombok.RequiredArgsConstructor;

import org.apache.commons.lang3.arch.Processor.Arch;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import groovyjarjarantlr4.v4.parse.ANTLRParser.finallyClause_return;
import team.placeholder.internalprojectsmanagementsystem.dto.model.project.*;
import team.placeholder.internalprojectsmanagementsystem.dto.model.issue.IssueDto;
import team.placeholder.internalprojectsmanagementsystem.dto.model.user.ClientDto;
import team.placeholder.internalprojectsmanagementsystem.dto.model.user.UserDto;
import team.placeholder.internalprojectsmanagementsystem.dto.uidto.ProListDto;
import team.placeholder.internalprojectsmanagementsystem.model.project.Amount;
import team.placeholder.internalprojectsmanagementsystem.model.project.Architecture;
import team.placeholder.internalprojectsmanagementsystem.model.project.Deliverable;
import team.placeholder.internalprojectsmanagementsystem.model.project.DeliverableType;
import team.placeholder.internalprojectsmanagementsystem.model.project.Project;
import team.placeholder.internalprojectsmanagementsystem.model.project.projectenums.TaskStatus;
import team.placeholder.internalprojectsmanagementsystem.model.user.userenums.Role;
import team.placeholder.internalprojectsmanagementsystem.repository.project.DeliverableRepository;
import team.placeholder.internalprojectsmanagementsystem.repository.project.DeliverableTypeRepo;
import team.placeholder.internalprojectsmanagementsystem.repository.project.ProjectRepository;
import team.placeholder.internalprojectsmanagementsystem.service.FakerService;
import team.placeholder.internalprojectsmanagementsystem.service.impl.project.ArchitectureServiceImpl;
import team.placeholder.internalprojectsmanagementsystem.service.impl.project.DeliverableTypeServiceImpl;
import team.placeholder.internalprojectsmanagementsystem.service.impl.project.ProjectServiceImpl;
import team.placeholder.internalprojectsmanagementsystem.service.impl.project.TaskServiceImpl;
import team.placeholder.internalprojectsmanagementsystem.service.impl.user.UserServiceImpl;

import java.util.*;

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
    private final ProjectRepository projectRepository;
    private final DeliverableRepository deliverableTypeServiceImpl;
    private final DeliverableTypeRepo deliverableTypeRe;
    private final ModelMapper modelMapper;

    @GetMapping("/generate-fake-project/{count}")
    public ResponseEntity<String> generateFakeProjects(@PathVariable int count) {
        fakerService.generateAndSaveFakeProjects(count);
        return ResponseEntity.ok("Projects generated successfully");
    }

    @PostMapping(value = "/save")
    public ResponseEntity<ProListDto> save(@RequestBody ProjectDto project){
        ProjectDto savedProject = projectService.save(project);
        ProListDto proListDto = new ProListDto();
        if (savedProject!=null){

            proListDto.setId(savedProject.getId());
            proListDto.setProjectName(savedProject.getName());
            proListDto.setEndDate(savedProject.getEnd_date());
            proListDto.setStartDate(savedProject.getStart_date());
            proListDto.setPercentage(0);
            proListDto.setUser(savedProject.getProjectManagerUserDto());
            return new ResponseEntity<>(proListDto, HttpStatus.OK);
        }else {
        return new ResponseEntity<>(proListDto, HttpStatus.BAD_REQUEST);
    }
    }

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


    @PutMapping(value = "/update", consumes ="application/Json")
    public ResponseEntity<ProjectDto> updatePrject(@RequestBody ProjectDto projectDto){

        log.info("Project : {}", projectDto);
        log.info(String.valueOf(projectDto.getId()));
        log.info(String.valueOf(projectDto.getDuration()));
        log.info(String.valueOf(projectDto.getStart_date()));
        log.info(String.valueOf(projectDto.getEnd_date()));
        log.info(String.valueOf(projectDto.getCurrent_phase()));
        log.info(String.valueOf(projectDto.getObjective()));

         ProjectDto updateProject = projectService.updateProject(projectDto);

         return ResponseEntity.ok(updateProject);
    }

    @PutMapping("/update/amount/{id}")
    public ResponseEntity<ProjectDto> updateAmountById(@PathVariable long id, @RequestBody AmountDto amountDto) {
        Project project = projectRepository.getReferenceById(id);

        Amount amount = project.getAmount();

        amount.setBasic_design(amountDto.getBasic_design());
        amount.setCoding(amountDto.getCoding());
        amount.setDetail_design(amountDto.getDetail_design());
        amount.setUnit_testing(amountDto.getUnit_testing());
        amount.setIntegrated_testing(amountDto.getIntegrated_testing());

        project.setAmount(amount);

        projectRepository.save(project);

        return ResponseEntity.ok(projectService.getProjectById(id));
    }


    @GetMapping(value = "/architecturelist")
    public ResponseEntity <List <ArchitectureDto>> getAllArchitecture(){
        List<ArchitectureDto> architectureDtos= architectureService.getAllArchitecture();
        System.out.println(architectureDtos);
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

    @GetMapping(value = "/count/project-manager/{id}")
    public ResponseEntity<Long> countByProjectManagerId(@PathVariable long id){
        Long count = projectService.countAllProjectsByProjectManagerId(id);
        return new ResponseEntity<>(count, HttpStatus.OK);
    }

    @GetMapping(value = "/count/project-manager/{id}/status/{status}")
    public ResponseEntity<Long> countByProjectManagerIdAndStatus(@PathVariable long id, @PathVariable String status){
        Long count = projectService.countAllProjectsByProjectManagerIdAndClosed(id, Boolean.parseBoolean(status));
        return new ResponseEntity<>(count, HttpStatus.OK);
    }

    @GetMapping(value = "/count/department/{departmentId}")
    public ResponseEntity<Long> countAllByDepartmentId(@PathVariable Long departmentId){
        return ResponseEntity.ok(projectService.countAllProjectsByDepartmentId(departmentId));
    }

    @GetMapping(value = "/deliverableType")
    public ResponseEntity<Set<DeliverableTypeDto>> getDeliverableType() {
        return ResponseEntity.ok(deliverableTypeService.getAll());
    }

    @GetMapping(value = "/list/role/{role}/id/{id}")
    public ResponseEntity<List<ProjectDto>> getAllProjectsByRole(@PathVariable String role, @PathVariable Long id){

        //store the project list from the 

        log.info("current login user role " + role);

        switch (role) {
            case "PROJECT_MANAGER" -> {
                List<ProjectDto> projects = projectService.getAllProjectsByProjectManagerId(id); // i odn't know

                projects.forEach(project -> {
                    log.info("total taskcount is here : {}", project.getTotalTaskCount());
                    log.info("complete taskcount is here : {}", project.getCompleteTaskCount());
                });

                return getListResponseEntity(projects);
            }
            case "DEPARTMENT_HEAD" -> {
                long departmentId = userService.getUserById(id).getDepartmentdto().getId();
                log.info(" department is here : {}", userService.getUserById(id).getDepartmentdto());
                List<ProjectDto> projects = projectService.getAllProjectsByDepartmentId(departmentId);
                return getListResponseEntity(projects);
            }
            case "MEMBER" -> {
                return getListResponseEntity(projectService.findAllByUserId(id));
            }
            default -> {
                return new ResponseEntity<>(projectService.getAllProjects(), HttpStatus.OK);
            }
        }
    }

    @GetMapping("/list/sort/by/department")
    public ResponseEntity<Map<Long, List<Long>>> sortProjectByDepId() {

        List<ProjectDto> projectList = projectService.getAllProjects();

        Map<Long, List<Long>> departmentProjectMap = new HashMap<>();

        for(ProjectDto project: projectList) {
            Long departmentId = project.getDepartmentDto().getId();
            Long projectId = project.getId();

            if(departmentProjectMap.containsKey(departmentId)) {
                departmentProjectMap.get(departmentId).add(projectId);
            } else {
                List<Long> projectIds = new ArrayList<>();
                projectIds.add(projectId);
                departmentProjectMap.put(departmentId, projectIds);
            }

        }

        return new ResponseEntity<>(departmentProjectMap, HttpStatus.OK);

    }

    @GetMapping("/list/department-name/{name}")
    public ResponseEntity<List<ProjectDto>> getAllProjectsByDepartmentName(@PathVariable String name){
        List<ProjectDto> projects = projectService.getAllProjectsByDepartmentName(name);
        return new ResponseEntity<>(projects, HttpStatus.OK);
    }

    @GetMapping("/list/ID/{id}/{status}")
    public ResponseEntity<Map<String, Object>> getProjectByIdAndStatus(@PathVariable long id, @PathVariable String status){
        List<ProjectDto> project = projectService.findAllByUserId(id);

        Long currentProjectId = null;

//        log.info("project : {}" , (Object)project);

        Map<String, Object> projectMap = new HashMap<>();
        for(ProjectDto projectDto : project){

//            log.info("porject close or not : {}", projectDto.isClosed());

            if(projectDto.isClosed() == Boolean.parseBoolean(status)){
//                log.info("closed proejct : {}" , projectDto);

                currentProjectId = projectDto.getId();

                ClientDto clientDto = projectDto.getClientDto();
                List<UserDto> userDtos = projectDto.getMembersUserDto();

                projectMap.put("client", clientDto);
                projectMap.put("userList", userDtos);

            }
        }

        projectMap.put("projectId", currentProjectId);

        if (currentProjectId == null) {
            return new ResponseEntity<>(null, HttpStatus.OK);
        }

//        log.info( "project map : {}", projectMap);

        return new ResponseEntity<>(projectMap, HttpStatus.OK);
    }

    @GetMapping("/list/for/pmo-and-sdqc")
    public ResponseEntity<Map<Long,List<Long>>> getProjectWithDpId() {

        List<Project> projectList = projectRepository.findAll();

        Map<Long, List<Long>> departmentProejctMap = new HashMap<>();

        for(Project proejct: projectList) {
            Long departmentId = proejct.getDepartment().getId();
            Long projectId = proejct.getId();

            if(departmentProejctMap.containsKey(departmentId)) {
                departmentProejctMap.get(departmentId).add(projectId);
            } else {
                List<Long> projectIds = new ArrayList<>();
                projectIds.add(projectId);
                departmentProejctMap.put(departmentId, projectIds);
            }

        }

        return new ResponseEntity<>(departmentProejctMap, HttpStatus.OK);
    }

    ResponseEntity<List<ProjectDto>> getListResponseEntity(List<ProjectDto> projects) {
        for(ProjectDto projectDto : projects){
            if(projectDto.getProjectManagerUserDto() != null) {
//                if(projectDto.getUserDto().getProjectList() != null) {
//                    projectDto.getUserDto().getProjectList().clear();
//                }
            }
            projectDto.setTotalTaskCount(taskService.countByProjectId(projectDto.getId()));
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

    @GetMapping(value = "new/list/role/{role}/id/{id}")
    public ResponseEntity<List<ProListDto>> newListView(@PathVariable String role, @PathVariable Long id){

//        log.info("current login user role " + role);

        return new ResponseEntity<>(projectService.newProjectLook(Role.valueOf(role), id), HttpStatus.OK);
    }

    @GetMapping("/update/status/{id}/{condition}")
    public ResponseEntity<String> updateProjectStatus(@PathVariable long id, @PathVariable boolean condition) {

        projectService.updateProjectClosed(id, condition);

        return new ResponseEntity<>(" i don't know", HttpStatus.OK);
    }

    @PutMapping("/update/userlist/{projectid}")
    public ResponseEntity<String> updateUserListInProejct(@PathVariable long projectid, @RequestBody List<UserDto> userDtos) {


        projectService.updateUserListInProject(projectid, userDtos);

        return ResponseEntity.ok("User list updated successfully");

    }

    @PutMapping("/delete/{id}/deliveralbeList")
    public ResponseEntity<List<DeliverableDto>> updateDeliverableList(@PathVariable long id, @RequestBody Map<String, Long> requestBody) {

        long deliID = requestBody.get("deliID");
        
        Deliverable deliverable = deliverableTypeServiceImpl.findById(deliID);

        Project project = projectRepository.getReferenceById(id);

        project.getDeliverables().remove(deliverable);

        projectRepository.save(project);

        deliverable.setDeliverableTypes(null);

        deliverableTypeServiceImpl.delete(deliverable);

        ProjectDto projectDto = projectService.getProjectById(id);

        return ResponseEntity.ok(projectDto.getDeliverableDto());
    }

    @PutMapping("/save/{id}/deliveralbeList")
    public ResponseEntity<List<DeliverableDto>> saveDeliverableList(@PathVariable long id, @RequestBody List<DeliverableDto> requestBody) {

        List<DeliverableType> deliverableTypes = new ArrayList<>();

        for(DeliverableDto deliverableDto : requestBody) {
            DeliverableType deliverableType = deliverableTypeRe.getReferenceById(deliverableDto.getDeliverableType().getId());
            deliverableTypes.add(deliverableType);
        }

        Project project = projectRepository.getReferenceById(id);

        List<Deliverable> deliverable = new ArrayList<>();

        for(DeliverableType deliTpye : deliverableTypes) {
            Deliverable deliverable1 = new Deliverable();
            deliverable1.setDeliverableTypes(deliTpye);
            deliverable.add(deliverable1);
        }

        project.getDeliverables().addAll(deliverable);

        projectRepository.save(project);

        ProjectDto projectDto = projectService.getProjectById(id);

        return ResponseEntity.ok(projectDto.getDeliverableDto());
    }

    @PutMapping("/update/{id}/architectureList")
    public ResponseEntity<List<ArchitectureDto>> updateArchitectureList(@PathVariable long id, @RequestBody Map<String, Long> requestBody) {

        Project project = projectRepository.getReferenceById(id);

        Architecture architecture = architectureService.findById(requestBody.get("arcid"));

        project.getArchitectures().remove(architecture);

        projectRepository.save(project);

        List<ArchitectureDto> architectureDtos = new ArrayList<>();

        for(Architecture architecture1 : project.getArchitectures()) {
            ArchitectureDto architectureDto = modelMapper.map(architecture1, ArchitectureDto.class);
            architectureDtos.add(architectureDto);
        }

        return new ResponseEntity<>(architectureDtos, HttpStatus.OK);
        
    }

    @GetMapping("/architecturelist/{id}")
    public ResponseEntity<List<ArchitectureDto>> getArchitectureList(@PathVariable long id) {

        Project project = projectRepository.getReferenceById(id);

        List<ArchitectureDto> architectureDtos = architectureService.getAllArchitecture();

        List<ArchitectureDto> architectureDtoList = new ArrayList<>();

        for(ArchitectureDto architectureDto : architectureDtos) {
            if(!project.getArchitectures().contains(architectureService.findById(architectureDto.getId()))) {
                architectureDtoList.add(architectureDto);
            }
        }

        return new ResponseEntity<>(architectureDtoList, HttpStatus.OK);
    }

    @PutMapping("/save/{id}/architectureList")
    public ResponseEntity<Set<ArchitectureDto>> saveArchitectureList(@PathVariable long id, @RequestBody Set<ArchitectureDto> requestBody) {

        Project project = projectRepository.getReferenceById(id);

        List<Architecture> architectures = new ArrayList<>();
        for(ArchitectureDto architectureDto : requestBody) {
            Architecture architecture = architectureService.findById(architectureDto.getId());
            architectures.add(architecture);
        }

        project.getArchitectures().addAll(architectures);
        projectRepository.save(project);

        return ResponseEntity.ok(projectService.getProjectById(id).getArchitectureDto());
    }

}
