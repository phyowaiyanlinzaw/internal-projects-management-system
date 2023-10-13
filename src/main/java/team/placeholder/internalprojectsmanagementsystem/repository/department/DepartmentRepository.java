package team.placeholder.internalprojectsmanagementsystem.repository.department;

import org.springframework.data.jpa.repository.JpaRepository;
import team.placeholder.internalprojectsmanagementsystem.model.department.Department;

import java.util.List;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

    //TODO: Implement Department Repository
    Department findById(long id);

    Department findByName(String name);

    List<Department> selectAllDepartment();


}
