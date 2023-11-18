package team.placeholder.internalprojectsmanagementsystem.controller.api;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import team.placeholder.internalprojectsmanagementsystem.dto.model.department.DepartmentDto;
import team.placeholder.internalprojectsmanagementsystem.service.FakerService;
import team.placeholder.internalprojectsmanagementsystem.service.impl.department.DepartmentServiceImpl;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


class DepartmentControllerTest {

@Mock
private DepartmentServiceImpl departmentService;

@Mock
private FakerService fakerService;

@InjectMocks
private DepartmentController departmentController;


@BeforeEach
void setUp(){
    MockitoAnnotations.initMocks(this);
}


    @Test
    public void testGenerateFakeDepartments() throws Exception{
    int count = 5;
    doNothing().when(fakerService).generateAndSaveFakeDepartments(count);
    ResponseEntity<String> responseEntity= departmentController.generateFakeDepartments(count);
    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    assertEquals("Fake departments generated successfully", responseEntity.getBody());

    verify(fakerService, times(1)).generateAndSaveFakeDepartments(count);

    }
    @Test
    public void testGetAllDepartments() throws Exception {
        List<DepartmentDto> mockDepartment = Arrays.asList(new DepartmentDto(), new DepartmentDto());
        when(departmentService.getAllDepartments()).thenReturn(mockDepartment);
        ResponseEntity<List<DepartmentDto>> response = departmentController.getAllDepartments();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockDepartment, response.getBody());
        verify(departmentService, times(1)).getAllDepartments();
        ResponseEntity <List<DepartmentDto>> expectedResponse = ResponseEntity.ok(mockDepartment);
        assertEquals(expectedResponse, response);
    }

    @Test
    public void testById() throws Exception{

    long id =1L;
    DepartmentDto departmentDto = new DepartmentDto();
    when(departmentService.getDepartmentById(id)).thenReturn(departmentDto);
    ResponseEntity<DepartmentDto> response = departmentController.getDepartmentById(id);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(departmentDto, response.getBody());
    verify(departmentService, times(1)).getDepartmentById(id);

    }

    @Test
    public void testByIdNotFound(){
    long notFoundId= 999L;
    when(departmentService.getDepartmentById(notFoundId)).thenReturn(null);
    ResponseEntity<DepartmentDto> response = departmentController.getDepartmentById(notFoundId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertTrue(response.getBody() == null || response.getBody().equals(""));
        assertTrue(response.getBody() == null || response.getBody().equals(""));
    }


    @Test
    public void testByName() throws Exception{
    String name="Test Department";
    DepartmentDto departmentDto= new DepartmentDto();
    when(departmentService.getDepartmentByName(name)).thenReturn(departmentDto);
    ResponseEntity<DepartmentDto> response = departmentController.getDepartmentByName(name);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(departmentDto, response.getBody());
    verify(departmentService, times(1)).getDepartmentByName(name);


    }

    @Test
    public void testByNameNotFound(){
        String notFoundName= "Not Found Deparment";
        when(departmentService.getDepartmentByName(notFoundName)).thenReturn(null);
        ResponseEntity<DepartmentDto> response = departmentController.getDepartmentByName(notFoundName);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertTrue(response.getBody() == null || response.getBody().equals(""));
        assertTrue(response.getBody() == null || response.getBody().equals(""));
    }

    @Test
    public void testUpdate() throws Exception{
        long id =1L;
        DepartmentDto departmentDto = new DepartmentDto();
        departmentDto.setId(id);
        DepartmentDto updateDepartment = new DepartmentDto();
        when(departmentService.updateDepartment(departmentDto)).thenReturn(updateDepartment);
        ResponseEntity<DepartmentDto> response = departmentController.updateDepartment(id, departmentDto);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updateDepartment, response.getBody());
        verify(departmentService, times(1)).updateDepartment(departmentDto);
    }
    @Test
    void testUpdateDepartmentBadRequest() {
        // Mocking the path variable
        long Id = 1L;

        // Mocking the request body
        DepartmentDto departmentDto = new DepartmentDto();
        departmentDto.setId(Id);

        // Mocking the service response for a failed update
        when(departmentService.updateDepartment(departmentDto)).thenReturn(null);

        ResponseEntity<DepartmentDto> response = departmentController.updateDepartment(Id, departmentDto);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertTrue(response.getBody() == null || response.getBody().equals(""));
        assertTrue(response.getBody() == null || response.getBody().equals(""));

        // Verifying service method invocation
        verify(departmentService, times(1)).updateDepartment(departmentDto);
    }

    @Test
    public void testCount() throws Exception{
        long count = 10L;
        when(departmentService.getDeprtmentCount()).thenReturn(count);

        long response = departmentController.dpCount();

        assertEquals(count, response);

        verify(departmentService, times(1)).getDeprtmentCount();
    }



}