package team.placeholder.internalprojectsmanagementsystem.dto.mapper.department;

import team.placeholder.internalprojectsmanagementsystem.dto.model.department.DepartmentDto;
import team.placeholder.internalprojectsmanagementsystem.model.department.Department;

public class DepartmentMapper {
    public static DepartmentDto toDepartmentDto(Department department) {
        if (department == null) {
            return null;
        }
        DepartmentDto departmentDto = new DepartmentDto();
            departmentDto.setId(department.getId());
            departmentDto.setName(department.getName());

        return departmentDto;
    }

    public static Department toDepartment(DepartmentDto departmentDto) {
        if(departmentDto == null) {
            return null;
        }

        Department department = new Department();
        department.setId(departmentDto.getId());
        department.setName(departmentDto.getName());

        return department;
    }
}
