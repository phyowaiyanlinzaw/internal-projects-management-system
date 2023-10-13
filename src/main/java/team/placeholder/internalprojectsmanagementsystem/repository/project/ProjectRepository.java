package team.placeholder.internalprojectsmanagementsystem.repository.project;

import org.springframework.data.jpa.repository.JpaRepository;
import team.placeholder.internalprojectsmanagementsystem.model.project.Project;
import team.placeholder.internalprojectsmanagementsystem.model.user.Client;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {

    //TODO: Implement Project Repository
    List<Project> selectAllProject();
    Project findById(long id);
    Project findByName(String name);

    List<Project> selectAllProjectById(long id);

}
