package team.placeholder.internalprojectsmanagementsystem.dto.mapper.project;

import team.placeholder.internalprojectsmanagementsystem.dto.model.project.NotificationDto;
import team.placeholder.internalprojectsmanagementsystem.model.project.TaskNotification;

public class NotificationMapper {
    public static NotificationDto toNotificationDto(TaskNotification taskNotification){
        if(taskNotification == null){
            return null;
        }
        NotificationDto notificationDto = new NotificationDto();
        notificationDto.setId(taskNotification.getId());
        notificationDto.setDescription(taskNotification.getDescription());
        return notificationDto;
    }
}
