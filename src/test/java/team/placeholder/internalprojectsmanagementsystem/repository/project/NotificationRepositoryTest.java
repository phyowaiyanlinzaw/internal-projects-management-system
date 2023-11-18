package team.placeholder.internalprojectsmanagementsystem.repository.project;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import team.placeholder.internalprojectsmanagementsystem.model.project.Notification;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class NotificationRepositoryTest {

    @Mock
    NotificationRepository notificationRepository;
    @BeforeEach
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindAllByUserId(){
        long userId = 1L;

        List<Notification> expectedNotifications = new ArrayList<>();
        expectedNotifications.add(new Notification());
        expectedNotifications.add(new Notification());
        expectedNotifications.add(new Notification());

        when(notificationRepository.findAllByUserId(userId)).thenReturn(expectedNotifications);

        List<Notification> result = notificationRepository.findAllByUserId(userId);

        assertEquals(expectedNotifications.size(), result.size());
        for (int i = 0; i < expectedNotifications.size(); i++) {
            assertEquals(expectedNotifications.get(i), result.get(i));
        }
    }

}