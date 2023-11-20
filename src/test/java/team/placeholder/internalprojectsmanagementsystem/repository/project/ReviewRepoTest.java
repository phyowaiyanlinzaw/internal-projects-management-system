package team.placeholder.internalprojectsmanagementsystem.repository.project;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import team.placeholder.internalprojectsmanagementsystem.model.project.Review;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class ReviewRepoTest {
    @Mock
    private ReviewRepo reviewRepo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void findById() {
        long id = 1L;
        Review expectedReview = new Review();

        when(reviewRepo.findById(id)).thenReturn(expectedReview);

        Review result = reviewRepo.findById(id);

        assertEquals(expectedReview, result);
    }
    }



