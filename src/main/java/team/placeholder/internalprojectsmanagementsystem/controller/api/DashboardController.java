package team.placeholder.internalprojectsmanagementsystem.controller.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import team.placeholder.internalprojectsmanagementsystem.dto.model.project.AmountDto;
import team.placeholder.internalprojectsmanagementsystem.dto.model.project.ProjectDto;
import team.placeholder.internalprojectsmanagementsystem.dto.model.project.ReviewDto;
import team.placeholder.internalprojectsmanagementsystem.model.project.Project;
import team.placeholder.internalprojectsmanagementsystem.model.project.Review;
import team.placeholder.internalprojectsmanagementsystem.service.impl.project.ProjectServiceImpl;
import team.placeholder.internalprojectsmanagementsystem.service.impl.project.TaskServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.logging.Logger;

import static org.hibernate.query.sqm.tree.SqmNode.log;

@RestController
@RequiredArgsConstructor
@Slf4j
public class DashboardController {
    private final ProjectServiceImpl projectService;
    private final TaskServiceImpl taskService;

    @GetMapping(value="/getKPI/{id}")
    public int getKpi(@PathVariable long id){

        ProjectDto project = projectService.getProjectById(id);
        AmountDto amount = project.getAmountDto();
        ReviewDto review = project.getReviewDto();

        int internal = review.getInternal_review_count();
        int external = review.getExternal_review_count();

        int review_count = internal + external;

        int basic_design = amount.getBasic_design();
        int detail_design = amount.getDetail_design();
        int coding = amount.getCoding();
        int unit_testing = amount.getUnit_testing();
        int integerated_testing = amount.getIntegrated_testing();

        int review_kpi = projectService.getKPI(basic_design,review_count);
        int detail_kpi = projectService.getKPI(detail_design,review_count);
        int coding_kpi = projectService.getKPI(coding,review_count);
        int unit_test_kpi = projectService.getKPI(unit_testing,review_count);
        int integraterd_test_lpi = projectService.getKPI(integerated_testing,review_count);

        return  0 ;
    }
}
