package team.placeholder.internalprojectsmanagementsystem.dto.mapper.project;

import org.junit.jupiter.api.Test;
import team.placeholder.internalprojectsmanagementsystem.dto.model.project.ReviewDto;
import team.placeholder.internalprojectsmanagementsystem.model.project.Review;

import static org.junit.jupiter.api.Assertions.*;

class ReviewMapperTest {

    @Test
    public void testToReviewDto() {
        // Create a sample Review
        Review review = new Review();
        review.setId(1L);
        review.setInternal_review_count(5);
        review.setExternal_review_count(10);

        // Use the mapper to convert the sample Review to ReviewDto
        ReviewDto reviewDto = ReviewMapper.toReviewDto(review);

        // Verify that the mapping is correct
        assertEquals(1L, reviewDto.getId());
        assertEquals(5, reviewDto.getInternal_review_count());
        assertEquals(10, reviewDto.getExternal_review_count());
    }

    @Test
    public void testToReviewDto_NullInput() {
        // Use the mapper to convert a null Review to ReviewDto
        ReviewDto reviewDto = ReviewMapper.toReviewDto(null);

        // Verify that the result is also null
        assertNull(reviewDto);
    }

    @Test
    public void testToReview() {
        // Create a sample ReviewDto
        ReviewDto reviewDto = new ReviewDto();
        reviewDto.setId(1L);
        reviewDto.setInternal_review_count(5);
        reviewDto.setExternal_review_count(10);

        // Use the mapper to convert the sample ReviewDto to Review
        Review review = ReviewMapper.toReview(reviewDto);

        // Verify that the mapping is correct
        assertEquals(1L, review.getId());
        assertEquals(5, review.getInternal_review_count());

    }

    @Test
    public void testToReview_NullInput() {
        // Use the mapper to convert a null ReviewDto to Review
        Review review = ReviewMapper.toReview(null);

        // Verify that the result is also null
        assertNull(review);
    }



}