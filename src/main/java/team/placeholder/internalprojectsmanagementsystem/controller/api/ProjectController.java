package team.placeholder.internalprojectsmanagementsystem.controller.api;

import lombok.RequiredArgsConstructor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import team.placeholder.internalprojectsmanagementsystem.dto.model.department.DepartmentDto;
import team.placeholder.internalprojectsmanagementsystem.dto.model.project.ProjectDto;
import team.placeholder.internalprojectsmanagementsystem.dto.model.user.UserDto;
import team.placeholder.internalprojectsmanagementsystem.dto.uidto.NewProDto;
import team.placeholder.internalprojectsmanagementsystem.dto.uidto.PrjDto;
import team.placeholder.internalprojectsmanagementsystem.model.user.User;
import team.placeholder.internalprojectsmanagementsystem.model.user.userenums.Role;
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


//    @GetMapping("/count/{id}")
//    public ResponseEntity<Map<String, Object>> getCount(@PathVariable long id) {
//        long pcount = projectService.getCountByDepartment(id);
//        long ecount = userService.getMemberCount(id);
//
//        List<UserDto> departmentHead = userService.getDepartmentHeadByRole(Role.DEPARTMENT_HEAD);
//        DepartmentDto department = new DepartmentDto();
//        department.setId(id);
//        UserDto user = new UserDto();
//        user.setDepartmentdto(department);
//
//        UserDto departmentHead = userService.getDepartmentHead(id,Role.DEPARTMENT_HEAD);
//
//        Map<String, Object> counts = new HashMap<>();
//        counts.put("projectCount : ", pcount);
//        counts.put("memberCount : ", ecount);
//        counts.put("departmentHead : ","departmentHead");
//        return ResponseEntity.ok(counts);
//    }



}
