package team.placeholder.internalprojectsmanagementsystem.repository.department;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import team.placeholder.internalprojectsmanagementsystem.model.department.Department;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DepartmentRepositoryTest {

    @Mock
    private DepartmentRepository departmentRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindById() {
        // Arrange
        long id = 1L;
        Department department = new Department();
        department.setId(id);

        // Mock the behavior of departmentRepository.findById
        when(departmentRepository.findById(id)).thenReturn(department);

        // Act
        Department result = departmentRepository.findById(id);

        // Assert
        assertEquals(id, result.getId());
    }

    @Test
    public void testFindByName() {
        // Arrange
        String name = "TestDepartment";
        Department department = new Department();
        department.setName(name);

        // Mock the behavior of departmentRepository.findByName
        when(departmentRepository.findByName(name)).thenReturn(department);

        // Act
        Department result = departmentRepository.findByName(name);

        // Assert
        assertEquals(name, result.getName());
    }

    @Test
    public void testSelectAllDepartment() {
        // Arrange
        List<Department> departments = new ArrayList<>();
        departments.add(new Department());
        departments.add(new Department());

        // Mock the behavior of departmentRepository.selectAllDepartment
        when(departmentRepository.selectAllDepartment()).thenReturn(departments);

        // Act
        List<Department> result = departmentRepository.selectAllDepartment();

        // Assert
        assertEquals(departments.size(), result.size());
    }


}
