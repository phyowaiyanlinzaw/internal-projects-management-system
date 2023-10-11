package team.placeholder.internalprojectsmanagementsystem.model.project;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;

class SystemOutLineTest {

    @MockBean
    private SystemOutLine systemOutLine;

    @MockBean
    private Project project;

    @BeforeEach
    public void setUp() {
        systemOutLine = new SystemOutLine();
    }

    @Test
    public void testConstructor() {
        assertNotNull(systemOutLine);
    }

    @Test
    public void testId() {
        systemOutLine.setId(1L);
        assertEquals(1L, systemOutLine.getId());
    }

    @Test
    public void testAnalysis() {
        systemOutLine.setAnalysis(true);
        assertTrue(systemOutLine.isAnalysis());
    }

    @Test
    public void testSystemDesign() {
        systemOutLine.setSys_design(true);
        assertTrue(systemOutLine.isSys_design());
    }

    @Test
    public void testCoding() {
        systemOutLine.setCoding(true);
        assertTrue(systemOutLine.isCoding());
    }

    @Test
    public void testTesting() {
        systemOutLine.setTesting(true);
        assertTrue(systemOutLine.isTesting());
    }

    @Test
    public void testDeployment() {
        systemOutLine.setDeploy(true);
        assertTrue(systemOutLine.isDeploy());
    }

    @Test
    public void testMaintenance() {
        systemOutLine.setMaintenance(true);
        assertTrue(systemOutLine.isMaintenance());
    }

    @Test
    public void testDocumentation() {
        systemOutLine.setDocument(true);
        assertTrue(systemOutLine.isDocument());
    }

    @Test
    public void testProject() {
        systemOutLine.setProject(project);
        assertEquals(project, systemOutLine.getProject());
    }
}



