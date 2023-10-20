package team.placeholder.internalprojectsmanagementsystem.service.impl.project;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import team.placeholder.internalprojectsmanagementsystem.dto.mapper.project.NotificationMapper;
import team.placeholder.internalprojectsmanagementsystem.dto.model.project.NotificationDto;
import team.placeholder.internalprojectsmanagementsystem.model.project.Notification;
import team.placeholder.internalprojectsmanagementsystem.repository.project.NotificationRepository;
import team.placeholder.internalprojectsmanagementsystem.service.project.NotificationService;

import java.util.List;
import java.util.stream.Collectors;


@Service
@Slf4j
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private NotificationRepository notificationRepository;

    @Override
    public NotificationDto save(NotificationDto notificationDto) {
        Notification notification = new Notification();
        notification.setDescription(notificationDto.getDescription());
        notificationRepository.save(notification);
        return NotificationMapper.toNotificationDto(notificationRepository.save(notification));
    }

    @Override
    public NotificationDto getNotificationById(long id) {
        Notification notification = notificationRepository.findById(id);
        if(notification != null) {
            return NotificationMapper.toNotificationDto(notification);
        }else{
            return null;
        }
    }

    @Override
    public List<NotificationDto> getAllNotifications() {
        List<Notification> notifications = notificationRepository.findAll();
        return notifications.stream()
                .map(NotificationMapper::toNotificationDto)
                .collect(Collectors.toList());
    }
}
