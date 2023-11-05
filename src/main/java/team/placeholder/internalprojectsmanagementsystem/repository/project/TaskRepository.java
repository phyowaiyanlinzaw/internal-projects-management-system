package team.placeholder.internalprojectsmanagementsystem.repository.project;

import org.springframework.data.jpa.repository.JpaRepository;
import team.placeholder.internalprojectsmanagementsystem.model.project.Tasks;

import java.util.List;

public interface TaskRepository extends JpaRepository<Tasks, Long> {

    List<Tasks> findByProjectId(long id);

    List<Tasks> findByUserId(long id);
}
