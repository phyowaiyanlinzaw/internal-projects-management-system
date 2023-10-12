package team.placeholder.internalprojectsmanagementsystem.model.project;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;

public class AmountTest {

    @MockBean
    private Amount amount1;

    @MockBean
    private Amount amount2;

    @MockBean
    private Project project;

    @BeforeEach
    public void setUp() {
        amount1 = new Amount();
        amount1.setId(1);
        amount1.setBasic_design(10);
        amount1.setDetail_design(20);
        amount1.setCoding(30);
        amount1.setUnit_testing(40);
        amount1.setIntegrated_testing(50);

        amount2 = new Amount();
        amount2.setId(2);
        amount2.setBasic_design(10);
        amount2.setDetail_design(20);
        amount2.setCoding(30);
        amount2.setUnit_testing(40);
        amount2.setIntegrated_testing(50);

        project = new Project();
        project.setId(101L);
        amount1.setProject(project);
    }

    @Test
    public void testConstructor() {
        assertNotNull(amount2);
        assertNotNull(amount1);
    }

    @Test
    public void testEquals() {
        assertEquals(amount1, amount1);
        Amount amount1Copy = new Amount();
        amount1Copy.setId(1);
        assertEquals(amount1, amount1Copy);
        assertNotEquals(amount1, amount2);
        assertNotEquals("Not an Amount object", amount1);
    }

    @Test
    public void testHashCode() {
        assertEquals(amount1.hashCode(), amount1.hashCode());
        Amount amount1Copy = new Amount();
        amount1Copy.setId(1);
        assertEquals(amount1.hashCode(), amount1Copy.hashCode());
        assertNotEquals(amount1.hashCode(), amount2.hashCode());
    }
}
