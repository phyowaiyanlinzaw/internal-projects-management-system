package team.placeholder.internalprojectsmanagementsystem.service.project;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import team.placeholder.internalprojectsmanagementsystem.model.project.AvailableUser;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AvailableEmployeeServiceTest {

    @Mock
    private AvailableEmployeeService availableEmployeeService;


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void save() {
        AvailableUser availableUser = new AvailableUser();
        availableEmployeeService.save(availableUser);
    }

    @Test
    void getAllAvailableUser() {
        List<AvailableUser> availableUserList = availableEmployeeService.getAllAvailableUser();
        Mockito.when(availableEmployeeService.getAllAvailableUser()).thenReturn(availableUserList);
        List<AvailableUser> availableUserList1 = availableEmployeeService.getAllAvailableUser();
        assertEquals(availableUserList, availableUserList1);

    }

    @Test
    void getAvailableUserByUserId() {
        long userId = 1;
        AvailableUser availableUser = new AvailableUser();
        Mockito.when(availableEmployeeService.getAvailableUserByUserId(userId)).thenReturn(availableUser);
        AvailableUser availableUser1 = availableEmployeeService.getAvailableUserByUserId(userId);
        assertEquals(availableUser, availableUser1);
    }
}