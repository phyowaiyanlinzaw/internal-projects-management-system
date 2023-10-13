package team.placeholder.internalprojectsmanagementsystem.repository.user;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import team.placeholder.internalprojectsmanagementsystem.model.user.User;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
    User findByName(String name);

    List<User> findAll();
    User findById(long id);


}
