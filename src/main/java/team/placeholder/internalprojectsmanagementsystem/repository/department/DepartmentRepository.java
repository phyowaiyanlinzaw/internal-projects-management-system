package team.placeholder.internalprojectsmanagementsystem.repository.department;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import team.placeholder.internalprojectsmanagementsystem.model.department.Department;

import java.util.List;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

    Department findById(long id);

    Department findByName(String name);

    List<Department> findAll();

    long count();




}
