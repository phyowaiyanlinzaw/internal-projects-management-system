package team.placeholder.internalprojectsmanagementsystem.service.impl.department;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import team.placeholder.internalprojectsmanagementsystem.dto.model.department.DepartmentDto;
import team.placeholder.internalprojectsmanagementsystem.model.department.Department;
import team.placeholder.internalprojectsmanagementsystem.repository.department.DepartmentRepository;
import team.placeholder.internalprojectsmanagementsystem.service.department.DepartmentService;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final ModelMapper modelmapper;

    @Override
    public DepartmentDto save(DepartmentDto departmentDto) {
        Department department = modelmapper.map(departmentDto, Department.class);
        department.setName(departmentDto.getName());
       Department savedDepartment = departmentRepository.save(department);
        return modelmapper.map(savedDepartment, DepartmentDto.class);

    }



    @Override
    public List<DepartmentDto> getAllDepartments() {
        List<Department> departments = departmentRepository.findAll();
        return departments.stream().map(department -> modelmapper.map(department, DepartmentDto.class)).collect(Collectors.toList());
    }


    @Override
    public DepartmentDto getDepartmentById(long id) {
        Department department = departmentRepository.findById(id);
        if(department != null) {
            return modelmapper  .map(department, DepartmentDto.class);
        }else{
            return null;
        }
    }

    @Override
    public DepartmentDto getDepartmentByName(String name) {
        Department department = departmentRepository.findByName(name);


        if(department != null) {
            return modelmapper.map(department, DepartmentDto.class);
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
            return modelmapper.map(department, DepartmentDto.class);
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
