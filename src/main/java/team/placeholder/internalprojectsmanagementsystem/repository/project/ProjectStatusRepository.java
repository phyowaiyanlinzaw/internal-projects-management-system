package team.placeholder.internalprojectsmanagementsystem.repository.project;

import org.springframework.data.jpa.repository.JpaRepository;
import team.placeholder.internalprojectsmanagementsystem.model.project.ProjectStatus;

public interface ProjectStatusRepository extends JpaRepository<ProjectStatus, Long> {

    ProjectStatus findById(long id);


}
