package team.placeholder.internalprojectsmanagementsystem.service.impl.NotiServiceImpl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pusher.rest.Pusher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import team.placeholder.internalprojectsmanagementsystem.dto.uidto.NotiDto;
import team.placeholder.internalprojectsmanagementsystem.model.project.Notification;
import team.placeholder.internalprojectsmanagementsystem.repository.project.NotificationRepository;
import team.placeholder.internalprojectsmanagementsystem.repository.user.UserRepository;
import team.placeholder.internalprojectsmanagementsystem.service.noti.NotificationService;

import java.util.Collections;
import java.util.List;


@Service
@Slf4j
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notiRepo;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;

    @Override
    public void save(String description, long userId, String eventName, Object... objects) {

        log.info("In Notification save method");
        log.info("This user will get noti : {}", (Object) userId);
        try {

            log.info("In try statement");

            Notification notification = new Notification();

            notification.setDescription(description);
            notification.setNoti_time(System.currentTimeMillis());
            notification.setUser(userRepository.getReferenceById(userId));

            Notification notification1 = notiRepo.save(notification);

            NotiDto notiDto = modelMapper.map(notification, NotiDto.class);

            if(objects.length > 0) {
                for (Object object : objects) {
                    sendNotification(notiDto, userId, eventName, object);
                }
            }
            else {
                sendNotification(notiDto, userId, eventName);
            }

        } catch (Exception e) {
            log.error("Error while sending notification: {}", e.getMessage());
            log.error("Stack Trace: ", e); // This will automatically print the stack trace
            log.error("Cause: {}", e.getCause());
            log.error("Filled Stack Trace: ", e.fillInStackTrace());
        }
        log.info("Notification is saved");
    }

    @Override
    public void sendNotification(NotiDto notiDto, long userId, String eventName, Object... objects)
    {
        log.info("In sendNotification method and setting necessary variables");
        String appId = "1629453";
        String key = "3c0b3426bd0875be392f";
        String secret = "53343242223c164f3904";
        String cluster = "ap1";

        try (Pusher pusher = new Pusher(appId, key, secret)) {

            ObjectMapper mapper = new ObjectMapper();
            String notiJson = mapper.writeValueAsString(notiDto);

            log.info("In try statement");

            pusher.setCluster("ap1");
            pusher.setEncrypted(true);

            if(objects.length > 0) {
                for (Object object : objects) {
                    pusher.trigger("my-channel-" + userId, eventName, "{\"notification\":" + notiJson + ", \"object\":" + mapper.writeValueAsString(object) + "}");
                }
            }
            else {
                pusher.trigger("my-channel-" + userId, eventName, "{\"notification\":" + notiJson + "}");
            }

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
    public List<NotiDto> getAllNotificationByUserId(long id) {

        List<Notification> notificationList = notiRepo.findAllByUserId(id);

        return notificationList.stream().map(notification -> modelMapper.map(notification, NotiDto.class)).toList();
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
