package team.placeholder.internalprojectsmanagementsystem.model.project;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import team.placeholder.internalprojectsmanagementsystem.model.project.projectenums.DevelopmentPhase;
import team.placeholder.internalprojectsmanagementsystem.repository.project.ProjectRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

public class ProjectTest {
    @Mock
    Project project = mock(Project.class);


    @Test
    public void testProjectFields() {
        // Create a Project instance
        Project project = new Project();
        project.setId(1L);
        project.setName("Test Project");
        project.setBackground("Test Background");
        project.setDuration(10);
        project.setStart_date(System.currentTimeMillis());
        project.setEnd_date(System.currentTimeMillis() + 1000000);
        project.setCurrent_phase(DevelopmentPhase.PLANNING);
        project.setObjective("Test Objective");

        // Verify the fields
        assertEquals(1L, project.getId());
        assertEquals("Test Project", project.getName());
        assertEquals("Test Background", project.getBackground());
        assertEquals(10, project.getDuration());
        // You might want to use a tolerance for comparing timestamps due to potential minor differences
        assertEquals(System.currentTimeMillis(), project.getStart_date(), 1000);
        assertEquals(System.currentTimeMillis() + 1000000, project.getEnd_date(), 1000);
        assertEquals(DevelopmentPhase.PLANNING, project.getCurrent_phase());
        assertEquals("Test Objective", project.getObjective());
    }

}



