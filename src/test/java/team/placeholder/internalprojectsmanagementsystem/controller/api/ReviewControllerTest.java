package team.placeholder.internalprojectsmanagementsystem.controller.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import team.placeholder.internalprojectsmanagementsystem.service.FakerService;
import team.placeholder.internalprojectsmanagementsystem.service.impl.issue.IssueServiceImpl;
import team.placeholder.internalprojectsmanagementsystem.service.impl.project.ReviewServiceImp;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ReviewControllerTest {

    @Mock
    private FakerService fakerService;

    @InjectMocks
    private ReviewController reviewController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    void generateFakeReview() {
        int count = 10;
        // Mock behavior
        doNothing().when(fakerService).generateAndSaveFakeReview(count);
        // Act
        String result = reviewController.generateFakeReview(count);
        // Assert
        assertEquals("Fake reviews generated successfully", result);
        // Verify that the service method was called
        verify(fakerService, times(1)).generateAndSaveFakeReview(count);

    }
}