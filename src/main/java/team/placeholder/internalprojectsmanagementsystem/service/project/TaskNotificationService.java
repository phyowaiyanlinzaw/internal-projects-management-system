package team.placeholder.internalprojectsmanagementsystem.service.project;

import team.placeholder.internalprojectsmanagementsystem.dto.model.project.TaskNotificationDto;

public interface TaskNotificationService {

        TaskNotificationDto save(TaskNotificationDto taskNotificationDto);

        TaskNotificationDto getTaskNotificationById(long id);

        void deleteTaskNotification(long id);
}
