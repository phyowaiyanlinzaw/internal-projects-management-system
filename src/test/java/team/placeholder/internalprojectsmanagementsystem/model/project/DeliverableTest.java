//package team.placeholder.internalprojectsmanagementsystem.model.project;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.boot.test.mock.mockito.MockBean;
//
//import java.sql.Date;
//import static org.junit.jupiter.api.Assertions.*;
//
//public class DeliverableTest {
//
//    @MockBean
//    private Deliverable deliverable1;
//
//
//    @MockBean
//    private Deliverable deliverable2;
//
//
//    @MockBean
//    private Project project;
//
//    @BeforeEach
//    public void setUp() {
//        deliverable1 = new Deliverable();
//        deliverable1.setId(1);
//        deliverable1.setDescription("Description1");
//        deliverable1.setName("Name1");
//        deliverable1.setType("Type1");
//        deliverable1.setStatus("Status1");
//        deliverable1.setDue_date(Date.valueOf("2023-10-31"));
//
//        deliverable2 = new Deliverable();
//        deliverable2.setId(2);
//        deliverable2.setDescription("Description2");
//        deliverable2.setName("Name2");
//        deliverable2.setType("Type2");
//        deliverable2.setStatus("Status2");
//        deliverable2.setDue_date(Date.valueOf("2023-11-15"));
//
//        project = new Project();
//        project.setId(101L);
//        deliverable1.setProject(project);
//    }
//
//
//    @Test
//    public void testConstructor() {
//        assertNotNull(deliverable1);
//        assertNotNull(deliverable2);
//    }
//
//    @Test
//    public void testId() {
//        assertEquals(1L, deliverable1.getId());
//        assertEquals(2L, deliverable2.getId());
//    }
//
//    @Test
//    public void testDescription() {
//        assertEquals("Description1", deliverable1.getDescription());
//        assertEquals("Description2", deliverable2.getDescription());
//    }
//
//    @Test
//    public void testName() {
//        assertEquals("Name1", deliverable1.getName());
//        assertEquals("Name2", deliverable2.getName());
//    }
//
//    @Test
//    public void testType() {
//        assertEquals("Type1", deliverable1.getType());
//        assertEquals("Type2", deliverable2.getType());
//    }
//
//    @Test
//    public void testStatus() {
//        assertEquals("Status1", deliverable1.getStatus());
//        assertEquals("Status2", deliverable2.getStatus());
//    }
//
//    @Test
//    public void testDueDate() {
//        assertEquals(Date.valueOf("2023-10-31"), deliverable1.getDue_date());
//        assertEquals(Date.valueOf("2023-11-15"), deliverable2.getDue_date());
//    }
//
//    @Test
//    public void testProject() {
//        assertEquals(project, deliverable1.getProject());
//        assertNull(deliverable2.getProject());
//    }
//
//    @Test
//    public void testEquals() {
//        assertEquals(deliverable1, deliverable1);
//        Deliverable deliverable1Copy = new Deliverable();
//        deliverable1Copy.setId(1L);
//        assertEquals(deliverable1, deliverable1Copy);
//        assertNotEquals(deliverable1, deliverable2);
//        assertNotEquals("Not a Deliverable object", deliverable1);
//    }
//
//    @Test
//    public void testHashCode() {
//        assertEquals(deliverable1.hashCode(), deliverable1.hashCode());
//        Deliverable deliverable1Copy = new Deliverable();
//        deliverable1Copy.setId(1L);
//        assertEquals(deliverable1.hashCode(), deliverable1Copy.hashCode());
//        assertNotEquals(deliverable1.hashCode(), deliverable2.hashCode());
//    }
//}
