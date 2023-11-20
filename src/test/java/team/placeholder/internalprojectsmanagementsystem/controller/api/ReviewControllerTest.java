package team.placeholder.internalprojectsmanagementsystem.controller.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import team.placeholder.internalprojectsmanagementsystem.dto.model.project.ReviewDto;
import team.placeholder.internalprojectsmanagementsystem.service.FakerService;
import team.placeholder.internalprojectsmanagementsystem.service.impl.issue.IssueServiceImpl;
import team.placeholder.internalprojectsmanagementsystem.service.project.ReviewService;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class ReviewControllerTest {

    @Mock
    private FakerService fakerService;

    @Mock
    private ReviewService reviewService;

    @InjectMocks
    private ReviewController reviewController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


 @Test
    public void testGetAllReview(){

     List<ReviewDto> reviewDtoList = Arrays.asList(
             new ReviewDto()

     );
     when(reviewService.getAllReviews()).thenReturn(reviewDtoList);

     ResponseEntity<List<ReviewDto>> responseEntity = reviewController.getAllReview();

     assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
     assertEquals(reviewDtoList, responseEntity.getBody());

 }

 @Test
    public void testUpdateReview(){


     long reviewId = 1L;
     ReviewDto updatedReviewDto = new ReviewDto();


     when(reviewService.updateReview(any())).thenReturn(updatedReviewDto);

     ResponseEntity<ReviewDto> responseEntity = reviewController.updateReview(reviewId, updatedReviewDto);

     assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
     assertEquals(updatedReviewDto, responseEntity.getBody());

 }

}