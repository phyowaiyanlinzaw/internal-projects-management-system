package team.placeholder.internalprojectsmanagementsystem.repository.project;

import org.springframework.data.jpa.repository.JpaRepository;
import team.placeholder.internalprojectsmanagementsystem.model.project.Project;
import team.placeholder.internalprojectsmanagementsystem.model.user.Client;

import java.util.Collection;
import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {

    Project findById(long id);
    Project findByName(String name);

    long countAllByUsersId(long id);

    List<Project> findAllByProjectManagerId(long id);
    List<Project> findAllByReviewsId(long id);

    List<Project> findAllByUsersId(long id);

    List<Project> findAllByClientIsNotNull();

    List<Project> findAllByProjectManagerIsNotNull();

    List<Project> findByDepartmentId(long id);

    List<Project> findByDepartmentName(String name);

    Project findByUsersId(long id);



}
