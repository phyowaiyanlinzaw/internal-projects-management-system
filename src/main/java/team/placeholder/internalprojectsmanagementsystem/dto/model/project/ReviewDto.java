package team.placeholder.internalprojectsmanagementsystem.dto.model.project;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewDto {
    private long id;
    private int internal_review_count,external_review_count;

}
