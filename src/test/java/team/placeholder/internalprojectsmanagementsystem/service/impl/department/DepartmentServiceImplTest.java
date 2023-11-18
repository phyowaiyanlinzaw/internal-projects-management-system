package team.placeholder.internalprojectsmanagementsystem.service.impl.department;


import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import team.placeholder.internalprojectsmanagementsystem.dto.model.department.DepartmentDto;
import team.placeholder.internalprojectsmanagementsystem.model.department.Department;
import team.placeholder.internalprojectsmanagementsystem.repository.department.DepartmentRepository;

import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringBootTest
class DepartmentServiceImplTest {

    @InjectMocks
    private DepartmentServiceImpl departmentService;

    @MockBean
    private DepartmentRepository departmentRepository;

    @Test
    public void testSave() {
        DepartmentDto departmentDto = new DepartmentDto();
        departmentDto.setName("Test Department");

        Mockito.when(departmentRepository.save(Mockito.any())).thenReturn(new Department());
        DepartmentDto savedDepartment = departmentService.save(departmentDto);
        assertNotNull(savedDepartment);
    }


    @Test
    public void testGetAllDepartments() {

    }


    @Test
    public void testGetDepartmentById() {

    }

@Test
    public void testGetDepartmentByName() {

    }

    @Test
    public void testUpdateDepartment() {



    }

    @Test
    public void testUpdateNonExistentDepartment() {

    }


    @Test
    public void testDeleteDepartment() {

    }

    @Test
    public void testDeleteNonExistentDepartment() {




    }

    @Test
    public void testGetDepartmentCount() {


    }
}
