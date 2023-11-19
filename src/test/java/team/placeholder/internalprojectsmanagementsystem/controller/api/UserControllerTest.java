package team.placeholder.internalprojectsmanagementsystem.controller.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import team.placeholder.internalprojectsmanagementsystem.service.FakerService;
import team.placeholder.internalprojectsmanagementsystem.service.impl.user.UserServiceImpl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserControllerTest {



    @Mock
    private FakerService fakerService;

    @Mock
    private UserServiceImpl userService;
    @InjectMocks
    private UserController userController;

    @BeforeEach
    void setUp(){MockitoAnnotations.initMocks(this);}

    @Test
    public void testGenerateFakeUsers(){
        int count = 5;
        doNothing().when(fakerService).generateAndSaveFakeUsers(count);
        ResponseEntity<String> responseEntity = userController.generateFakeUsers(count);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Fake users generated successfully", responseEntity.getBody());
        verify(fakerService, times(1)).generateAndSaveFakeUsers(count);
    }



    @Test
    public void testGetUserInfo(){

    }

    @Test
    public void testSendEmail(){

    }

    @Test
    public void testComfirmOtp(){

    }

    @Test
    public void testResetPassword(){

    }

    @Test
    public void testRegisterEmployee(){



    }

    @Test
    public void testChangePassword(){

    }

    @Test
    public void testUpdateDepartment(){

    }

    @Test
    public void testUpdateEmployeeStatus(){

    }

    @Test
    public void testGetUserById(){

    }

    @Test
    public void testGetUserByEmail(){

    }

    @Test
    public void testGetAllUser(){

    }

    @Test
    public void testGetAllEmployeesExceptPMOAndSDQC(){

    }

    @Test
    public void testGetAllUsersByRole(){

    }

    @Test
    public void testGetAllUsersByDepartmentId(){

    }

    @Test
    public void testGetAllUserByProjectManagerId(){

    }

    @Test
    public void testCountAllByRole(){

    }

    @Test
    public void testCountAll(){

    }

    @Test
    public void testUserByDepartmentIdAndRole(){

    }

    @Test
    public void testCountAllByDepartmentId(){

    }

    @Test
    public void testChangeUsername(){

    }

}