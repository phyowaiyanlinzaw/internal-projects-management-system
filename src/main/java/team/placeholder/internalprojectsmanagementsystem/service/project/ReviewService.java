package team.placeholder.internalprojectsmanagementsystem.service.project;

import team.placeholder.internalprojectsmanagementsystem.dto.model.project.ReviewDto;
import team.placeholder.internalprojectsmanagementsystem.model.project.Review;

import java.util.List;

public interface ReviewService {

    Review save(ReviewDto reviewDto);

    ReviewDto update(ReviewDto reviewDto);

    void deleteReview(ReviewDto reviewDto);

    List<ReviewDto> getAllReviews();

    ReviewDto getRevieweById(long id);


}
