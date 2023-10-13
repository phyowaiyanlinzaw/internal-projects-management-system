package team.placeholder.internalprojectsmanagementsystem.dto.mapper.project;

import team.placeholder.internalprojectsmanagementsystem.dto.model.project.NotificationDto;
import team.placeholder.internalprojectsmanagementsystem.model.project.Notification;

public class NotificationMapper {
    public static NotificationDto toNotificationDto(Notification notification){
        if(notification == null){
            return null;
        }
        NotificationDto notificationDto = new NotificationDto();
        notificationDto.setId(notification.getId());
        notificationDto.setDescription(notification.getDescription());
        return notificationDto;
    }
}
