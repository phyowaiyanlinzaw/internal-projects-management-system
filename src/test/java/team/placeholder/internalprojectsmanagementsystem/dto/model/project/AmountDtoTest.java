package team.placeholder.internalprojectsmanagementsystem.dto.model.project;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AmountDtoTest {
    @Mock
    private AmountDto amountDto;

    @Test
    public void testAmountDto() {
        // Create an AmountDto object with some mock data
        amountDto = new AmountDto();
        amountDto.setId(1L);
        amountDto.setBasic_design(10);
        amountDto.setDetail_design(20);
        amountDto.setCoding(30);
        amountDto.setUnit_testing(40);
        amountDto.setIntegrated_testing(50);

        // Test the getter methods
        assertEquals(1L, amountDto.getId());
        assertEquals(10, amountDto.getBasic_design());
        assertEquals(20, amountDto.getDetail_design());
        assertEquals(30, amountDto.getCoding());
        assertEquals(40, amountDto.getUnit_testing());
        assertEquals(50, amountDto.getIntegrated_testing());
    }
}
