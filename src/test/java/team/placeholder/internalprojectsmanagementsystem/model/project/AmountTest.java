package team.placeholder.internalprojectsmanagementsystem.model.project;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AmountTest {
    private Amount amount1;
    private Amount amount2;
    private Project project;

    @BeforeEach
    public void setUp() {
        amount1 = new Amount();
        amount1.setId(1L);
        amount1.setBasic_design(10);
        amount1.setDetail_design(20);
        amount1.setCoding(30);
        amount1.setUnit_testing(40);
        amount1.setIntegrated_testing(50);

        amount2 = new Amount();
        amount2.setId(2L);
        amount2.setBasic_design(15);
        amount2.setDetail_design(25);
        amount2.setCoding(35);
        amount2.setUnit_testing(45);
        amount2.setIntegrated_testing(55);

        project = new Project();
        project.setId(101L);
        amount1.setProject(project);
    }

    @Test
    public void testId() {
        assertEquals(1L, amount1.getId());
        assertEquals(2L, amount2.getId());
    }

    @Test
    public void testBasicDesign() {
        assertEquals(10, amount1.getBasic_design());
        assertEquals(15, amount2.getBasic_design());
    }

    @Test
    public void testDetailDesign() {
        assertEquals(20, amount1.getDetail_design());
        assertEquals(25, amount2.getDetail_design());
    }

    @Test
    public void testCoding() {
        assertEquals(30, amount1.getCoding());
        assertEquals(35, amount2.getCoding());
    }

    @Test
    public void testUnitTesting() {
        assertEquals(40, amount1.getUnit_testing());
        assertEquals(45, amount2.getUnit_testing());
    }

    @Test
    public void testIntegratedTesting() {
        assertEquals(50, amount1.getIntegrated_testing());
        assertEquals(55, amount2.getIntegrated_testing());
    }

    @Test
    public void testProject() {
        assertEquals(project, amount1.getProject());
        assertNull(amount2.getProject());
    }

    @Test
    public void testEquals() {
        assertEquals(amount1, amount1);
        Amount amount1Copy = new Amount();
        amount1Copy.setId(1L);
        assertEquals(amount1, amount1Copy);
        assertNotEquals(amount1, amount2);
        assertNotEquals("Not an Amount object", amount1);
    }

    @Test
    public void testHashCode() {
        assertEquals(amount1.hashCode(), amount1.hashCode());
        Amount amount1Copy = new Amount();
        amount1Copy.setId(1L);
        assertEquals(amount1.hashCode(), amount1Copy.hashCode());
        assertNotEquals(amount1.hashCode(), amount2.hashCode());
    }
}
