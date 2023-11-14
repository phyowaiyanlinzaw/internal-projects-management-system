//package team.placeholder.internalprojectsmanagementsystem.service.impl.department;
//
//import org.junit.jupiter.api.Test;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.springframework.boot.test.context.SpringBootTest;
//import team.placeholder.internalprojectsmanagementsystem.dto.model.department.DepartmentDto;
//import team.placeholder.internalprojectsmanagementsystem.model.department.Department;
//import team.placeholder.internalprojectsmanagementsystem.repository.department.DepartmentRepository;
//
//import java.util.Arrays;
//import java.util.List;
//import java.util.Optional;
//import java.util.logging.Logger;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//@SpringBootTest
//class DepartmentServiceImplTest {
//    @Mock
//    DepartmentRepository departmentRepository;
//    @Mock
//    private Logger logger;
//
//    @Test
//    public void toSaveDepartment(){
//        DepartmentDto departmentDto = new DepartmentDto();
//        departmentDto.setName("Department Testing");
//
//        Department department = new Department();
//        department.setId(1L);
//        department.setName("Department Testing");
//
//        when(departmentRepository.save(Mockito.any(Department.class))).thenReturn(department);
//        DepartmentServiceImpl departmentService = new DepartmentServiceImpl(departmentRepository);
//
//
//        DepartmentDto savedDepartmentDto = departmentService.save(departmentDto);
//
//        assertNotNull(savedDepartmentDto);
//        assertEquals(departmentDto.getName(), savedDepartmentDto.getName());
//        assertEquals(1L, savedDepartmentDto.getId());
//    }
//
//    @Test
//    public void testGetAllDepartments() {
//
//        Department department1 = new Department();
//        department1.setId(1L);
//        department1.setName("Department 1");
//
//        Department department2 = new Department();
//        department2.setId(2L);
//        department2.setName("Department 2");
//
//
//        when(departmentRepository.findAll()).thenReturn(Arrays.asList(department1, department2));
//
//
//        DepartmentServiceImpl departmentService = new DepartmentServiceImpl(departmentRepository);
//
//
//        List<DepartmentDto> departmentDtoList = departmentService.getAllDepartments();
//
//
//        assertEquals(2, departmentDtoList.size());
//        assertEquals("Department 1", departmentDtoList.get(0).getName());
//        assertEquals(1L, departmentDtoList.get(0).getId());
//        assertEquals("Department 2", departmentDtoList.get(1).getName());
//        assertEquals(2L, departmentDtoList.get(1).getId());
//    }
//
//
//    @Test
//    public void testGetDepartmentById() {
//
//        Department department = new Department();
//        department.setId(1L);
//        department.setName("Test Department");
//
//        when(departmentRepository.findById(1L)).thenReturn(department);
//        when(departmentRepository.findById(2L)).thenReturn(null); // Simulating a department not found case
//
//
//        DepartmentServiceImpl departmentService = new DepartmentServiceImpl(departmentRepository);
//
//
//        DepartmentDto departmentDtoFound = departmentService.getDepartmentById(1L);
//        DepartmentDto departmentDtoNotFound = departmentService.getDepartmentById(2L);
//
//        assertNotNull(departmentDtoFound);
//        assertEquals(1L, departmentDtoFound.getId());
//        assertEquals("Test Department", departmentDtoFound.getName());
//
//        assertNull(departmentDtoNotFound);
//    }
//
//@Test
//    public void testGetDepartmentByName() {
//        // Create a mock Department
//    Department department = new Department();
//    department.setId(1L);
//    department.setName("Test Department");
//
//    // Mock the behavior of the repository
//    when(departmentRepository.findByName("Test Department")).thenReturn(department);
//    when(departmentRepository.findByName("Non-existent Department")).thenReturn(null); // Simulating a department not found case
//
//    // Create an instance of DepartmentServiceImpl
//    DepartmentServiceImpl departmentService = new DepartmentServiceImpl(departmentRepository);
//
//    // Call the getDepartmentByName method
//    DepartmentDto departmentDtoFound = departmentService.getDepartmentByName("Test Department");
//    DepartmentDto departmentDtoNotFound = departmentService.getDepartmentByName("Non-existent Department");
//
//    // Verify that the correct mapping and handling of null case were performed
//    assertNotNull(departmentDtoFound);
//    assertEquals(1L, departmentDtoFound.getId());
//    assertEquals("Test Department", departmentDtoFound.getName());
//
//    assertNull(departmentDtoNotFound);
//    }
//
//    @Test
//    public void testUpdateDepartment() {
//
//        DepartmentDto departmentDto = new DepartmentDto();
//        departmentDto.setId(1L);
//        departmentDto.setName("Updated Department Name");
//
//
//        Department department = new Department();
//        department.setId(1L);
//        department.setName("Original Department Name");
//
//
//        when(departmentRepository.findById(1L)).thenReturn(department);
//
//
//        DepartmentServiceImpl departmentService = new DepartmentServiceImpl(departmentRepository);
//
//
//        DepartmentDto updatedDepartmentDto = departmentService.updateDepartment(departmentDto);
//
//
//        assertNotNull(updatedDepartmentDto);
//        assertEquals(departmentDto.getId(), updatedDepartmentDto.getId());
//        assertEquals(departmentDto.getName(), updatedDepartmentDto.getName());
//
//    }
//
//    @Test
//    public void testUpdateNonExistentDepartment() {
//        DepartmentDto departmentDto = new DepartmentDto();
//        departmentDto.setId(2L);
//        departmentDto.setName("Non-existent Department");
//
//        when(departmentRepository.findById(2L)).thenReturn(null);
//
//        DepartmentServiceImpl departmentService = new DepartmentServiceImpl(departmentRepository);
//
//        DepartmentDto updatedDepartmentDto = departmentService.updateDepartment(departmentDto);
//
//        assertNull(updatedDepartmentDto);
//    }
//
//
//    @Test
//    public void testDeleteDepartment() {
//        // Create a mock Department
//        Department department = new Department();
//        department.setId(1L);
//        department.setName("Test Department");
//
//        // Mock the behavior of the repository
//        when(departmentRepository.findById(1L)).thenReturn(department);
//
//        // Create an instance of DepartmentServiceImpl
//        DepartmentServiceImpl departmentService = new DepartmentServiceImpl(departmentRepository);
//
//        // Call the deleteDepartment method
//        departmentService.deleteDepartment(1L);
//
//        // Verify that delete was called on the repository with the correct department
//        verify(departmentRepository, times(1)).delete(department);
//    }
//
//    @Test
//    public void testDeleteNonExistentDepartment() {
//        // Mock the behavior of the repository to return null (non-existent department)
//        when(departmentRepository.findById(2L)).thenReturn(null);
//
//        // Create an instance of DepartmentServiceImpl
//        DepartmentServiceImpl departmentService = new DepartmentServiceImpl(departmentRepository);
//
//        // Call the deleteDepartment method
//        departmentService.deleteDepartment(2L);
//
//
//    }
//
//    @Test
//    public void testGetDepartmentCount() {
//        // Mock the behavior of the repository
//        when(departmentRepository.count()).thenReturn(5L); // Assuming there are 5 departments
//
//        // Create an instance of DepartmentServiceImpl
//        DepartmentServiceImpl departmentService = new DepartmentServiceImpl(departmentRepository);
//
//        // Call the getDepartmentCount method
//        long departmentCount = departmentService.getDeprtmentCount();
//
//        // Verify that the correct count is returned
//        assertEquals(5L, departmentCount);
//    }
//}
