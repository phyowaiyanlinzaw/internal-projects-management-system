package team.placeholder.internalprojectsmanagementsystem.repository.project;

import org.springframework.data.jpa.repository.JpaRepository;
import team.placeholder.internalprojectsmanagementsystem.dto.uidto.NotiDto;
import team.placeholder.internalprojectsmanagementsystem.model.project.Notification;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {

    List<Notification> findAllByUserId(long id);

}
