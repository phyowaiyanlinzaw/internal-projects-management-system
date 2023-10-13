package team.placeholder.internalprojectsmanagementsystem.dto.model.issue;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class IssueCategoryDtoTest {

    @Test
    public void testIssueCategoryDto() {
        // Create an IssueCategoryDto object with some mock data
        IssueCategoryDto issueCategoryDto = new IssueCategoryDto();
        issueCategoryDto.setId(1L);
        issueCategoryDto.setName("Bug");

        // Test the getter methods
        assertEquals(1L, issueCategoryDto.getId());
        assertEquals("Bug", issueCategoryDto.getName());
    }
}

