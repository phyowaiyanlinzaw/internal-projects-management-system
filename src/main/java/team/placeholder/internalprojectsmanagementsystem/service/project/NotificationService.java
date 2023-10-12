package team.placeholder.internalprojectsmanagementsystem.service.project;

import team.placeholder.internalprojectsmanagementsystem.dto.model.project.NotificationDto;

import java.util.List;

public interface NotificationService {

    NotificationDto save(NotificationDto notificationDto);

    NotificationDto getNotificationById(int id);

    List<NotificationDto> getAllNotifications();
}
