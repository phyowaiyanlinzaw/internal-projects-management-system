package team.placeholder.internalprojectsmanagementsystem.service.impl.NotiServiceImpl;

import com.pusher.rest.Pusher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import team.placeholder.internalprojectsmanagementsystem.dto.uidto.NotiDto;
import team.placeholder.internalprojectsmanagementsystem.model.project.Notification;
import team.placeholder.internalprojectsmanagementsystem.repository.NotiRepo.NotiRepo;
import team.placeholder.internalprojectsmanagementsystem.repository.user.UserRepository;
import team.placeholder.internalprojectsmanagementsystem.service.noti.NotificationService;

import java.util.Collections;
import java.util.List;


@Service
@Slf4j
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final NotiRepo notiRepo;
    private final UserRepository userRepository;

    @Override
    public void save(NotiDto notiDto) {

        log.info("In Notification save method");
        log.info("This noti is going to save : {}", notiDto);
        try {

            log.info("In try statement");

            Notification notification = new Notification();

            long userId = notiDto.getUserId();

            notification.setDescription(notiDto.getDescription());
            notification.setNoti_time(notiDto.getNotiTime());
            notification.setUser(userRepository.getReferenceById(notiDto.getUserId()));

            notiRepo.save(notification);

            sendNotification(notiDto);

        } catch (Exception e) {
            log.error("Error while sending notification: {}", e.getMessage());
            log.error("Stack Trace: ", e); // This will automatically print the stack trace
            log.error("Cause: {}", e.getCause());
            log.error("Filled Stack Trace: ", e.fillInStackTrace());
        }
        log.info("Notification is saved");
    }

    @Override
    public void sendNotification(NotiDto notiDto)
    {
        log.info("In sendNotification method and setting necessary variables");
        String appId = "1529588";
        String key = "cd7cdc2857a652669f6c";
        String secret = "685bfa38bf47ea7cf2e5";
        String cluster = "ap1";

        try (Pusher pusher = new Pusher(appId, key, secret)) {

            log.info("In try statement");

            pusher.setCluster("ap1");
            pusher.setEncrypted(true);

            pusher.trigger("private-" + notiDto.getUserId(), "noti-event", "{\"notification\":\""+ notiDto +"\"}");

            log.info("Pusher is triggered");

        } catch (Exception e) {
            log.error("Error while sending notification: {}", e.getMessage());
            log.error("Stack Trace: ", e);
            log.error("Cause: {}", e.getCause());
            log.error("Filled Stack Trace: ", e.fillInStackTrace());
        }
        log.info("Notification is sent");
    }

    @Override
    public List<Notification> getAllNotification() {
        return null;
    }

    @Override
    public void deleteNotification(long id) {

        log.info("In deleteNotification method");

        try {
            log.info("In try statement");
            notiRepo.deleteById(id);
        } catch (Exception e) {
            log.error("Error while deleting notification: {}", e.getMessage());
            log.error("Stack Trace: ", e);
            log.error("Cause: {}", e.getCause());
            log.error("Filled Stack Trace: ", e.fillInStackTrace());
        }

        log.info("Notification is deleted");
    }
}
