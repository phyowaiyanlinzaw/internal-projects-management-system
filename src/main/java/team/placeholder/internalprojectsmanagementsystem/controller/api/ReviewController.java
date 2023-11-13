package team.placeholder.internalprojectsmanagementsystem.controller.api;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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



}
