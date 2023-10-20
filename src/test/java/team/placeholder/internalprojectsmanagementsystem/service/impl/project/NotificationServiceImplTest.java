package team.placeholder.internalprojectsmanagementsystem.service.impl.project;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import team.placeholder.internalprojectsmanagementsystem.model.project.Notification;
import team.placeholder.internalprojectsmanagementsystem.repository.project.NotificationRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class NotificationServiceImplTest {

    @Mock
    private NotificationRepository notificationRepository;

    @InjectMocks
    private NotificationServiceImpl notificationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSaveNotification() {
        Notification notification = new Notification();
        notification.setDescription("IT");
        notificationRepository.save(notification);
        verify(notificationRepository, times(1)).save(notification);
    }

    @Test
    public void testGetNotificationById() {
        Notification notification = new Notification();
        notification.setId(1L);
        notification.setDescription("IT");
        when(notificationRepository.findById(notification.getId())).thenReturn(notification);

        Notification notification1 = notificationRepository.findById(notification.getId());
        assertEquals("IT", notification1.getDescription());
        verify(notificationRepository, times(1)).findById(notification.getId());
    }

    @Test
    public void testGetAllNotifications() {
        List<Notification> list = new ArrayList<>();
        Notification notification1 = new Notification();
        notification1.setDescription("IT");

        Notification notification2 = new Notification();
        notification2.setDescription("HR");

        list.add(notification1);
        list.add(notification2);

        when(notificationRepository.findAll()).thenReturn(list);
        List<Notification> notifications = notificationRepository.findAll();
        assertEquals(2, notifications.size());
        verify(notificationRepository, times(1)).findAll();
    }



}