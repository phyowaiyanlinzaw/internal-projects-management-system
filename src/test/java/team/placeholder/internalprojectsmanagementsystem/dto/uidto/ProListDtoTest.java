package team.placeholder.internalprojectsmanagementsystem.dto.uidto;

import org.junit.jupiter.api.Test;
import team.placeholder.internalprojectsmanagementsystem.dto.model.project.TasksDto;
import team.placeholder.internalprojectsmanagementsystem.dto.model.user.UserDto;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ProListDtoTest {

    @Test
    public void testDefaultConstructor() {
        // Create a ProListDto using the default constructor
        ProListDto proListDto = new ProListDto();

        // Verify that the default values are set
        assertEquals(0L, proListDto.getId());
        assertNull(proListDto.getProjectName());
        assertEquals(0L, proListDto.getPercentage());
        assertEquals(0L, proListDto.getStartDate());
        assertEquals(0L, proListDto.getEndDate());
        assertNull(proListDto.getUser());
        assertNull(proListDto.getTasks());
        assertFalse(proListDto.isClosed());
    }

    @Test
    public void testParameterizedConstructor() {
        // Create a ProListDto using the parameterized constructor
        ProListDto proListDto = new ProListDto(1L, "Project A", 75L, 1636600000000L, 1636700000000L, new UserDto(), new ArrayList<>());

        // Verify that the values are set correctly
        assertEquals(1L, proListDto.getId());
        assertEquals("Project A", proListDto.getProjectName());
        assertEquals(75L, proListDto.getPercentage());
        assertEquals(1636600000000L, proListDto.getStartDate());
        assertEquals(1636700000000L, proListDto.getEndDate());
        assertNotNull(proListDto.getUser());
        assertNotNull(proListDto.getTasks());
        assertFalse(proListDto.isClosed());
    }

    @Test
    public void testGetterAndSetter() {
        // Create a ProListDto
        ProListDto proListDto = new ProListDto();
        proListDto.setId(1L);
        proListDto.setProjectName("Project A");
        proListDto.setPercentage(75L);
        proListDto.setStartDate(1636600000000L); // Assuming a timestamp in milliseconds
        proListDto.setEndDate(1636700000000L); // Assuming a timestamp in milliseconds
        UserDto userDto = new UserDto();
        proListDto.setUser(userDto);
        List<TasksDto> tasksDtoList = new ArrayList<>();
        proListDto.setTasks(tasksDtoList);
        proListDto.setClosed(true);

        // Verify that the getters return the expected values
        assertEquals(1L, proListDto.getId());
        assertEquals("Project A", proListDto.getProjectName());
        assertEquals(75L, proListDto.getPercentage());
        assertEquals(1636600000000L, proListDto.getStartDate());
        assertEquals(1636700000000L, proListDto.getEndDate());
        assertEquals(userDto, proListDto.getUser());
        assertEquals(tasksDtoList, proListDto.getTasks());
        assertTrue(proListDto.isClosed());

        // Modify some values using setters
        proListDto.setId(2L);
        proListDto.setProjectName("Project B");
        proListDto.setPercentage(80L);
        proListDto.setStartDate(1636800000000L); // Assuming a different timestamp in milliseconds
        proListDto.setEndDate(1636900000000L); // Assuming a different timestamp in milliseconds
        UserDto newUserDto = new UserDto();
        proListDto.setUser(newUserDto);
        List<TasksDto> newTasksDtoList = new ArrayList<>();
        proListDto.setTasks(newTasksDtoList);
        proListDto.setClosed(false);

        // Verify that the modified values are reflected
        assertEquals(2L, proListDto.getId());
        assertEquals("Project B", proListDto.getProjectName());
        assertEquals(80L, proListDto.getPercentage());
        assertEquals(1636800000000L, proListDto.getStartDate());
        assertEquals(1636900000000L, proListDto.getEndDate());
        assertEquals(newUserDto, proListDto.getUser());
        assertEquals(newTasksDtoList, proListDto.getTasks());
        assertFalse(proListDto.isClosed());
    }

    // Add more tests for edge cases or specific scenarios

    // ... rest of the tests
}