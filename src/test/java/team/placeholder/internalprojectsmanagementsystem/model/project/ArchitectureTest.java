package team.placeholder.internalprojectsmanagementsystem.model.project;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import static org.junit.jupiter.api.Assertions.*;

public class ArchitectureTest {

    @MockBean
    private Architecture architecture1;


    @MockBean
    private Architecture architecture2;

    @MockBean
    private Project project;

    @BeforeEach
    public void setUp() {
        architecture1 = new Architecture();
        architecture1.setId(1L);
        architecture1.setTech_name("Tech1");

        architecture2 = new Architecture();
        architecture2.setId(2L);
        architecture2.setTech_name("Tech2");

        project = new Project();
        project.setId(101L);
        architecture1.setProject(project);
    }

    @Test
    public void testConstructor() {
        assertNotNull(architecture1);
        assertNotNull(architecture2);
    }

    @Test
    public void testId() {
        assertEquals(1L, architecture1.getId());
        assertEquals(2L, architecture2.getId());
    }

    @Test
    public void testTechName() {
        assertEquals("Tech1", architecture1.getTech_name());
        assertEquals("Tech2", architecture2.getTech_name());
    }

    @Test
    public void testProject() {
        assertEquals(project, architecture1.getProject());
        assertNull(architecture2.getProject());
    }

    @Test
    public void testEquals() {
        assertEquals(architecture1, architecture1);
        Architecture architecture1Copy = new Architecture();
        architecture1Copy.setId(1L);
        assertEquals(architecture1, architecture1Copy);
        assertNotEquals(architecture1, architecture2);
        assertNotEquals("Not an Architecture object", architecture1);
    }

    @Test
    public void testHashCode() {
        assertEquals(architecture1.hashCode(), architecture1.hashCode());
        Architecture architecture1Copy = new Architecture();
        architecture1Copy.setId(1L);
        assertEquals(architecture1.hashCode(), architecture1Copy.hashCode());
        assertNotEquals(architecture1.hashCode(), architecture2.hashCode());
    }
}
