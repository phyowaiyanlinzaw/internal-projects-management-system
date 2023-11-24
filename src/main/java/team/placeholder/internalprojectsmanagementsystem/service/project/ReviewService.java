package team.placeholder.internalprojectsmanagementsystem.service.project;

import team.placeholder.internalprojectsmanagementsystem.dto.model.project.ReviewDto;
import team.placeholder.internalprojectsmanagementsystem.model.project.Review;

import java.util.List;

public interface ReviewService {

    Review save(ReviewDto reviewDto);


    List<ReviewDto> getAllReviews();



    ReviewDto updateReview(ReviewDto reviewDto);



}
