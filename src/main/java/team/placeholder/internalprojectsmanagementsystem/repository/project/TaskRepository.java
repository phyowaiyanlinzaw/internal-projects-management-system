package team.placeholder.internalprojectsmanagementsystem.repository.project;

import org.springframework.data.jpa.repository.JpaRepository;
import team.placeholder.internalprojectsmanagementsystem.model.project.Tasks;
import team.placeholder.internalprojectsmanagementsystem.model.project.projectenums.TaskStatus;

import java.util.List;

public interface TaskRepository extends JpaRepository<Tasks, Long> {

    Tasks findById(long id);

    List<Tasks> findByUserId(Long id);

    List<Tasks> findTasksByProjectIdAndUserId(long projectId, long userId);
    long countByProjectIdAndStatus(long id, TaskStatus status);

    long countByProjectId(long id);

    List<Tasks> findByProjectId(long id);

}
