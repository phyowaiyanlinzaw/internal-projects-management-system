package team.placeholder.internalprojectsmanagementsystem.controller.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import team.placeholder.internalprojectsmanagementsystem.dto.uidto.NotiDto;
import team.placeholder.internalprojectsmanagementsystem.service.impl.NotiServiceImpl.NotificationServiceImpl;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class NotificationControllerTest {

    @Mock
    private NotificationServiceImpl notificationService;

    @InjectMocks
    private NotificationController notificationController;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }


    @Test
    public void testGetAllNotiByUserId(){
        long userId = 1L;

        List<NotiDto> notiDtoList = Arrays.asList(new NotiDto());
        when(notificationService.getAllNotificationByUserId(userId)).thenReturn(notiDtoList);

        ResponseEntity<List<NotiDto>> responseEntity = notificationController.getAllNotiByUserId(userId);

        assertEquals(200, responseEntity.getStatusCodeValue());
        assertEquals(notiDtoList, responseEntity.getBody());

    }

    @Test
    public void testDeleteAllNotiByUserId(){

        long userId = 1L;

        List<NotiDto> notiDtoList = Arrays.asList(new NotiDto());
        when(notificationService.getAllNotificationByUserId(userId)).thenReturn(notiDtoList);

        notificationController.deleteAllNotiByUserId(userId);

        verify(notificationService, times(notiDtoList.size())).deleteNotification(anyLong());
    }

}