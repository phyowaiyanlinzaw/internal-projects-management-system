package team.placeholder.internalprojectsmanagementsystem.dto.uidto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductivityDtoTest {
    @Test
    public void testGetterAndSetter() {
        // Create a ProductivityDto
        ProductivityDto productivityDto = new ProductivityDto();
        productivityDto.setMonthName("January");
        productivityDto.setProductivityRatio(0.85);

        // Verify that the getters return the expected values
        assertEquals("January", productivityDto.getMonthName());
        assertEquals(0.85, productivityDto.getProductivityRatio(), 0.001); // Specify a delta for double comparisons

        // Modify some values using setters
        productivityDto.setMonthName("February");
        productivityDto.setProductivityRatio(0.92);

        // Verify that the modified values are reflected
        assertEquals("February", productivityDto.getMonthName());
        assertEquals(0.92, productivityDto.getProductivityRatio(), 0.001);
    }


}