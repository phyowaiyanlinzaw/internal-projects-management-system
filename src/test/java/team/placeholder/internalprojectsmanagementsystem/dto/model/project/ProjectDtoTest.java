package team.placeholder.internalprojectsmanagementsystem.dto.model.project;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import team.placeholder.internalprojectsmanagementsystem.model.project.projectenums.Development_phase;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.*;

public class ProjectDtoTest {
    @Mock
    private ProjectDto projectDto;
    @Test
    public void testProjectDto() {
        // Create a ProjectDto object with some mock data
        projectDto = new ProjectDto();
        projectDto.setId(1L);
        projectDto.setName("Sample Project");
        projectDto.setBackground("This is a test project.");
        projectDto.setDuration(30);
        projectDto.setStart_date(new Date(System.currentTimeMillis()));
        projectDto.setEnd_date(new Date(System.currentTimeMillis() + 30 * 24 * 60 * 60 * 1000)); // 30 days later
        projectDto.setCurrent_phase(Development_phase.PLANNING);
        projectDto.setObjective("Test Objective");

        // Test the getter methods
        assertEquals(1L, projectDto.getId());
        assertEquals("Sample Project", projectDto.getName());
        assertEquals("This is a test project.", projectDto.getBackground());
        assertEquals(30, projectDto.getDuration());
        assertNotNull(projectDto.getStart_date());
        assertNotNull(projectDto.getEnd_date());
        assertEquals(Development_phase.PLANNING, projectDto.getCurrent_phase());
        assertEquals("Test Objective", projectDto.getObjective());
    }
}

