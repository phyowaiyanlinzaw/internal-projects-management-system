package team.placeholder.internalprojectsmanagementsystem.repository.NotiRepo;

import org.springframework.data.jpa.repository.JpaRepository;
import team.placeholder.internalprojectsmanagementsystem.model.project.Notification;

public interface NotiRepo extends JpaRepository<Notification, Long> {
}
