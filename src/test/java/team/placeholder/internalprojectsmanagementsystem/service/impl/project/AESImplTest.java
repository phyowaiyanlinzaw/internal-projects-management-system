package team.placeholder.internalprojectsmanagementsystem.service.impl.project;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import team.placeholder.internalprojectsmanagementsystem.model.project.AvailableUser;
import team.placeholder.internalprojectsmanagementsystem.model.user.User;
import team.placeholder.internalprojectsmanagementsystem.repository.project.AvailableUserRepo;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AESImplTest {

    @Mock
    private AvailableUserRepo availableUserRepo;

    @InjectMocks
    private AESImpl availableEmployeeService;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSave(){
        AvailableUser availableUser = new AvailableUser();
        availableUser.setUser(new User());

        // Act
        availableEmployeeService.save(availableUser);

        // Assert
        verify(availableUserRepo, times(1)).save(availableUser);

    }

    @Test
    public void testGetAllAvailableUser(){
        AvailableUser availableUser = new AvailableUser();
        availableUser.setUser(new User());

        when(availableUserRepo.findAllByAvaliableIsTrue()).thenReturn(Collections.singletonList(availableUser));

        // Act
        List<AvailableUser> result = availableEmployeeService.getAllAvailableUser();

        // Assert
        assertEquals(1, result.size());
        assertEquals(availableUser.getUser(), result.get(0).getUser());

    }

    @Test
    public void testGetAvailableUserByUserId(){
        AvailableUser availableUser = new AvailableUser();
        availableUser.setUser(new User());

        when(availableUserRepo.findByUserId(1L)).thenReturn(availableUser);

        // Act
        AvailableUser result = availableEmployeeService.getAvailableUserByUserId(1L);

        // Assert
        assertEquals(availableUser.getUser(), result.getUser());
    }

}