package team.placeholder.internalprojectsmanagementsystem.repository.user;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import team.placeholder.internalprojectsmanagementsystem.model.project.AvailableUser;
import team.placeholder.internalprojectsmanagementsystem.model.user.User;
import team.placeholder.internalprojectsmanagementsystem.model.user.userenums.Role;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);
    User findByName(String name);
    User findById(long id);

    List<User> findAllByRole(Role role);

    Long countAllByRole(Role role);

    List<User> findAllByProjectManagerId(Long id);

    User findUserByDepartmentIdAndRole(Long departmentId, Role role);

    Long countAllByDepartmentId(Long departmentId);

    List<User> findAllByProjectsId(Long projectId);
}
