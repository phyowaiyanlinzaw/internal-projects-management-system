package team.placeholder.internalprojectsmanagementsystem.dto.model.project;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReviewDtoTest {

    @Test
    public void testReviewDto() {
        // Create a ReviewDto object with some mock data
        ReviewDto reviewDto = new ReviewDto();

        // When
        reviewDto.setId(1L);
        reviewDto.setInternal_review_count(3);
        reviewDto.setExternal_review_count(5);

        // Then
        assertEquals(1L, reviewDto.getId());
        assertEquals(3, reviewDto.getInternal_review_count());
        assertEquals(5, reviewDto.getExternal_review_count());
    }
}
