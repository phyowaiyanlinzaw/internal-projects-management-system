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

        long id = 1L;
        Department department = new Department();
        department.setId(id);

        when(departmentRepository.findById(id)).thenReturn(department);

        Department result = departmentRepository.findById(id);


        assertEquals(id, result.getId());
    }

    @Test
    public void testFindByName() {

        String name = "TestDepartment";
        Department department = new Department();
        department.setName(name);

        when(departmentRepository.findByName(name)).thenReturn(department);

        Department result = departmentRepository.findByName(name);

        assertEquals(name, result.getName());
    }


    @Test
    public void testCount(){
        long expectedCount = 5L;

        when(departmentRepository.count()).thenReturn(expectedCount);

        long result = departmentRepository.count();

        assertEquals(expectedCount, result);
    }

    @Test
    public void testFindAll(){
        List<Department> expectedDepartments = new ArrayList<>();
        expectedDepartments.add(new Department());

        when(departmentRepository.findAll()).thenReturn(expectedDepartments);

        List<Department> result = departmentRepository.findAll();

        assertEquals(expectedDepartments.size(), result.size());
        for (int i = 0; i < expectedDepartments.size(); i++) {
            assertEquals(expectedDepartments.get(i), result.get(i));
        }
    }




}
