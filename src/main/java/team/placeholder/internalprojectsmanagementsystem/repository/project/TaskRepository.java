package team.placeholder.internalprojectsmanagementsystem.repository.project;

import org.springframework.data.jpa.repository.JpaRepository;
import team.placeholder.internalprojectsmanagementsystem.model.project.Tasks;
import team.placeholder.internalprojectsmanagementsystem.model.project.projectenums.TaskStatus;

import java.util.List;

public interface TaskRepository extends JpaRepository<Tasks, Long> {

    List<Tasks> findByUserId(Long id);

    long countByProjectIdAndStatus(long id, TaskStatus status);

    long countByProjectId(long id);

}
