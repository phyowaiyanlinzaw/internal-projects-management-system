package team.placeholder.internalprojectsmanagementsystem.repository.project;

import org.springframework.data.jpa.repository.JpaRepository;
import team.placeholder.internalprojectsmanagementsystem.model.project.TaskNotification;

public interface NotificationRepository extends JpaRepository<TaskNotification, Long> {
    TaskNotification findById(long id);
}
