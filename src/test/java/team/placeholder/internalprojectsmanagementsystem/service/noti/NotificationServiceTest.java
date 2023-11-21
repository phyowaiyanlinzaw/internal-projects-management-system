package team.placeholder.internalprojectsmanagementsystem.service.noti;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import team.placeholder.internalprojectsmanagementsystem.dto.uidto.NotiDto;
import team.placeholder.internalprojectsmanagementsystem.model.project.Notification;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class NotificationServiceTest {

    @Mock
    private NotificationService notificationService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testSaveWhenGivenNotificationThenReturnSameNotification() {
        String description = "Test Description";
        long userId = 1L;
        String eventName = "Test Event";
        doNothing().when(notificationService).save(description, userId, eventName);
        notificationService.save(description, userId, eventName);
        verify(notificationService, times(1)).save(description, userId, eventName);
    }

    @Test
    void testSaveWhenNotificationThenReturnNotification() {
        String description = "Test Description";
        long userId = 1L;
        String eventName = "Test Event";
        Object[] objects = new Object[]{"Test Object"};

        doNothing().when(notificationService).save(description, userId, eventName, objects);

        notificationService.save(description, userId, eventName, objects);

        verify(notificationService, times(1)).save(description, userId, eventName, objects);
    }

    @Test
    void testGetAllNotificationByUserIdWhenCalledThenCallGetAllNotificationByUserIdMethodWithCorrectParameter() {
        long userId = 1L;
        NotiDto notiDto = new NotiDto();
        notiDto.setId(1L);
        notiDto.setDescription("Test Description");
        notiDto.setNoti_time(System.currentTimeMillis());
        List<NotiDto> notiDtoList = Collections.singletonList(notiDto);
        when(notificationService.getAllNotificationByUserId(userId)).thenReturn(notiDtoList);
        List<NotiDto> returnedNotiDtoList = notificationService.getAllNotificationByUserId(userId);
        assertEquals(notiDtoList, returnedNotiDtoList);
        verify(notificationService, times(1)).getAllNotificationByUserId(userId);
    }


    @Test
    void testSendNotificationWhenCalledThenCallSendNotificationMethodWithCorrectParameters() {
        NotiDto notiDto = new NotiDto();
        notiDto.setId(1L);
        notiDto.setDescription("Test Description");
        notiDto.setNoti_time(System.currentTimeMillis());
        long userId = 1L;
        String eventName = "Test Event";
        doNothing().when(notificationService).sendNotification(notiDto, userId, eventName);
        notificationService.sendNotification(notiDto, userId, eventName);
        verify(notificationService, times(1)).sendNotification(notiDto, userId, eventName);
    }


    @Test
    void sendNotification() {
        NotiDto notiDto = new NotiDto();
        notiDto.setId(1L);
        notiDto.setDescription("Test Description");
        notiDto.setNoti_time(System.currentTimeMillis());

        doNothing().when(notificationService).sendNotification(notiDto, 1L, "Test Event", "Test Object");

        notificationService.sendNotification(notiDto, 1L, "Test Event", "Test Object");

        verify(notificationService, times(1)).sendNotification(notiDto, 1L, "Test Event", "Test Object");
    }

    @Test
    void getAllNotificationByUserId() {
        NotiDto notiDto = new NotiDto();
        notiDto.setId(1L);
        notiDto.setDescription("Test Description");
        notiDto.setNoti_time(System.currentTimeMillis());

        List<NotiDto> notiDtoList = Arrays.asList(notiDto);

        when(notificationService.getAllNotificationByUserId(1L)).thenReturn(notiDtoList);

        List<NotiDto> returnedNotiDtoList = notificationService.getAllNotificationByUserId(1L);

        assertEquals(notiDtoList, returnedNotiDtoList);
    }

    @Test
    void deleteNotification() {
        doNothing().when(notificationService).deleteNotification(1L);

        notificationService.deleteNotification(1L);

        verify(notificationService, times(1)).deleteNotification(1L);
    }

    @Test
    void testDeleteNotificationWhenCalledThenCallDeleteNotificationMethodWithCorrectParameter() {
        long notificationId = 1L;
        doNothing().when(notificationService).deleteNotification(notificationId);
        notificationService.deleteNotification(notificationId);
        verify(notificationService, times(1)).deleteNotification(notificationId);
    }



}