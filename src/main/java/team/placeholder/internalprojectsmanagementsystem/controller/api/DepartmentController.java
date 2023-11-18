package team.placeholder.internalprojectsmanagementsystem.controller.api;


import groovy.util.logging.Slf4j;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;
import team.placeholder.internalprojectsmanagementsystem.dto.model.department.DepartmentDto;
import team.placeholder.internalprojectsmanagementsystem.repository.department.DepartmentRepository;
import team.placeholder.internalprojectsmanagementsystem.service.FakerService;
import team.placeholder.internalprojectsmanagementsystem.service.impl.department.DepartmentServiceImpl;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/department/")
public class DepartmentController {

    private final DepartmentServiceImpl departmentService;
    private final FakerService fakerService;
    private final DepartmentRepository repository;

    @GetMapping("/generate-fake-departments/{count}")
    public ResponseEntity<String> generateFakeDepartments(@PathVariable("count") int count) {
        fakerService.generateAndSaveFakeDepartments(count);
        return ResponseEntity.ok("Fake departments generated successfully");
    }

    @GetMapping("list")
    public ResponseEntity<List<DepartmentDto>> getAllDepartments() {
       List<DepartmentDto> departmentDtos = departmentService.getAllDepartments();
        return ResponseEntity.ok(departmentDtos);
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


    @PutMapping("/update/{id}")
    public ResponseEntity<DepartmentDto> updateDepartment(@PathVariable("id") long id, @RequestBody DepartmentDto departmentDto) {

        departmentDto.setId(id);
        DepartmentDto updatedDepartment = departmentService.updateDepartment(departmentDto);

        if (updatedDepartment != null) {
            return ResponseEntity.ok(updatedDepartment);
        } else {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("/count")
    public long dpCount(){

        return departmentService.getDeprtmentCount();
    }




}
