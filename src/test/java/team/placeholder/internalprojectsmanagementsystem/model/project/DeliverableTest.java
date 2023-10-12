package team.placeholder.internalprojectsmanagementsystem.model.project;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.*;

class DeliverableTest {

    @MockBean
    private Deliverable deliverable;

    @MockBean
    private Project project;

    @BeforeEach
    void setUp() {
        deliverable = new Deliverable();
    }

    @Test
    public void testConstructor() {
        assertNotNull(deliverable);
    }

    @Test
    public void testId() {
        long deliverableId = 1L;
         deliverable.setId(deliverableId);
        assertEquals(deliverableId, deliverable.getId());
    }

    @Test

    public void testDescription() {
        deliverable.setDescription("Sample Description");
        assertEquals("Sample Description",deliverable.getDescription());
    }
    @Test
    public void testName() {
        deliverable.setName("Sample name");
        assertEquals("Sample name", deliverable.getName());
    }

    @Test
    public void testType() {
        deliverable.setType("Sample type");
        assertEquals("Sample type", deliverable.getType());
    }

    @Test
    public void testStatus() {
        deliverable.setStatus("In Progress");
        assertEquals("In Progress", deliverable.getStatus());
    }

    @Test
    public void testDueDate() {
        Date dueDate = new Date(System.currentTimeMillis());
        deliverable.setDue_date(dueDate);
        assertEquals(dueDate, deliverable.getDue_date());
    }

    @Test
    public void testProject() {
        deliverable.setProject(project);
        assertEquals(project, deliverable.getProject());
    }

}