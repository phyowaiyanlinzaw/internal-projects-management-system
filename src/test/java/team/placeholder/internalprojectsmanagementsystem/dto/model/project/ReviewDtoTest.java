package team.placeholder.internalprojectsmanagementsystem.dto.model.project;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReviewDtoTest {
    @Mock
    private ReviewDto reviewDto;

    @Test
    public void testReviewDto() {
        // Create a ReviewDto object with some mock data
        reviewDto = new ReviewDto();
        reviewDto.setId(1L);
        reviewDto.setInternal_review_count(5);
        reviewDto.setExternal_review_count(3);

        // Test the getter methods
        assertEquals(1L, reviewDto.getId());
        assertEquals(5, reviewDto.getInternal_review_count());
        assertEquals(3, reviewDto.getExternal_review_count());
    }
}
