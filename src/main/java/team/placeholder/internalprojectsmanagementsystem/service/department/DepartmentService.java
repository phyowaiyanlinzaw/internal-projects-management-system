package team.placeholder.internalprojectsmanagementsystem.service.department;

import team.placeholder.internalprojectsmanagementsystem.dto.model.department.DepartmentDto;
import team.placeholder.internalprojectsmanagementsystem.model.department.Department;

import java.util.List;

public interface DepartmentService {
    //TODO: Implement Department Service
    DepartmentDto save(DepartmentDto departmentDto);

    List<DepartmentDto> getAllDepartments();


    DepartmentDto getDepartmentById(long id);

    DepartmentDto getDepartmentByName(String name);

    DepartmentDto updateDepartment(DepartmentDto departmentDto);

    public void deleteDepartment(long id);



}
