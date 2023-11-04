package team.placeholder.internalprojectsmanagementsystem.model.project;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;

public class SystemOutLineTest {

    @MockBean
    private SystemOutLine systemOutline1;

    @MockBean
    private SystemOutLine systemOutline2;

    @MockBean
    private Project project;

    @BeforeEach
    public void setUp() {
        systemOutline1 = new SystemOutLine();
        systemOutline1.setId(1);
        systemOutline1.setAnalysis(true);
        systemOutline1.setSys_design(false);
        systemOutline1.setCoding(true);
        systemOutline1.setTesting(false);
        systemOutline1.setDeploy(true);
        systemOutline1.setMaintenance(false);
        systemOutline1.setDocument(true);

        systemOutline2 = new SystemOutLine();
        systemOutline2.setId(2);
        systemOutline2.setAnalysis(false);
        systemOutline2.setSys_design(true);
        systemOutline2.setCoding(false);
        systemOutline2.setTesting(true);
        systemOutline2.setDeploy(false);
        systemOutline2.setMaintenance(true);
        systemOutline2.setDocument(false);

        project = new Project();
        project.setId(101L);
//        systemOutline1.setProject(project);
    }

    @Test
    public void testConstructor() {
        assertNotNull(systemOutline1);
        assertNotNull(systemOutline2);
    }

    @Test
    public void testId() {
        assertEquals(1L, systemOutline1.getId());
        assertEquals(2L, systemOutline2.getId());
    }

    @Test
    public void testAnalysis() {
        assertTrue(systemOutline1.isAnalysis());
        assertFalse(systemOutline2.isAnalysis());
    }

    @Test
    public void testSysDesign() {
        assertFalse(systemOutline1.isSys_design());
        assertTrue(systemOutline2.isSys_design());
    }

    @Test
    public void testCoding() {
        assertTrue(systemOutline1.isCoding());
        assertFalse(systemOutline2.isCoding());
    }

    @Test
    public void testTesting() {
        assertFalse(systemOutline1.isTesting());
        assertTrue(systemOutline2.isTesting());
    }

    @Test
    public void testDeploy() {
        assertTrue(systemOutline1.isDeploy());
        assertFalse(systemOutline2.isDeploy());
    }

    @Test
    public void testMaintenance() {
        assertFalse(systemOutline1.isMaintenance());
        assertTrue(systemOutline2.isMaintenance());
    }

    @Test
    public void testDocument() {
        assertTrue(systemOutline1.isDocument());
        assertFalse(systemOutline2.isDocument());
    }

//    @Test
//    public void testProject() {
//        assertEquals(project, systemOutline1.getProject());
//        assertNull(systemOutline2.getProject());
//    }

    @Test
    public void testEquals() {
        assertEquals(systemOutline1, systemOutline1);
        SystemOutLine systemOutline1Copy = new SystemOutLine();
        systemOutline1Copy.setId(1L);
        assertEquals(systemOutline1, systemOutline1Copy);
        assertNotEquals(systemOutline1, systemOutline2);
        assertNotEquals("Not a SystemOutLine object", systemOutline1);
    }

    @Test
    public void testHashCode() {
        assertEquals(systemOutline1.hashCode(), systemOutline1.hashCode());
        SystemOutLine systemOutline1Copy = new SystemOutLine();
        systemOutline1Copy.setId(1L);
        assertEquals(systemOutline1.hashCode(), systemOutline1Copy.hashCode());
        assertNotEquals(systemOutline1.hashCode(), systemOutline2.hashCode());
    }
}
