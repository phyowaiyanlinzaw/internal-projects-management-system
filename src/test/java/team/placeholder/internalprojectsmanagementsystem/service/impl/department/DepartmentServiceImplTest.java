package team.placeholder.internalprojectsmanagementsystem.service.impl.department;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import team.placeholder.internalprojectsmanagementsystem.model.department.Department;
import team.placeholder.internalprojectsmanagementsystem.repository.department.DepartmentRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DepartmentServiceImplTest {

    @Mock
    private DepartmentRepository departmentRepository;

    @InjectMocks
    private DepartmentServiceImpl departmentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void testSaveDepartment() {
        Department department = new Department();
        department.setName("IT");
        departmentRepository.save(department);
        verify(departmentRepository, times(1)).save(department);
    }

    @Test
    public void testGetAllDepartments() {
        List<Department> list = new ArrayList<>();
        Department department1 = new Department();
        department1.setName("IT");

        Department department2 = new Department();
        department2.setName("HR");

        list.add(department1);
        list.add(department2);

        when(departmentRepository.findAll()).thenReturn(list);
        List<Department> departments = departmentRepository.findAll();
        assertEquals(2, departments.size());
        verify(departmentRepository, times(1)).findAll();
    }

    @Test
    public void testGetDepartmentById() {
        Department department = new Department();
        department.setName("IT");
        department.setId(1L);
        when(departmentRepository.findById(1L)).thenReturn(department);
        Department department1 = departmentRepository.findById(1L);
        assertEquals("IT", department1.getName());
        verify(departmentRepository, times(1)).findById(1L);
    }

    @Test
    public void testGetDepartmentByName() {
        Department department = new Department();
        department.setName("IT");
        department.setId(1L);
        when(departmentRepository.findByName("IT")).thenReturn(department);
        Department department1 = departmentRepository.findByName("IT");
        assertEquals("IT", department1.getName());
        verify(departmentRepository, times(1)).findByName("IT");
    }

    @Test
    public void testUpdateDepartment() {
        Department department = new Department();
        department.setName("IT");
        department.setId(1L);
        when(departmentRepository.findById(1L)).thenReturn(department);
        Department department1 = departmentRepository.findById(1L);
        department1.setName("HR");
        departmentRepository.save(department1);
        assertEquals("HR", department1.getName());
        verify(departmentRepository, times(1)).save(department1);
    }

    @Test
    public void testDeleteDepartment() {
        Department department = new Department();
        department.setName("IT");
        department.setId(1L);
        departmentRepository.delete(department);
        verify(departmentRepository, times(1)).delete(department);
    }
}