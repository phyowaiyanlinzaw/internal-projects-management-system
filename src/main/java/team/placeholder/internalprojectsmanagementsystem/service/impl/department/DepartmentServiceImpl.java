package team.placeholder.internalprojectsmanagementsystem.service.impl.department;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import team.placeholder.internalprojectsmanagementsystem.dto.mapper.department.DepartmentMapper;
import team.placeholder.internalprojectsmanagementsystem.dto.model.department.DepartmentDto;
import team.placeholder.internalprojectsmanagementsystem.model.department.Department;
import team.placeholder.internalprojectsmanagementsystem.service.department.DepartmentService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentService departmentService;
    @Override
    public DepartmentDto save(Department departmentDto) {
        Department department = new Department();
        department.setName(departmentDto.getName());
        departmentService.save(department);
        return DepartmentMapper.toDepartmentDto(departmentService.save(department));
    }

    @Override
    public List<DepartmentDto> getAllDepartments() {
        List<Department> departments = departmentService.findAll();
        return departments.stream()
                .map(DepartmentMapper::toDepartmentDto)
                .collect(Collectors.toList());
    }

    @Override
    public DepartmentDto getDepartmentById(int id) {
        return null;
    }

    @Override
    public DepartmentDto getDepartmentByName(String name) {
        return null;
    }

    @Override
    public DepartmentDto updateDepartment(DepartmentDto departmentDto) {
        return null;
    }

    @Override
    public void deleteDepartment(DepartmentDto departmentDto) {

    }

    //TODO: Implement Department Service Implementation


}
