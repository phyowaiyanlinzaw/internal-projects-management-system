package team.placeholder.internalprojectsmanagementsystem.controller.api;


import groovy.util.logging.Slf4j;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.beans.support.PropertyComparator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import team.placeholder.internalprojectsmanagementsystem.dto.model.department.DepartmentDto;
import team.placeholder.internalprojectsmanagementsystem.dto.uidto.PageRequestDto;
import team.placeholder.internalprojectsmanagementsystem.model.department.Department;
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

    @PostMapping("list")
    public ResponseEntity<List<DepartmentDto>> getAllDepartments() {
       List<DepartmentDto> departmentDtos = departmentService.getAllDepartments();
        return ResponseEntity.ok(departmentDtos);
    }
    @PostMapping("/departments")
    public Page<Department> getAllDepartment(@RequestBody PageRequestDto dto) {

        List<Department> departmentList = repository.findAll();

        // 1. PageListHolder
        PagedListHolder<Department> pagedListHolder = new PagedListHolder<Department>(departmentList);
        pagedListHolder.setPage(dto.getPageNo());
        pagedListHolder.setPageSize(dto.getPageSize());

        // 2. PropertyComparator
        List<Department> pageSlice = pagedListHolder.getPageList();
        boolean ascending = dto.getSort().isAscending();

        PropertyComparator.sort(pageSlice, new MutableSortDefinition(dto.getSortById(),true,true));


        // 3. PageImpl
        Page<Department> departments = new PageImpl<>(pageSlice, new PageRequestDto().getPageable(dto),departmentList.size());
        Pageable pageable = new PageRequestDto().getPageable(dto);
        Page<Department> departmentDtoPage = repository.findAll(pageable);
        return departments;
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
    @DeleteMapping("/lists/delete/{id}")
    public ResponseEntity<String> deleteDepartment(@PathVariable("id") long id) {
        departmentService.deleteDepartment(id); // Pass the 'id' as a path variable
        return ResponseEntity.ok("Department deleted successfully");
    }

    @GetMapping("/count")
    public long dpCount(){
        return departmentService.getDeprtmentCount();
    }




}
