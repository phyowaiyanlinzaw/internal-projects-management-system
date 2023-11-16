package team.placeholder.internalprojectsmanagementsystem.controller.api;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import team.placeholder.internalprojectsmanagementsystem.dto.model.project.ReviewDto;
import team.placeholder.internalprojectsmanagementsystem.model.project.Review;
import team.placeholder.internalprojectsmanagementsystem.service.FakerService;
import team.placeholder.internalprojectsmanagementsystem.service.impl.project.ReviewServiceImpl;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/review")
public class ReviewController {

    private final ReviewServiceImpl reviewServiceImp;
    private final FakerService fakerService;

    @GetMapping(value = "/list")
    public ResponseEntity<List<ReviewDto>> getAllReview(){
        return ResponseEntity.ok(reviewServiceImp.getAllReviews());
    }

    @PutMapping("/update-review/{id}")
    public ResponseEntity<ReviewDto> updateReview(@PathVariable("id") long id, @RequestBody ReviewDto reviewDto){
        reviewDto.setId(id);
        ReviewDto updateReview = reviewServiceImp.updateReview(reviewDto);
        if (updateReview != null){
            return ResponseEntity.ok(updateReview);
        }else {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }



}
