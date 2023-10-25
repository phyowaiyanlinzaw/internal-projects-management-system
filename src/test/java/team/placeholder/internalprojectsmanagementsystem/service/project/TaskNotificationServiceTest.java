package team.placeholder.internalprojectsmanagementsystem.service.project;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import team.placeholder.internalprojectsmanagementsystem.dto.model.project.NotificationDto;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class TaskNotificationServiceTest {

    @Mock
    private NotificationService notificationService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void testSaveNotification() {
         NotificationDto notificationDto = new NotificationDto();
         Mockito.when(notificationService.save(notificationDto)).thenReturn(notificationDto);
         NotificationDto savedNotification = notificationService.save(notificationDto);
         assertEquals(notificationDto, savedNotification);
    }

    @Test
    public void testGetNotificationById() {
        long id = 1;
        NotificationDto notificationDto = new NotificationDto();
        Mockito.when(notificationService.getNotificationById(id)).thenReturn(notificationDto);
        NotificationDto notificationById = notificationService.getNotificationById(1L);
        assertEquals(notificationDto, notificationById);
    }

    @Test
    public void testGetAllNotifications() {
        List<NotificationDto> notificationDto = new ArrayList<>();

        Mockito.when(notificationService.getAllNotifications()).thenReturn(notificationDto);

        List<NotificationDto> allNotifications = notificationService.getAllNotifications();
        assertEquals(notificationDto, allNotifications);
    }


}