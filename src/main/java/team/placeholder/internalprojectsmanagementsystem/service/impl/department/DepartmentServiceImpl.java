package team.placeholder.internalprojectsmanagementsystem.service.impl.department;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import team.placeholder.internalprojectsmanagementsystem.dto.model.department.DepartmentDto;
import team.placeholder.internalprojectsmanagementsystem.dto.model.user.UserDto;
import team.placeholder.internalprojectsmanagementsystem.model.department.Department;
import team.placeholder.internalprojectsmanagementsystem.repository.department.DepartmentRepository;
import team.placeholder.internalprojectsmanagementsystem.service.department.DepartmentService;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final ModelMapper modelMapper;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository, ModelMapper modelMapper) {
        this.departmentRepository = departmentRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public DepartmentDto save(DepartmentDto departmentDto) {
        Department department = modelMapper.map(departmentDto, Department.class);
        Department savedDepartment = departmentRepository.save(department);
        return modelMapper.map(savedDepartment, DepartmentDto.class);
    }
    @Override
    public List<DepartmentDto> getAllDepartments() {
        List<Department> departments = departmentRepository.findAll();

        List<DepartmentDto> departmentDtos = new ArrayList<>();

        for(Department department : departments) {
            DepartmentDto departmentDto = modelMapper.map(department, DepartmentDto.class);
            for(UserDto user : departmentDto.getUsers()) {

                if(user.getProjectsByUsers() != null)
                    user.getProjectsByUsers().clear();
                if(user.getProjectsByProjectManager() != null)
                    user.getProjectsByProjectManager().clear();
            }
            departmentDtos.add(departmentDto);
        }
        return departmentDtos;
    }
    @Override
    public DepartmentDto getDepartmentById(long id) {
        Department department = departmentRepository.findById(id);
        if(department != null) {
            return modelMapper.map(department, DepartmentDto.class);
        }else{
            return null;
        }
    }

    @Override
    public DepartmentDto getDepartmentByName(String name) {
        Department department = departmentRepository.findByName(name);
        if(department != null) {
            return modelMapper.map(department, DepartmentDto.class);
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
            return modelMapper.map(department, DepartmentDto.class);
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
    @Override
    public long getDeprtmentCount() {
        return departmentRepository.count();
    }


}
