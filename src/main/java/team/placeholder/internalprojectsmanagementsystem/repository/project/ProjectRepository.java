package team.placeholder.internalprojectsmanagementsystem.repository.project;

import org.springframework.data.jpa.repository.JpaRepository;
import team.placeholder.internalprojectsmanagementsystem.model.project.Project;
import team.placeholder.internalprojectsmanagementsystem.model.user.Client;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {

    List<Project> findAll();
    Project findById(long id);
    Project findByName(String name);

    long countByDepartmentId(long id);
}
