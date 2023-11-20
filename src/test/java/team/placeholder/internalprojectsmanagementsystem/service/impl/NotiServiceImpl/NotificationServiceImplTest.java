package team.placeholder.internalprojectsmanagementsystem.service.impl.NotiServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import team.placeholder.internalprojectsmanagementsystem.dto.uidto.NotiDto;
import team.placeholder.internalprojectsmanagementsystem.model.project.Notification;
import team.placeholder.internalprojectsmanagementsystem.repository.project.NotificationRepository;
import team.placeholder.internalprojectsmanagementsystem.repository.user.UserRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class NotificationServiceImplTest {

    @Mock
    private NotificationServiceImpl notificationService;

    @Mock
    private NotificationRepository notificationRepository;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        notificationService = new NotificationServiceImpl(notificationRepository, modelMapper, userRepository);

    }

    @Test
    public void testSave() {
        // Test data
        String description = "Test notification";
        long userId = 1L;
        String eventName = "testEvent";
        Object[] objects = new Object[0];

        // Mock behavior
        Notification savedNotification = new Notification();
        savedNotification.setId(1L);
        when(notificationRepository.save(any(Notification.class))).thenReturn(savedNotification);

        // Call the method to be tested
        notificationService.save(description, userId, eventName, objects);

        // Verify the behavior
        verify(notificationRepository, times(1)).save(any(Notification.class));
        verify(userRepository, times(1)).getReferenceById(userId);
    }

    @Test
    public void testSendNotification() {
        // Test data
        NotiDto notiDto = new NotiDto();
        long userId = 1L;
        String eventName = "testEvent";
        Object[] objects = new Object[0];

        // Call the method to be tested
        notificationService.sendNotification(notiDto, userId, eventName, objects);

    }

    @Test
    public void testGetAllNotificationByUserId() {
        // Test data
        long userId = 1L;

        // Mock behavior
        List<Notification> notificationList = new ArrayList<>();
        when(notificationRepository.findAllByUserId(userId)).thenReturn(notificationList);

        // Call the method to be tested
        List<NotiDto> result = notificationService.getAllNotificationByUserId(userId);

        // Verify the behavior
        assertEquals(notificationList.size(), result.size());
    }


    @Test
    void deleteNotification() {
        // Test data
        long id = 1L;

        // Call the method to be tested
        notificationService.deleteNotification(id);

        // Verify the behavior
        verify(notificationRepository, times(1)).deleteById(id);
    }
}