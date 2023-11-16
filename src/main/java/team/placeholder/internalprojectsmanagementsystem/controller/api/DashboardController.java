package team.placeholder.internalprojectsmanagementsystem.controller.api;

import lombok.extern.slf4j.Slf4j;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team.placeholder.internalprojectsmanagementsystem.dto.model.project.AmountDto;
import team.placeholder.internalprojectsmanagementsystem.dto.model.project.ProjectDto;
import team.placeholder.internalprojectsmanagementsystem.dto.model.project.ReviewDto;
import team.placeholder.internalprojectsmanagementsystem.dto.uidto.*;
import team.placeholder.internalprojectsmanagementsystem.service.impl.project.ProjectServiceImpl;
import team.placeholder.internalprojectsmanagementsystem.service.impl.dashboard.DashboardServiceImpl;
import team.placeholder.internalprojectsmanagementsystem.service.impl.project.TaskServiceImpl;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/dashboard")
@Slf4j
public class DashboardController {
    private final ProjectServiceImpl projectService;
    private final TaskServiceImpl taskService;

    private final DashboardServiceImpl dashboardService;

    @GetMapping("/man-month/project/{projectId}")
    public ResponseEntity<List<ManMonthDto>> getPlanManHours(
            @PathVariable("projectId") long projectId
    ) {
        return ResponseEntity.ok(dashboardService.getManMonth(projectId));
    }

    @GetMapping(value="/getKPI/{id}")
    public ResponseEntity<KPIDto> getKpi(@PathVariable long id){
        KPIDto kpiDto = projectService.getKPI(id);
        log.info("review kpi " + kpiDto.getReview_kpi());
        log.info("detail kpi " + kpiDto.getDetail_kpi());
        log.info("coding kpi " + kpiDto.getCoding_kpi());
        log.info("unit test kpi " + kpiDto.getUnit_test_kpi());
        log.info("integrated test kpi " + kpiDto.getIntegrated_test_kpi());
        return  ResponseEntity.ok(kpiDto) ;
    }



    @GetMapping("/productivity/project/{projectId}")
    public ResponseEntity<List<ProductivityDto>> getProductivity(
            @PathVariable("projectId") long projectId
    ) {
        return ResponseEntity.ok(dashboardService.getProductivity(projectId));
    }
}
