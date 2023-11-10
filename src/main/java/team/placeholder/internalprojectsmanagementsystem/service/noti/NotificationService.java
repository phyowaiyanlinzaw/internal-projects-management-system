package team.placeholder.internalprojectsmanagementsystem.service.noti;

import org.springframework.stereotype.Service;
import team.placeholder.internalprojectsmanagementsystem.dto.uidto.NotiDto;
import team.placeholder.internalprojectsmanagementsystem.model.project.Notification;

import java.util.List;

public interface NotificationService {

    public void save(NotiDto notiDto);

    public void sendNotification(NotiDto notiDto);

    public List<Notification> getAllNotification();

    public void deleteNotification(long id);

}
