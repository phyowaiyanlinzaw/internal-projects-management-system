package team.placeholder.internalprojectsmanagementsystem.service.noti;

import org.springframework.stereotype.Service;
import team.placeholder.internalprojectsmanagementsystem.dto.uidto.NotiDto;
import team.placeholder.internalprojectsmanagementsystem.model.project.Notification;

import java.util.List;

public interface NotificationService {

    public void save(String description, long userId, String eventName, Object... objects);

    public void sendNotification(NotiDto notiDto, long userId, String eventName, Object... objects);

    public List<NotiDto> getAllNotificationByUserId(long id);

    public void deleteNotification(long id);

}
