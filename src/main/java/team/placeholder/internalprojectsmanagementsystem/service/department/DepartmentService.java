package team.placeholder.internalprojectsmanagementsystem.service.department;

import team.placeholder.internalprojectsmanagementsystem.dto.model.department.DepartmentDto;

import java.util.List;

public interface DepartmentService {
    //TODO: Implement Department Service

    DepartmentDto save(DepartmentDto departmentDto);

    List<DepartmentDto> getAllDepartments();

    DepartmentDto getDepartmentById(int id);

    DepartmentDto getDepartmentByName(String name);

    DepartmentDto updateDepartment(DepartmentDto departmentDto);

    void deleteDepartment(DepartmentDto departmentDto);







}
