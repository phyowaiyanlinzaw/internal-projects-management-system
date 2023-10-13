package team.placeholder.internalprojectsmanagementsystem.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import team.placeholder.internalprojectsmanagementsystem.model.user.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
    User findByName(String name);
    List<User> selectAllUser();
    User findById(long id);
    User selectAllUserById(int id);




}
