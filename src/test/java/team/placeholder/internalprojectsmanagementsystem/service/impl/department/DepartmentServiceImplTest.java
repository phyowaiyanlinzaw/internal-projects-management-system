//package team.placeholder.internalprojectsmanagementsystem.service.impl.department;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.MockitoAnnotations;
//import team.placeholder.internalprojectsmanagementsystem.dto.mapper.department.DepartmentMapper;
//import team.placeholder.internalprojectsmanagementsystem.dto.model.department.DepartmentDto;
//import team.placeholder.internalprojectsmanagementsystem.model.department.Department;
//import team.placeholder.internalprojectsmanagementsystem.repository.department.DepartmentRepository;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//class DepartmentServiceImplTest {
//
//    @Mock
//    private DepartmentRepository departmentRepository;
//
//    @InjectMocks
//    private DepartmentServiceImpl departmentService;
//
//    @BeforeEach
//    public void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    public void testSaveDepartment() {
//        // Arrange
//        DepartmentDto departmentDto = new DepartmentDto();
//        departmentDto.setName("IT");
//
//        Department department = new Department();
//        department.setName(departmentDto.getName());
//        department.setId(1L); // Set a valid ID
//
//        when(departmentRepository.save(any(Department.class))).thenReturn(department);
//
//        // Act
//        DepartmentDto savedDto = departmentService.save(departmentDto);
//
//        System.out.println("Saved DTO: " + savedDto);
//
//        // Assert
//        assertNotNull(savedDto);
//        assertEquals(departmentDto.getName(), savedDto.getName());
//        assertEquals(department.getId(), savedDto.getId()); // Check that the ID is transferred to the DTO
//
//        verify(departmentRepository, times(1)).save(any(Department.class));
//    }
//
//
//
//    @Test
//    public void testGetAllDepartments() {
//        // Arrange
//        List<Department> mockDepartments = new ArrayList<>();
//
//        Department itDepartment = new Department();
//        itDepartment.setId(1L);
//        itDepartment.setName("IT");
//
//        Department hrDepartment = new Department();
//        hrDepartment.setId(2L);
//        hrDepartment.setName("HR");
//
//        mockDepartments.add(itDepartment);
//        mockDepartments.add(hrDepartment);
//
//        when(departmentRepository.findAll()).thenReturn(mockDepartments);
//
//        // Act
//        List<DepartmentDto> result = departmentService.getAllDepartments();
//
//        // Assert
//        assertEquals(2, result.size());
//        assertEquals("IT", result.get(0).getName());
//        assertEquals("HR", result.get(1).getName());
//    }
//
//    @Test
//    public void testGetDepartmentById() {
//        // Arrange
//        long validDepartmentId = 1L;
//        long invalidDepartmentId = 2L;
//
//        Department itDepartment = new Department();
//        itDepartment.setId(validDepartmentId);
//        itDepartment.setName("IT");
//
//        when(departmentRepository.findById(validDepartmentId)).thenReturn(itDepartment);
//        when(departmentRepository.findById(invalidDepartmentId)).thenReturn(null);
//
//        // Act
//        DepartmentDto resultValid = departmentService.getDepartmentById(validDepartmentId);
//        DepartmentDto resultInvalid = departmentService.getDepartmentById(invalidDepartmentId);
//
//        // Assert
//        assertNotNull(resultValid);
//        assertEquals("IT", resultValid.getName());
//        assertEquals(validDepartmentId, resultValid.getId());
//
//        assertNull(resultInvalid);
//    }
//
//
//
//    @Test
//    public void testGetDepartmentByName() {
//        // Arrange
//        String departmentName = "IT";
//        String invalidDepartmentName = "HR";
//
//        Department department = new Department();
//        department.setId(1L);
//        department.setName(departmentName);
//
//        when(departmentRepository.findByName(departmentName)).thenReturn(department);
//        when(departmentRepository.findByName(invalidDepartmentName)).thenReturn(null);
//
//        // Act
//        DepartmentDto departmentDto = departmentService.getDepartmentByName(departmentName);
//        DepartmentDto invalidDepartmentDto = departmentService.getDepartmentByName(invalidDepartmentName);
//
//        // Assert
//        assertNotNull(departmentDto);
//
//        assertEquals(departmentName, departmentDto.getName());
//        assertNull(invalidDepartmentDto);
//
//    }
//
//    @Test
//    public void testUpdateExistingDepartment() {
//        // Arrange
//        long existingDepartmentId = 1L;
//
//        DepartmentDto departmentDto = new DepartmentDto();
//        departmentDto.setId(existingDepartmentId);
//        departmentDto.setName("Updated Department Name");
//
//        Department existingDepartment = new Department();
//        existingDepartment.setId(existingDepartmentId);
//        existingDepartment.setName("Original Department Name");
//
//        DepartmentRepository departmentRepository = Mockito.mock(DepartmentRepository.class);
//
//        DepartmentServiceImpl departmentService = new DepartmentServiceImpl(departmentRepository);
//
//        Mockito.when(departmentRepository.findById(existingDepartmentId)).thenReturn(existingDepartment);
//        Mockito.when(departmentRepository.save(existingDepartment)).thenReturn(existingDepartment);
//
//        // Act
//        DepartmentDto result = departmentService.updateDepartment(departmentDto);
//
//        // Assert
//        assertEquals(departmentDto.getName(), result.getName());
//    }
//
//    @Test
//    public void testUpdateNonExistingDepartment() {
//        // Arrange
//        long nonExistingDepartmentId = 2L;
//
//        DepartmentDto departmentDto = new DepartmentDto();
//        departmentDto.setId(nonExistingDepartmentId);
//        departmentDto.setName("Updated Department Name");
//
//        DepartmentRepository departmentRepository = Mockito.mock(DepartmentRepository.class);
//
//        DepartmentServiceImpl departmentService = new DepartmentServiceImpl(departmentRepository);
//
//        Mockito.when(departmentRepository.findById(nonExistingDepartmentId)).thenReturn(null);
//
//        // Act
//        DepartmentDto result = departmentService.updateDepartment(departmentDto);
//
//        // Assert
//        assertNull(result);
//    }
//
//    @Test
//    public void testDeleteExistingDepartment() {
//        // Arrange
//        long existingDepartmentId = 1L;
//
//        Department existingDepartment = new Department();
//        existingDepartment.setId(existingDepartmentId);
//        existingDepartment.setName("Existing Department");
//
//        Mockito.when(departmentRepository.findById(existingDepartmentId)).thenReturn(existingDepartment);
//
//        // Act
//        departmentService.deleteDepartment(existingDepartmentId);
//
//    }
//
//
//    @Test
//    public void testDeleteNonExistingDepartment() {
//        // Arrange
//        long nonExistingDepartmentId = 2L;
//
//        DepartmentRepository departmentRepository = Mockito.mock(DepartmentRepository.class);
//
//        DepartmentServiceImpl departmentService = new DepartmentServiceImpl(departmentRepository);
//
//        Mockito.when(departmentRepository.findById(nonExistingDepartmentId)).thenReturn(null);
//
//        // Act
//        departmentService.deleteDepartment(nonExistingDepartmentId);
//
//
//    }
//
//}
