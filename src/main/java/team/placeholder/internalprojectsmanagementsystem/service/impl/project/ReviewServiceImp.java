package team.placeholder.internalprojectsmanagementsystem.service.impl.project;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import team.placeholder.internalprojectsmanagementsystem.dto.model.project.ReviewDto;
import team.placeholder.internalprojectsmanagementsystem.model.project.Review;
import team.placeholder.internalprojectsmanagementsystem.repository.project.ReviewRepo;
import team.placeholder.internalprojectsmanagementsystem.service.project.ReviewService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewServiceImp implements ReviewService {

    private final ReviewRepo reviewRepo;

    @Override
    public Review save(ReviewDto reviewDto) {
        return null;
    }

    @Override
    public ReviewDto update(ReviewDto reviewDto) {
        return null;
    }

    @Override
    public void deleteReview(ReviewDto reviewDto) {

    }

    @Override
    public List<ReviewDto> getAllReviews() {
        return null;
    }

    @Override
    public ReviewDto getRevieweById(long id) {
        return null;
    }
}
