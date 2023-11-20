package team.placeholder.internalprojectsmanagementsystem.service.department;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import team.placeholder.internalprojectsmanagementsystem.dto.model.department.DepartmentDto;
import team.placeholder.internalprojectsmanagementsystem.model.department.Department;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DepartmentServiceTest {
    @Mock
    private DepartmentService departmentService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSaveDepartment() {
        DepartmentDto departmentDto = new DepartmentDto();

        Mockito.when(departmentService.save(departmentDto)).thenReturn(departmentDto);
        DepartmentDto savedDepartment = departmentService.save(departmentDto);
        assertEquals(departmentDto, savedDepartment);
    }


    @Test
    public void testGetAllDepartments() {
        List<DepartmentDto> departmentDtos = new ArrayList<>();

        Mockito.when(departmentService.getAllDepartments()).thenReturn(departmentDtos);

        List<DepartmentDto> allDepartments = departmentService.getAllDepartments();
        assertEquals(departmentDtos, allDepartments);

    }

    @Test
    public void testGetDepartmentById() {
        long departmentId = 1;
        DepartmentDto departmentDto = new DepartmentDto();

        Mockito.when(departmentService.getDepartmentById(departmentId)).thenReturn(departmentDto);

        DepartmentDto departmentById = departmentService.getDepartmentById(1L);
        assertEquals(departmentDto, departmentById);
    }


    @Test
    public void testGetDepartmentByName() {

    String departmentName = "department1";
    DepartmentDto departmentDto = new DepartmentDto();

    Mockito.when(departmentService.getDepartmentByName(departmentName)).thenReturn(departmentDto);

    DepartmentDto retrievedDepartment = departmentService.getDepartmentByName(departmentName);

    assertEquals(departmentDto, retrievedDepartment);
    }

    @Test
    public void testUpdateDepartment() {
        DepartmentDto departmentDto = new DepartmentDto();

        Mockito.when(departmentService.updateDepartment(departmentDto)).thenReturn(departmentDto);

        DepartmentDto updatedDepartment = departmentService.updateDepartment(departmentDto);

        assertEquals(departmentDto, updatedDepartment);
    }

    @Test
    public void testDeleteDepartment() {
        long departmentId = 1;

        departmentService.deleteDepartment(departmentId);

        Mockito.verify(departmentService).deleteDepartment(departmentId);

    }

    @Test
    public void testLongGetDepartmentCount() {
        long departmentCount = 1;

        Mockito.when(departmentService.getDeprtmentCount()).thenReturn(departmentCount);

        long retrievedDepartmentCount = departmentService.getDeprtmentCount();

        assertEquals(departmentCount, retrievedDepartmentCount);
    }




}