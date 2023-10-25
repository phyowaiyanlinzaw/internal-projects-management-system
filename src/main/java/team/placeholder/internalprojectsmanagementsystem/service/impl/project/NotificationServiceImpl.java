package team.placeholder.internalprojectsmanagementsystem.service.impl.project;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import team.placeholder.internalprojectsmanagementsystem.dto.mapper.project.NotificationMapper;
import team.placeholder.internalprojectsmanagementsystem.dto.model.project.NotificationDto;
import team.placeholder.internalprojectsmanagementsystem.model.project.TaskNotification;
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
        TaskNotification taskNotification = new TaskNotification();
        taskNotification.setDescription(notificationDto.getDescription());
        notificationRepository.save(taskNotification);
        return NotificationMapper.toNotificationDto(notificationRepository.save(taskNotification));
    }

    @Override
    public NotificationDto getNotificationById(long id) {
        TaskNotification taskNotification = notificationRepository.findById(id);
        if(taskNotification != null) {
            return NotificationMapper.toNotificationDto(taskNotification);
        }else{
            return null;
        }
    }

    @Override
    public List<NotificationDto> getAllNotifications() {
        List<TaskNotification> taskNotifications = notificationRepository.findAll();
        return taskNotifications.stream()
                .map(NotificationMapper::toNotificationDto)
                .collect(Collectors.toList());
    }
}
