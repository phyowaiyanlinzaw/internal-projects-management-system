package team.placeholder.internalprojectsmanagementsystem.model.project;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;

class ArchitectureTest {

    @MockBean
    private Architecture architecture;

    @MockBean
    private Project project;

    @BeforeEach
    public void setUp() {
        architecture = new Architecture ();

    }

    @Test
    public void testConstructor() {
        assertNotNull(architecture);
    }

    @Test
    public void testId() {
        long architectureId = 1L;
        architecture.setId(architectureId);
        assertEquals(architectureId, architecture.getId());
    }

    @Test
    public void testTechName() {
       String tech_name = "Java";
       architecture.setTech_name(tech_name);
       assertEquals(tech_name, architecture.getTech_name());
    }

    @Test
    public void testProject() {
        architecture.setProject(project);
        assertEquals(project, architecture.getProject());
    }



}