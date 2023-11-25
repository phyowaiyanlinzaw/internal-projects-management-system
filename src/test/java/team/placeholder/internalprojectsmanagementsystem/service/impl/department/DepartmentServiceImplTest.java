package team.placeholder.internalprojectsmanagementsystem.service.impl.department;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import team.placeholder.internalprojectsmanagementsystem.dto.model.department.DepartmentDto;
import team.placeholder.internalprojectsmanagementsystem.dto.model.project.ProjectDto;
import team.placeholder.internalprojectsmanagementsystem.dto.model.user.UserDto;
import team.placeholder.internalprojectsmanagementsystem.model.department.Department;
import team.placeholder.internalprojectsmanagementsystem.repository.department.DepartmentRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DepartmentServiceImplTest {

    @Mock
    private DepartmentRepository departmentRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private DepartmentServiceImpl departmentService;

    @BeforeEach
    void setUp() {
        // Initialize mocks
        modelMapper = mock(ModelMapper.class);
        departmentRepository = mock(DepartmentRepository.class);
        departmentService = new DepartmentServiceImpl(departmentRepository, modelMapper);
    }

    // Existing tests...



    @Test
    public void testGetAllDepartmentsWhenNoDepartmentsThenReturnEmptyList() {
        // Given
        when(departmentRepository.findAll()).thenReturn(Collections.emptyList());

        // When
        var result = departmentService.getAllDepartments();

        // Then
        assertTrue(result.isEmpty());
    }



    @Test
    public void testSaveWhenDepartmentDtoPassedThenReturnDepartmentDto() {
        // Arrange
        DepartmentDto departmentDto = new DepartmentDto();
        departmentDto.setName("Test Department");

        Department department = new Department();
        department.setName("Test Department");

        when(modelMapper.map(departmentDto, Department.class)).thenReturn(department);
        when(departmentRepository.save(department)).thenReturn(department);
        when(modelMapper.map(department, DepartmentDto.class)).thenReturn(departmentDto);

        // Act
        DepartmentDto savedDepartmentDto = departmentService.save(departmentDto);

        // Assert
        assertNotNull(savedDepartmentDto);
        assertEquals(departmentDto.getName(), savedDepartmentDto.getName());

        verify(modelMapper, times(1)).map(departmentDto, Department.class);
        verify(departmentRepository, times(1)).save(department);
        verify(modelMapper, times(1)).map(department, DepartmentDto.class);
    }

    @Test
    public void testGetAllDepartmentsThenReturnListOfDepartmentDtos() {
        Department department = new Department();
        department.setName("Test Department");

        // Mock the getUsers() method to return an empty list
        DepartmentDto departmentDto = new DepartmentDto();
        departmentDto.setUsers(Collections.emptyList());

        when(departmentRepository.findAll()).thenReturn(Collections.singletonList(department));
        when(modelMapper.map(any(Department.class), eq(DepartmentDto.class))).thenReturn(departmentDto);

        List<DepartmentDto> departmentDtos = departmentService.getAllDepartments();

        assertNotNull(departmentDtos);
        assertFalse(departmentDtos.isEmpty());
        verify(departmentRepository, times(1)).findAll();
    }



    @Test
    public void testGetAllDepartments() {

        Department department1 = new Department();
        department1.setId(1L);
        department1.setName("Department 1");

        Department department2 = new Department();
        department2.setId(2L);
        department2.setName("Department 2");

        List<Department> departments = new ArrayList<>();
        departments.add(department1);
        departments.add(department2);

        // Mock the behavior of the departmentRepository
        when(departmentRepository.findAll()).thenReturn(departments);

        // Mock the behavior of modelMapper
        when(modelMapper.map(any(), eq(DepartmentDto.class))).thenAnswer(invocation -> {
            Department source = invocation.getArgument(0);
            DepartmentDto target = new DepartmentDto();
            target.setId(source.getId());
            target.setName(source.getName());
            target.setUsers(new ArrayList<>());
            return target;
        });


        List<DepartmentDto> departmentDtos = departmentService.getAllDepartments();

        // Verify the results
        assertEquals(2, departmentDtos.size());

        for (DepartmentDto departmentDto : departmentDtos) {
            assertNotNull(departmentDto.getUsers());
            assertEquals(0, departmentDto.getUsers().size());
        }

        // Verify interactions
        verify(departmentRepository, times(1)).findAll();
        verify(modelMapper, times(2)).map(any(), eq(DepartmentDto.class));
    }


    @Test
    public void testGetDepartmentByIdWhenValidIdPassedThenReturnDepartmentDto() {
        Department department = new Department();
        department.setName("Test Department");

        DepartmentDto departmentDto = new DepartmentDto();
        departmentDto.setName("Test Department");

        when(departmentRepository.findById(anyLong())).thenReturn(department);
        when(modelMapper.map(department, DepartmentDto.class)).thenReturn(departmentDto);

        DepartmentDto result = departmentService.getDepartmentById(1L);

        assertNotNull(result);
        assertEquals(departmentDto.getName(), result.getName());
        verify(departmentRepository, times(1)).findById(anyLong());
    }

    @Test
    public void testGetDepartmentByIdWhenInvalidIdPassedThenReturnNull() {
        when(departmentRepository.findById(anyLong())).thenReturn(null);

        DepartmentDto result = departmentService.getDepartmentById(1L);

        assertNull(result);
        verify(departmentRepository, times(1)).findById(anyLong());
    }

    @Test
    public void testGetDepartmentByNameWhenValidNamePassedThenReturnDepartmentDto() {
        Department department = new Department();
        department.setName("Test Department");
        when(departmentRepository.findByName(anyString())).thenReturn(department);
        when(modelMapper.map(any(Department.class), eq(DepartmentDto.class))).thenReturn(new DepartmentDto());
        DepartmentDto departmentDto = departmentService.getDepartmentByName("Test Department");
        assertNotNull(departmentDto);
        verify(departmentRepository, times(1)).findByName(anyString());
    }

    @Test
    public void getDepartmentByName() {
        // Arrange
        String validName = "Test Department";
        Department department = new Department();
        department.setName(validName);
        DepartmentDto departmentDto = new DepartmentDto();
        departmentDto.setName(validName);

        when(departmentRepository.findByName(validName)).thenReturn(department);
        when(modelMapper.map(department, DepartmentDto.class)).thenReturn(departmentDto);

        // Act
        DepartmentDto result = departmentService.getDepartmentByName(validName);

        // Assert
        assertNotNull(result);
        assertEquals(validName, result.getName());
        verify(departmentRepository, times(1)).findByName(validName);
    }

    @Test
    public void testGetDepartmentByNameWhenInvalidNamePassedThenReturnNull() {
        // Arrange
        String invalidName = "Invalid Department";

        when(departmentRepository.findByName(invalidName)).thenReturn(null);

        // Act
        DepartmentDto result = departmentService.getDepartmentByName(invalidName);

        // Assert
        assertNull(result);
        verify(departmentRepository, times(1)).findByName(invalidName);
    }

    @Test
    public void testUpdateDepartmentWhenValidDepartmentDtoPassedThenReturnUpdatedDepartmentDto() {
        DepartmentDto departmentDto = new DepartmentDto();
        departmentDto.setId(1L);
        departmentDto.setName("Updated Department");
        Department department = new Department();
        department.setName("Updated Department");
        when(departmentRepository.findById(anyLong())).thenReturn(department);
        when(departmentRepository.save(any(Department.class))).thenReturn(department);
        when(modelMapper.map(any(Department.class), eq(DepartmentDto.class))).thenReturn(departmentDto);
        DepartmentDto updatedDepartmentDto = departmentService.updateDepartment(departmentDto);
        assertNotNull(updatedDepartmentDto);
        assertEquals(departmentDto.getName(), updatedDepartmentDto.getName());
        verify(departmentRepository, times(1)).findById(anyLong());
        verify(departmentRepository, times(1)).save(any(Department.class));
    }

    @Test
    public void testUpdateDepartmentWhenInvalidDepartmentDtoPassedThenReturnNull() {
        DepartmentDto departmentDto = new DepartmentDto();
        departmentDto.setId(1L);
        departmentDto.setName("Invalid Department");

        when(departmentRepository.findById(anyLong())).thenReturn(null);

        DepartmentDto updatedDepartmentDto = departmentService.updateDepartment(departmentDto);

        assertNull(updatedDepartmentDto);
        verify(departmentRepository, times(1)).findById(anyLong());
    }

    @Test
    public void testDeleteDepartmentWhenValidIdPassedThenDeleteDepartment() {
        Department department = new Department();
        department.setName("Test Department");
        when(departmentRepository.findById(anyLong())).thenReturn(department);
        doNothing().when(departmentRepository).delete(any(Department.class));
        departmentService.deleteDepartment(1L);
        verify(departmentRepository, times(1)).findById(anyLong());
        verify(departmentRepository, times(1)).delete(any(Department.class));
    }

    @Test
    public void testGetDepartmentCountThenReturnCount() {
        when(departmentRepository.count()).thenReturn(1L);
        long count = departmentService.getDeprtmentCount();
        assertEquals(1L, count);
        verify(departmentRepository, times(1)).count();
    }

    @Test
    public void testDeleteDepartment() {
        // Given
        long departmentId = 1L;
        Department department = new Department();
        when(departmentRepository.findById(departmentId)).thenReturn(department);

        // When
        departmentService.deleteDepartment(departmentId);

        // Then
        verify(departmentRepository, times(1)).findById(departmentId);
        verify(departmentRepository, times(1)).delete(department);
    }

    @Test
    public void testDeleteDepartmentNotFound() {
        // Given
        long nonExistingDepartmentId = 2L;
        when(departmentRepository.findById(nonExistingDepartmentId)).thenReturn(null);

        // When
        departmentService.deleteDepartment(nonExistingDepartmentId);

        // Then
        verify(departmentRepository, times(1)).findById(nonExistingDepartmentId);
        verify(departmentRepository, never()).delete(any());
    }
}