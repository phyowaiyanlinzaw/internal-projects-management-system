package team.placeholder.internalprojectsmanagementsystem.service.impl.project;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import team.placeholder.internalprojectsmanagementsystem.dto.mapper.project.ReviewMapper;
import team.placeholder.internalprojectsmanagementsystem.dto.model.project.ReviewDto;
import team.placeholder.internalprojectsmanagementsystem.model.project.Review;
import team.placeholder.internalprojectsmanagementsystem.repository.project.ReviewRepo;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

class ReviewServiceImplTest {
    @Mock
    private ReviewRepo reviewRepo;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private ReviewServiceImpl reviewService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void save() {




    }

    @Test
    void getAllReviews() {
        List<Review> reviewList = new ArrayList<>();
        reviewList.add(new Review());
        reviewList.add(new Review());

        when(reviewRepo.findAll()).thenReturn(reviewList);

        // When
        List<ReviewDto> reviewDtos = reviewService.getAllReviews();

        // Then
        assertEquals(reviewList.size(), reviewDtos.size());

        // Verify that findAll method of reviewRepo is called
        verify(reviewRepo, times(1)).findAll();
    }

    @Test
    void getRevieweById() {

    }

    @Test
    void updateReview() {
    }
}