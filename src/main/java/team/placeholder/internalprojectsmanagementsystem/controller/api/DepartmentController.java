package team.placeholder.internalprojectsmanagementsystem.controller.api;


import groovy.util.logging.Slf4j;
import lombok.RequiredArgsConstructor;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import team.placeholder.internalprojectsmanagementsystem.dto.model.department.DepartmentDto;
import team.placeholder.internalprojectsmanagementsystem.service.impl.department.DepartmentServiceImpl;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/department/")
public class DepartmentController {

    private final DepartmentServiceImpl departmentService;

    @GetMapping("lists")
    public ResponseEntity<List<DepartmentDto>> getAllDepartments() {
        List<DepartmentDto> departmentDtos = departmentService.getAllDepartments();
        return new ResponseEntity<>(departmentDtos, HttpStatus.OK);
    }

    @PostMapping("save")
    public ResponseEntity<String> save(@RequestBody DepartmentDto departmentDto) {
        DepartmentDto savedDepartment = departmentService.save(departmentDto);
        if (savedDepartment != null) {
            return ResponseEntity.ok("Department saved successfully");
        } else {
            return ResponseEntity.badRequest().body("Failed to save department");
        }
    }

    @GetMapping("/byId/{id}")
    public ResponseEntity<DepartmentDto> getDepartmentById(@PathVariable("id") long id) {
        DepartmentDto department = departmentService.getDepartmentById(id);

        if (department != null) {
            return ResponseEntity.ok(department);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/byName/{name}")
    public ResponseEntity<DepartmentDto> getDepartmentByName(@PathVariable("name") String name) {
        DepartmentDto department = departmentService.getDepartmentByName(name);

        if (department != null) {
            return ResponseEntity.ok(department);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @PutMapping("/lists/update/{id}")
    public ResponseEntity<String> updateDepartment(@PathVariable("id") long id, @RequestBody DepartmentDto departmentDto) {

        departmentDto.setId(id);
        DepartmentDto updatedDepartment = departmentService.updateDepartment(departmentDto);

        if (updatedDepartment != null) {
            return ResponseEntity.ok("Department updated successfully");
        } else {
            return ResponseEntity.badRequest().body("Failed to update department");
        }
    }
    @DeleteMapping("/lists/delete/{id}")
    public ResponseEntity<String> deleteDepartment(@PathVariable("id") long id) {
        departmentService.deleteDepartment(id); // Pass the 'id' as a path variable
        return ResponseEntity.ok("Department deleted successfully");
    }




}
