package team.placeholder.internalprojectsmanagementsystem.dto.uidto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ManMonthDtoTest {

    @Test
    public void testGetterAndSetter() {
        // Create a ManMonthDto
        ManMonthDto manMonthDto = new ManMonthDto();
        manMonthDto.setActualManMonthHours(120.5);
        manMonthDto.setPlanManMonthHours(150.0);
        manMonthDto.setMonthName("January");

        // Verify that the getters return the expected values
        assertEquals(120.5, manMonthDto.getActualManMonthHours(), 0.001); // Specify a delta for double comparisons
        assertEquals(150.0, manMonthDto.getPlanManMonthHours(), 0.001);
        assertEquals("January", manMonthDto.getMonthName());

        // Modify some values using setters
        manMonthDto.setActualManMonthHours(100.0);
        manMonthDto.setPlanManMonthHours(130.0);
        manMonthDto.setMonthName("February");

        // Verify that the modified values are reflected
        assertEquals(100.0, manMonthDto.getActualManMonthHours(), 0.001);
        assertEquals(130.0, manMonthDto.getPlanManMonthHours(), 0.001);
        assertEquals("February", manMonthDto.getMonthName());
    }

    // Add more tests for edge cases or specific scenarios

    // ... rest of the tests
}
