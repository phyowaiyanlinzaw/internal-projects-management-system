package team.placeholder.internalprojectsmanagementsystem.service.impl.project;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import team.placeholder.internalprojectsmanagementsystem.dto.mapper.project.ReviewMapper;
import team.placeholder.internalprojectsmanagementsystem.dto.model.project.ReviewDto;
import team.placeholder.internalprojectsmanagementsystem.model.project.Review;
import team.placeholder.internalprojectsmanagementsystem.repository.project.ReviewRepo;

import team.placeholder.internalprojectsmanagementsystem.service.project.ReviewService;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepo reviewRepo;
    private final ModelMapper modelMapper;


    @Override
    public Review save(ReviewDto reviewDto) {
        Review review = new Review();
        review.setInternal_review_count(reviewDto.getInternal_review_count());
        review.setExternal_review_count(reviewDto.getExternal_review_count());
        reviewRepo.save(review);
        return null;
    }



    @Override
    public List<ReviewDto> getAllReviews() {
        List<Review> reviewList = reviewRepo.findAll();
        for (Review review: reviewList){
            System.out.println(review.getId());

        }
        List<ReviewDto> reviewDtos = new ArrayList<>();
        for (Review review: reviewList){
            ReviewDto reviewDto = modelMapper.map(review, ReviewDto.class);
            reviewDtos.add(reviewDto);
        }
        return reviewDtos;

    }



    @Override
    public ReviewDto updateReview(ReviewDto reviewDto) {
            Review review = reviewRepo.findById(reviewDto.getId());
            if (review != null){
                review.setExternal_review_count(reviewDto.getExternal_review_count());
                review.setInternal_review_count(reviewDto.getInternal_review_count());
                reviewRepo.save(review);

                return modelMapper.map(review, ReviewDto.class);
            }else {
                return null;
            }
    }


}
