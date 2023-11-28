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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
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
    void updateReview() throws Exception {
        long reviewId = 1L;
        ReviewDto updatedReviewDto = new ReviewDto();
        updatedReviewDto.setId(reviewId);

        // Mocking the service call
        when(reviewService.updateReview(eq(updatedReviewDto))).thenReturn(updatedReviewDto);

        // Call the controller method directly
        ResponseEntity<ReviewDto> response = reviewController.updateReview(reviewId, updatedReviewDto);

        // Verify that the service method was called with the correct parameters
        verify(reviewService).updateReview(eq(updatedReviewDto));

        // Verify the response
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedReviewDto, response.getBody());
    }
}
