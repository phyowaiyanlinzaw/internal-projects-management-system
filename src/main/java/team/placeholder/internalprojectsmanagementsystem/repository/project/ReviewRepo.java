package team.placeholder.internalprojectsmanagementsystem.repository.project;

import org.springframework.data.jpa.repository.JpaRepository;
import team.placeholder.internalprojectsmanagementsystem.model.project.Review;

import java.util.List;

public interface ReviewRepo extends JpaRepository<Review, Long> {

}
