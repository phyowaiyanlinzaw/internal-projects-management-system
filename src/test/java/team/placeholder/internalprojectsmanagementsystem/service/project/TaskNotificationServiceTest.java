package team.placeholder.internalprojectsmanagementsystem.service.project;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import team.placeholder.internalprojectsmanagementsystem.dto.model.project.TaskNotificationDto;
import team.placeholder.internalprojectsmanagementsystem.service.noti.NotificationService;

import java.util.ArrayList;


import static org.junit.jupiter.api.Assertions.*;


public class TaskNotificationServiceTest {

    @Mock
    private TaskNotificationService taskNotificationService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void testSaveNotification() {
       TaskNotificationDto tasksNotificationDto = new TaskNotificationDto();
        Mockito.when(taskNotificationService.save(tasksNotificationDto)).thenReturn(tasksNotificationDto);
        TaskNotificationDto savedNotification = taskNotificationService.save(tasksNotificationDto);
        assertEquals(tasksNotificationDto, savedNotification);
    }





}