package team.placeholder.internalprojectsmanagementsystem.dto.uidto;

import lombok.Getter;
import lombok.Setter;
import team.placeholder.internalprojectsmanagementsystem.dto.model.project.AmountDto;
import team.placeholder.internalprojectsmanagementsystem.dto.model.project.ArchitectureDto;
import team.placeholder.internalprojectsmanagementsystem.dto.model.project.ProjectDto;
import team.placeholder.internalprojectsmanagementsystem.dto.model.project.SystemOutLineDto;
import team.placeholder.internalprojectsmanagementsystem.model.project.*;

import java.util.List;

@Getter
@Setter
public class PrjDto extends ProjectDto {

    // ONLY USE THIS OJBECT WHEN SEND DATA FROM BACK TO FRONT
    // FOR PROJECT LIST

    // review count
    private int internalReviews;
    private int exReviexternalReviewsews;
    // review count

    // other objects that are related to project
    private AmountDto amount;
    private SystemOutLineDto systemOutLine;
    private List<ArchitectureDto> architecture;
    private Deliverable deliverable; // not sure
    // other objects that are related to project

    // to calcuate the percentage in front-end
    private int totalTasks;
    private int doneTasks;
    // to calcuate the percentage in front-end

}
