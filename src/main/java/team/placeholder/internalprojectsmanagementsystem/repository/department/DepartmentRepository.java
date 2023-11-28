package team.placeholder.internalprojectsmanagementsystem.repository.department;

import org.springframework.data.jpa.repository.JpaRepository;
import team.placeholder.internalprojectsmanagementsystem.model.department.Department;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
    Department findById(long id);
    Department findByName(String name);
    long count();


}
