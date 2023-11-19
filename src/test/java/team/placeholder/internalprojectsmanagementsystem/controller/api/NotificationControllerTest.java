package team.placeholder.internalprojectsmanagementsystem.controller.api;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import team.placeholder.internalprojectsmanagementsystem.dto.uidto.NotiDto;
import team.placeholder.internalprojectsmanagementsystem.service.impl.NotiServiceImpl.NotificationServiceImpl;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class NotificationControllerTest {

    @Mock
    private NotificationServiceImpl notificationService;

    @InjectMocks
    private NotificationController notificationController;


    @Test
    public void testGetAllNotiByUserId(){

    }

    @Test
    public void testDeleteAllNotiByUserId(){

    }

}