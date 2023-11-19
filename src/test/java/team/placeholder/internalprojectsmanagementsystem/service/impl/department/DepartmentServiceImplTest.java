package team.placeholder.internalprojectsmanagementsystem.service.impl.department;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    @InjectMocks
    private DepartmentServiceImpl departmentService;

    @Mock
    private DepartmentRepository departmentRepository;

    @Mock
    private ModelMapper modelMapper;

    @BeforeEach
    void setUp() {
        // Initialize mocks
        modelMapper = mock(ModelMapper.class);
        departmentRepository = mock(DepartmentRepository.class);
        departmentService = new DepartmentServiceImpl(departmentRepository, modelMapper);
    }

// ...

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

//    @Test
//    public void testGetAllDepartmentWitPage() {
//        // Create a list of Department objects
//        Department department1 = new Department();
//        department1.setId(1L);
//        department1.setName("Department 1");
//        department1.setUsers(Collections.singletonList(new UserDto(department1))); // Fix here
//
//        Department department2 = new Department();
//        department2.setId(2L);
//        department2.setName("Department 2");
//        department2.setUsers(Collections.singletonList(new UserDto())); // Fix here
//
//        // Create a list of DepartmentDto objects
//        DepartmentDto departmentDto1 = new DepartmentDto();
//        departmentDto1.setId(1L);
//        departmentDto1.setName("Department 1");
//
//        DepartmentDto departmentDto2 = new DepartmentDto();
//        departmentDto2.setId(2L);
//        departmentDto2.setName("Department 2");
//
//        when(departmentRepository.findAll(any(Pageable.class)))
//                .thenReturn(new PageImpl<>(Arrays.asList(department1, department2)));
//
//        when(modelMapper.map(department1, DepartmentDto.class)).thenReturn(departmentDto1);
//        when(modelMapper.map(department2, DepartmentDto.class)).thenReturn(departmentDto2);
//
//        // Call the method to be tested
//        Page<DepartmentDto> resultPage = departmentService.getAllDepartmentWitPage(1);
//
//        // Verify the behavior
//        verify(departmentRepository, times(1)).findAll(any(Pageable.class));
//        verify(modelMapper, times(1)).map(department1, DepartmentDto.class);
//        verify(modelMapper, times(1)).map(department2, DepartmentDto.class);
//
//        // Check the result
//        assertEquals(2, resultPage.getContent().size());
//        assertEquals(departmentDto1, resultPage.getContent().get(0));
//        assertEquals(departmentDto2, resultPage.getContent().get(1));
//    }

}