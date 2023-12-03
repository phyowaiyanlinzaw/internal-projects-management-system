package team.placeholder.internalprojectsmanagementsystem.controller.api;

import lombok.extern.slf4j.Slf4j;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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

    @GetMapping(value="/kpi/project/{id}")
    public ResponseEntity<KPIDto> getKpi(@PathVariable long id){
        KPIDto kpiDto = dashboardService.getKPI(id);
        return  ResponseEntity.ok(kpiDto) ;
    }

    @GetMapping("/productivity/project/{projectId}")
    public ResponseEntity<List<ProductivityDto>> getProductivity(
            @PathVariable("projectId") long projectId
    ) {
        return ResponseEntity.ok(dashboardService.getProductivity(projectId));
    }
}
