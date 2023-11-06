package team.placeholder.internalprojectsmanagementsystem.controller.api;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team.placeholder.internalprojectsmanagementsystem.service.FakerService;
import team.placeholder.internalprojectsmanagementsystem.service.impl.project.ReviewServiceImp;

@RestController
@AllArgsConstructor
@RequestMapping("/api/review")
public class ReviewController {

    private final ReviewServiceImp reviewServiceImp;
    private final FakerService fakerService;

    @GetMapping("generate-fake-review/{count}")
    public String generateFakeReview(@PathVariable("count") int count) {
        fakerService.generateAndSaveFakeReview(count);
        return "Fake reviews generated successfully";
    }
}
