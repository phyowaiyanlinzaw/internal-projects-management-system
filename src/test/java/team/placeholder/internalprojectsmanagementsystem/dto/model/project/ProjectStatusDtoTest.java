package team.placeholder.internalprojectsmanagementsystem.dto.model.project;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import team.placeholder.internalprojectsmanagementsystem.model.project.projectenums.Status;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProjectStatusDtoTest {
    @Mock
    private ProjectStatusDto projectStatusDto;

    @Test
    public void testProjectStatusDto() {
        // Create a ProjectStatusDto object with some mock data
        projectStatusDto = new ProjectStatusDto();
        projectStatusDto.setId(1L);
        projectStatusDto.setName(Status.IN_PROGRESS);

        // Test the getter methods
        assertEquals(1L, projectStatusDto.getId());
        assertEquals(Status.IN_PROGRESS, projectStatusDto.getName());
    }
}

