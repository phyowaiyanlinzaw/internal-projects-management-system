package team.placeholder.internalprojectsmanagementsystem.service.impl.project;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import team.placeholder.internalprojectsmanagementsystem.dto.mapper.project.ReviewMapper;
import team.placeholder.internalprojectsmanagementsystem.dto.model.project.ReviewDto;
import team.placeholder.internalprojectsmanagementsystem.model.project.Review;
import team.placeholder.internalprojectsmanagementsystem.repository.project.ReviewRepo;

import java.util.ArrayList;
import java.util.List;


import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ReviewServiceImplTest {
    @Mock
    private ReviewRepo reviewRepo;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private ReviewServiceImpl reviewService;




    @Test
    void save() {
        ReviewDto reviewDto = new ReviewDto();
        reviewDto.setInternal_review_count(2);
        reviewDto.setExternal_review_count(3);

        // Act
        reviewService.save(reviewDto);

        // Assert
        // You can add assertions based on your specific requirements.
        // For example, you might want to verify that the save method was called.
        verify(reviewRepo, times(1)).save(any(Review.class));


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
    void updateReview() {
        ReviewDto reviewDto = new ReviewDto();
        reviewDto.setId(1L);
        reviewDto.setInternal_review_count(2);
        reviewDto.setExternal_review_count(3);

        Review existingReview = new Review();
        existingReview.setId(1L);
        existingReview.setInternal_review_count(1); // Initial internal count
        existingReview.setExternal_review_count(1); // Initial external count

        // Mocking behavior of the repository
        when(reviewRepo.findById(1L)).thenReturn(existingReview);
        when(reviewRepo.save(any(Review.class))).thenReturn(existingReview);

        // Mocking behavior of the modelMapper
        when(modelMapper.map(any(), eq(ReviewDto.class)))
                .thenReturn(new ReviewDto());

        // Act
        ReviewDto result = reviewService.updateReview(reviewDto);

        // Assert
        Assertions.assertThat(result)
                .usingRecursiveComparison()
                .isEqualTo(new ReviewDto());

        // Verify that findById and save methods were called
        verify(reviewRepo, times(1)).findById(1L);
        verify(reviewRepo, times(1)).save(existingReview);
    }
}