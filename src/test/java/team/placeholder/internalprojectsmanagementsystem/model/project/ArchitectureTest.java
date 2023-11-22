package team.placeholder.internalprojectsmanagementsystem.model.project;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

public class ArchitectureTest {

    @MockBean
    Architecture architecture1 = mock(Architecture.class);
    @MockBean
    Architecture architecture2 = mock(Architecture.class);
    @MockBean
    Project project = mock(Project.class);

    @Test
    public void testEqualsAndHashCode() {
        // Create two instances with the same ID
        architecture1 = new Architecture();
        architecture1.setId(1L);

        architecture2 = new Architecture();
        architecture2.setId(1L);

        // Test equals method
        assertEquals(architecture1, architecture2);
        assertEquals(architecture2, architecture1);

        // Test hashCode method
        assertEquals(architecture1.hashCode(), architecture2.hashCode());
    }

    @Test
    public void testNotEquals() {
        // Create two instances with different IDs
        architecture1 = new Architecture();
        architecture1.setId(1L);

        architecture2 = new Architecture();
        architecture2.setId(2L);

        // Test equals method
        assertNotEquals(architecture1, architecture2);
        assertNotEquals(architecture2, architecture1);

        // Test hashCode method (optional, but recommended)
        assertNotEquals(architecture1.hashCode(), architecture2.hashCode());
    }

    @Test
    public void testGetterAndSetter() {
        // Create an instance of the Architecture class
        architecture1 = new Architecture();

        // Set values using setters
        architecture1.setId(1L);
        architecture1.setTech_name("Java");

        // Test getters
        assertEquals(1L, architecture1.getId());
        assertEquals("Java", architecture1.getTech_name());
    }

    @Test
    public void testUniqueConstraint() {
        // Create two instances with the same tech_name
        architecture1 = new Architecture();
        architecture1.setTech_name("Java");

        architecture2 = new Architecture();
        architecture2.setTech_name("Java");

        // Ensure that equals method considers them equal
        assertEquals(architecture1, architecture2);
        assertEquals(architecture2, architecture1);

        // Ensure that hashCode method considers them equal
        assertEquals(architecture1.hashCode(), architecture2.hashCode());
    }




}
