package team.placeholder.internalprojectsmanagementsystem.controller.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import team.placeholder.internalprojectsmanagementsystem.dto.model.project.ReviewDto;
import team.placeholder.internalprojectsmanagementsystem.service.FakerService;
import team.placeholder.internalprojectsmanagementsystem.service.impl.project.ReviewServiceImpl;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@ExtendWith(MockitoExtension.class)
class ReviewControllerTest {

    @Mock
    private ReviewServiceImpl reviewService;

    @Mock
    private FakerService fakerService;

    @InjectMocks
    private ReviewController reviewController;

    @Test
    void getAllReview() throws Exception {
        when(reviewService.getAllReviews()).thenReturn(Arrays.asList(/* Add some ReviewDto objects */));

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(reviewController).build();

        mockMvc.perform(MockMvcRequestBuilders.get("/api/review/list"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray()); // Assuming the response is a JSON array
    }

    @Test
    void updateReviewSuccess() {
        // Arrange
        long id = 1L;
        ReviewDto reviewDto = new ReviewDto();
        reviewDto.setId(id);
        when(reviewService.updateReview(any(ReviewDto.class))).thenReturn(reviewDto);

        // Act
        ResponseEntity<ReviewDto> responseEntity = reviewController.updateReview(id, reviewDto);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertEquals(reviewDto, responseEntity.getBody());

        // Verify that the updateReview method was called with the correct argument
        verify(reviewService, times(1)).updateReview(reviewDto);
    }

    @Test
    void updateReviewFailure() {
        // Arrange
        long id = 2L;
        ReviewDto reviewDto = new ReviewDto();
        reviewDto.setId(id);
        when(reviewService.updateReview(any(ReviewDto.class))).thenReturn(null);

        // Act
        ResponseEntity<ReviewDto> responseEntity = reviewController.updateReview(id, reviewDto);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertNull(responseEntity.getBody());

        // Verify that the updateReview method was called with the correct argument
        verify(reviewService, times(1)).updateReview(reviewDto);
    }

    @Test
    void updateReviewInvalidId() {
        // Arrange
        long invalidId = -1L;
        ReviewDto reviewDto = new ReviewDto();
        reviewDto.setId(invalidId);  // Setting an invalid ID

        // Act
        ResponseEntity<ReviewDto> responseEntity = reviewController.updateReview(invalidId, reviewDto);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertNull(responseEntity.getBody());

        // Verify that the updateReview method was not called (invalid ID case)
        verify(reviewService, never()).updateReview(any(ReviewDto.class));
    }
}
