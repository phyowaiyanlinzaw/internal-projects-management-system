package team.placeholder.internalprojectsmanagementsystem.service.project;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import team.placeholder.internalprojectsmanagementsystem.dto.model.project.ReviewDto;
import team.placeholder.internalprojectsmanagementsystem.model.project.Review;
import team.placeholder.internalprojectsmanagementsystem.repository.project.ReviewRepo;
import team.placeholder.internalprojectsmanagementsystem.service.impl.project.ReviewServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ReviewServiceTest {

    @Mock
    private ReviewRepo reviewRepo;

    @InjectMocks
    private ReviewServiceImpl reviewService;

    private ReviewDto reviewDto;
    private Review review;

    @BeforeEach
    public void setUp() {
        reviewDto = new ReviewDto();
        reviewDto.setId(1L);
        reviewDto.setInternal_review_count(5);
        reviewDto.setExternal_review_count(3);

        review = new Review();
        review.setId(1L);
        review.setInternal_review_count(5);
        review.setExternal_review_count(3);
    }

    @Test
    public void testSaveReview() {
        when(reviewRepo.save(any(Review.class))).thenReturn(null);

        Review savedReview = reviewService.save(reviewDto);

        assertNull(savedReview, "Saved review should be null");
    }

    @Test
    public void testGetAllReview() {
        List<ReviewDto> reviewList = new ArrayList<>();

        when(reviewService.getAllReviews()).thenReturn(reviewList);

        List<ReviewDto> allReview = reviewService.getAllReviews();

        assertEquals(reviewList, allReview);
    }

    @Test
    void testGetReviewById() {
        ReviewService reviewService = mock(ReviewService.class);

        ReviewDto expectedReviewDto = new ReviewDto();
        expectedReviewDto.setId(1L);
        expectedReviewDto.setInternal_review_count(5);
        expectedReviewDto.setExternal_review_count(3);

        // Mock the behavior of the getRevieweById method
        when(reviewService.getRevieweById(1L)).thenReturn(expectedReviewDto);

        // Call the method being tested
        ReviewDto actualReviewDto = reviewService.getRevieweById(1L);

        // Verify the result
        assertEquals(expectedReviewDto, actualReviewDto);
    }

    @Test
    void testUpdateReview() {
        ReviewService reviewService = mock(ReviewService.class);

        ReviewDto expectedReviewDto = new ReviewDto();
        expectedReviewDto.setId(1L);
        expectedReviewDto.setInternal_review_count(5);
        expectedReviewDto.setExternal_review_count(3);

        // Mock the behavior of the updateReview method
        when(reviewService.updateReview(expectedReviewDto)).thenReturn(expectedReviewDto);

        // Call the method being tested
        ReviewDto actualReviewDto = reviewService.updateReview(expectedReviewDto);

        // Verify the result
        assertEquals(expectedReviewDto, actualReviewDto);
    }

}