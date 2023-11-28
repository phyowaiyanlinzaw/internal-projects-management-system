package team.placeholder.internalprojectsmanagementsystem.service.impl.NotiServiceImpl;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import team.placeholder.internalprojectsmanagementsystem.dto.uidto.NotiDto;
import team.placeholder.internalprojectsmanagementsystem.model.project.Notification;

import team.placeholder.internalprojectsmanagementsystem.repository.project.NotificationRepository;
import team.placeholder.internalprojectsmanagementsystem.repository.user.UserRepository;

import java.util.ArrayList;
import java.util.List;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class NotificationServiceImplTest {

    @Mock
    private NotificationRepository notificationRepository;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private NotificationServiceImpl notificationService;

    @Spy
    private Logger logger = LoggerFactory.getLogger(NotificationServiceImpl.class);


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        notificationService = new NotificationServiceImpl(notificationRepository, modelMapper, userRepository);
    }

    @Test
    public void testDeleteNotificationWhenNotificationExistsThenDeleteNotification() {
        long id = 1L;
        notificationService.deleteNotification(id);
        Mockito.verify(notificationRepository, times(1)).deleteById(id);
    }


    @Test
    public void testSave() {
        String description = "Test notification";
        long userId = 1L;
        String eventName = "testEvent";
        Object[] objects = {new Object()};

        Notification savedNotification = new Notification();
        savedNotification.setId(1L);
        when(notificationRepository.save(any(Notification.class))).thenReturn(savedNotification);

        notificationService.save(description, userId, eventName, objects);

        verify(notificationRepository, times(1)).save(any(Notification.class));
        verify(userRepository, times(1)).getReferenceById(userId);
    }


    @Test
    public void testSaveWhenNoObjectThenSendNotification() {
        String description = "Test notification";
        long userId = 1L;
        String eventName = "testEvent";
        Object[] objects = new Object[0];

        Notification savedNotification = new Notification();
        savedNotification.setId(1L);
        when(notificationRepository.save(any(Notification.class))).thenReturn(savedNotification);

        notificationService.save(description, userId, eventName, objects);

        verify(notificationRepository, times(1)).save(any(Notification.class));
        verify(userRepository, times(1)).getReferenceById(userId);
    }


    @Test
    public void testSendNotification() {
        NotiDto notiDto = new NotiDto();
        long userId = 1L;
        String eventName = "testEvent";
        Object[] objects = new Object[0];

        notificationService.sendNotification(notiDto, userId, eventName, objects);
    }

    @Test
    public void testGetAllNotificationByUserId() {
        long userId = 1L;
        List<Notification> notificationList = new ArrayList<>();
        when(notificationRepository.findAllByUserId(userId)).thenReturn(notificationList);

        List<NotiDto> result = notificationService.getAllNotificationByUserId(userId);

        assertEquals(notificationList.size(), result.size());
    }

    @Test
    void deleteNotification() {
        long id = 1L;
        notificationService.deleteNotification(id);
        verify(notificationRepository, times(1)).deleteById(id);
    }
}