package team.placeholder.internalprojectsmanagementsystem.repository.project;

import org.springframework.data.jpa.repository.JpaRepository;
import team.placeholder.internalprojectsmanagementsystem.model.project.Notification;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    Notification findById(long id);
}
