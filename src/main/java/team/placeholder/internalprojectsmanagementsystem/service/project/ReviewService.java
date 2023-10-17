package team.placeholder.internalprojectsmanagementsystem.service.project;

import team.placeholder.internalprojectsmanagementsystem.dto.model.project.ReviewDto;

import java.util.List;

public interface ReviewService {

    ReviewDto save(ReviewDto reviewDto);

    ReviewDto update(ReviewDto reviewDto);

    ReviewDto delete(ReviewDto reviewDto);

    List<ReviewDto> getAllReviews();

    ReviewDto getRevieweById(int id);


}