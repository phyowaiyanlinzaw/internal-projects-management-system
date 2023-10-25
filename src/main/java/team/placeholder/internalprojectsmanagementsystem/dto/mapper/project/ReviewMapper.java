package team.placeholder.internalprojectsmanagementsystem.dto.mapper.project;

import team.placeholder.internalprojectsmanagementsystem.dto.model.project.ReviewDto;
import team.placeholder.internalprojectsmanagementsystem.model.project.Review;

public class ReviewMapper {
    public static ReviewDto toReviewDto(Review review){
        if(review == null){
            return  null;
        }
        ReviewDto reviewDto = new ReviewDto();
        reviewDto.setId(review.getId());
        reviewDto.setInternal_review_count(review.getInternal_review_count());
        reviewDto.setExternal_review_count(review.getExternal_review_count());
        return reviewDto;
    }

    public static Review toReview(ReviewDto reviewDto) {
        if(reviewDto == null) {
            return null;
        }
        Review review = new Review();
        review.setId(reviewDto.getId());
        review.setInternal_review_count(reviewDto.getInternal_review_count());
        review.setExternal_review_count(review.getExternal_review_count());
        return review;
    }
}
