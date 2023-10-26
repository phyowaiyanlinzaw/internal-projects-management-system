//package team.placeholder.internalprojectsmanagementsystem.service.impl.project;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.mockito.junit.jupiter.MockitoExtension;
//import team.placeholder.internalprojectsmanagementsystem.model.project.TaskNotification;
//import team.placeholder.internalprojectsmanagementsystem.repository.project.NotificationRepository;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//class TaskNotificationServiceImplTest {
//
//    @Mock
//    private NotificationRepository notificationRepository;
//
//    @InjectMocks
//    private NotificationServiceImpl notificationService;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.initMocks(this);
//    }
//
//    @Test
//    public void testSaveNotification() {
//        TaskNotification taskNotification = new TaskNotification();
//        taskNotification.setDescription("IT");
//        notificationRepository.save(taskNotification);
//        verify(notificationRepository, times(1)).save(taskNotification);
//    }
//
//    @Test
//    public void testGetNotificationById() {
//        TaskNotification taskNotification = new TaskNotification();
//        taskNotification.setId(1L);
//        taskNotification.setDescription("IT");
//        when(notificationRepository.findById(taskNotification.getId())).thenReturn(taskNotification);
//
//        TaskNotification taskNotification1 = notificationRepository.findById(taskNotification.getId());
//        assertEquals("IT", taskNotification1.getDescription());
//        verify(notificationRepository, times(1)).findById(taskNotification.getId());
//    }
//
//    @Test
//    public void testGetAllNotifications() {
//        List<TaskNotification> list = new ArrayList<>();
//        TaskNotification taskNotification1 = new TaskNotification();
//        taskNotification1.setDescription("IT");
//
//        TaskNotification taskNotification2 = new TaskNotification();
//        taskNotification2.setDescription("HR");
//
//        list.add(taskNotification1);
//        list.add(taskNotification2);
//
//        when(notificationRepository.findAll()).thenReturn(list);
//        List<TaskNotification> taskNotifications = notificationRepository.findAll();
//        assertEquals(2, taskNotifications.size());
//        verify(notificationRepository, times(1)).findAll();
//    }
//
//
//
//}