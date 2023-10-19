package team.placeholder.internalprojectsmanagementsystem.service.project;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import team.placeholder.internalprojectsmanagementsystem.dto.model.project.ReviewDto;
import team.placeholder.internalprojectsmanagementsystem.dto.model.project.TasksDto;
import team.placeholder.internalprojectsmanagementsystem.model.project.Review;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ReviewServiceTest {

    @Mock
    private ReviewService reviewService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSaveReview() {
        ReviewDto reviewDto = new ReviewDto();

        Mockito.when(reviewService.save(reviewDto)).thenReturn(reviewDto);

        ReviewDto saveReview = reviewService.save(reviewDto);

        assertEquals(reviewDto,saveReview);
    }

    @Test
    public void testUpdateReview() {
        ReviewDto reviewDto = new ReviewDto();

        Mockito.when(reviewService.update(reviewDto)).thenReturn(reviewDto);

        ReviewDto updateReviewDto = reviewService.update(reviewDto);
        assertEquals(reviewDto,updateReviewDto);
    }

    @Test
    public void testDeleteReview() {
        ReviewDto reviewDto = new ReviewDto();
        reviewService.deleteReview(reviewDto);
        Mockito.verify(reviewService, Mockito.times(1)).deleteReview(reviewDto);
    }

    @Test
    public void testGetAllReview() {
        List<ReviewDto> reviewList = new ArrayList<>();

        Mockito.when(reviewService.getAllReviews()).thenReturn(reviewList);

        List<ReviewDto> allReview = reviewService.getAllReviews();

        assertEquals(reviewList,allReview);
    }

    @Test
    public void testGetReviewById() {
        long reviewId = 1;

        ReviewDto reviewDto = new ReviewDto();
        Mockito.when(reviewService.getRevieweById(reviewId)).thenReturn(reviewDto);
        ReviewDto reviewById = reviewService.getRevieweById(1L);
        assertEquals(reviewDto , reviewById);
    }




}