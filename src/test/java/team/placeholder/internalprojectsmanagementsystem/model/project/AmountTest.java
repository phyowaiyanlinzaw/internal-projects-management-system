package team.placeholder.internalprojectsmanagementsystem.model.project;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;

class AmountTest {

    @MockBean
    private Amount amount;

    @BeforeEach
    public void setUp() {
        amount = new Amount();
    }

    @Test
    public void testConstructor() {
        assertNotNull(amount);
    }

    @Test
    public void testId() {
        long amountId = 1L;
        amount.setId(amountId);
        assertEquals(amountId, amount.getId());
    }

    @Test
    public void testAmount() {
        int basic_design = 1;
        int detail_design = 2;
        int coding = 3;
        int unit_testing = 4;
        int integrated_testing = 5;

        amount.setBasic_design(basic_design);
        amount.setDetail_design(detail_design);
        amount.setCoding(coding);
        amount.setUnit_testing(unit_testing);
        amount.setIntegrated_testing(integrated_testing);

        assertEquals(basic_design, amount.getBasic_design());
        assertEquals(detail_design, amount.getDetail_design());
        assertEquals(coding, amount.getCoding());
        assertEquals(unit_testing, amount.getUnit_testing());
        assertEquals(integrated_testing, amount.getIntegrated_testing());
    }

    @Test
    public void testProject() {
        Project project = new Project();
        amount.setProject(project);
        assertEquals(project, amount.getProject());
    }



}