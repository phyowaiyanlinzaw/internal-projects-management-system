package team.placeholder.internalprojectsmanagementsystem.service.impl.department;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import team.placeholder.internalprojectsmanagementsystem.dto.model.department.DepartmentDto;
import team.placeholder.internalprojectsmanagementsystem.dto.model.project.ProjectDto;
import team.placeholder.internalprojectsmanagementsystem.dto.model.user.UserDto;
import team.placeholder.internalprojectsmanagementsystem.model.department.Department;
import team.placeholder.internalprojectsmanagementsystem.repository.department.DepartmentRepository;

import java.util.*;

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
        departmentService = new DepartmentServiceImpl(departmentRepository,modelMapper);
    }

    @Test
    public void testGetAllDepartments() {
        // Given
        List<Department> departments = new ArrayList<>(); // Add sample departments
        when(departmentRepository.findAll()).thenReturn(departments);


        when(modelMapper.map(any(), eq(DepartmentDto.class))).thenAnswer(invocation -> {
            Object source = invocation.getArgument(0);
            return source; // Simply return the source object for now
        });

        // When
        List<DepartmentDto> result = departmentService.getAllDepartments();



        // Then
        verify(departmentRepository, times(1)).findAll();
        verify(modelMapper, atLeastOnce()).map(any(), any());

        for (DepartmentDto departmentDto : result) {
            for (UserDto user : departmentDto.getUsers()) {
                assertTrue(user.getProjectsByUsers().isEmpty());
                assertTrue(user.getProjectsByProjectManager().isEmpty());
            }
        }
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
    public void testClearProjectsInUserDto() {
        // Create a mock UserDto
        UserDto userDto = new UserDto();
        userDto.setId(1L);
        userDto.setName("Test User");

        // Create mock Projects
        ProjectDto projectByUsers = new ProjectDto();
        projectByUsers.setId(101L);
        projectByUsers.setName("Project by Users");

        ProjectDto projectByProjectManager = new ProjectDto();
        projectByProjectManager.setId(102L);
        projectByProjectManager.setName("Project by Project Manager");

        // Set Projects in UserDto
        userDto.setProjectsByUsers(Set.of(projectByUsers));
        userDto.setProjectsByProjectManager(List.of(projectByProjectManager));



        // Create an instance of DepartmentServiceImpl
        DepartmentServiceImpl departmentService = new DepartmentServiceImpl(departmentRepository, modelMapper);

        // Call the method you want to test
        List<DepartmentDto> result = departmentService.getAllDepartments();

        // Verify that the Projects in UserDto are cleared
        for (DepartmentDto departmentDto : result) {
            for (UserDto resultUser : departmentDto.getUsers()) {
                assertTrue(resultUser.getProjectsByUsers().isEmpty());
                assertTrue(resultUser.getProjectsByProjectManager().isEmpty());
            }
        }
    }

    @Test
    public void testGetAllDepartmentsWhenNoDepartmentsThenReturnEmptyList() {
        when(departmentRepository.findAll()).thenReturn(Collections.emptyList());
        List<DepartmentDto> departmentDtos = departmentService.getAllDepartments();
        assertNotNull(departmentDtos);
        assertTrue(departmentDtos.isEmpty());
        verify(departmentRepository, times(1)).findAll();
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