package team.placeholder.internalprojectsmanagementsystem.service.impl.department;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import team.placeholder.internalprojectsmanagementsystem.dto.mapper.department.DepartmentMapper;
import team.placeholder.internalprojectsmanagementsystem.dto.mapper.user.UserMapper;
import team.placeholder.internalprojectsmanagementsystem.dto.model.department.DepartmentDto;
import team.placeholder.internalprojectsmanagementsystem.model.department.Department;
import team.placeholder.internalprojectsmanagementsystem.model.user.User;
import team.placeholder.internalprojectsmanagementsystem.repository.department.DepartmentRepository;
import team.placeholder.internalprojectsmanagementsystem.service.department.DepartmentService;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;

    @Override
    public DepartmentDto save(DepartmentDto departmentDto) {
        Department department = DepartmentMapper.toDepartment(departmentDto);
        department.setName(departmentDto.getName());
       Department savedDepartment = departmentRepository.save(department);
        return DepartmentMapper.toDepartmentDto(savedDepartment);

    }



    @Override
    public List<DepartmentDto> getAllDepartments() {
        List<Department> departments = departmentRepository.findAll();
        return departments.stream()
                .map(DepartmentMapper::toDepartmentDto)
                .collect(java.util.stream.Collectors.toList());
    }


    @Override
    public DepartmentDto getDepartmentById(long id) {
        Department department = departmentRepository.findById(id).orElse(null);
        if(department != null) {
            return DepartmentMapper.toDepartmentDto(department);
        }else{
            return null;
        }
    }

    @Override
    public DepartmentDto getDepartmentByName(String name) {
        Department department = departmentRepository.findByName(name).orElse(null);
        if(department != null) {
            return DepartmentMapper.toDepartmentDto(department);
        }else{
            return null;
        }

    }

    @Override
    public DepartmentDto updateDepartment(DepartmentDto departmentDto) {
        Department department = departmentRepository.findById(departmentDto.getId());
        if(department != null) {
            department.setName(departmentDto.getName());
            departmentRepository.save(department);
            return DepartmentMapper.toDepartmentDto(department);
        }else{
            return null;
        }
    }

    @Override
    public void deleteDepartment(long id) {
        Department department = departmentRepository.findById(id);
        if (department != null) {
            departmentRepository.delete(department);
        } else {
            log.error("Department not found");
        }
    }

}
