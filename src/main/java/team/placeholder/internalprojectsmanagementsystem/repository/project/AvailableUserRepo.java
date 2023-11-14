package team.placeholder.internalprojectsmanagementsystem.repository.project;

import org.springframework.data.jpa.repository.JpaRepository;
import team.placeholder.internalprojectsmanagementsystem.model.project.AvailableUser;

import java.util.List;

public interface AvailableUserRepo extends JpaRepository<AvailableUser, Long> {

    List<AvailableUser> findAllByAvaliableIsTrue();

    AvailableUser findByUserId(long userId);

    List<AvailableUser> findByUserIdIn(List<Long> userIds);

}
