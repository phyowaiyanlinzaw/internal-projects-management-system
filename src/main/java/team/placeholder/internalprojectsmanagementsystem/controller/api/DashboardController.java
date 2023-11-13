package team.placeholder.internalprojectsmanagementsystem.controller.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team.placeholder.internalprojectsmanagementsystem.dto.uidto.ActualManMonthDto;
import team.placeholder.internalprojectsmanagementsystem.dto.uidto.PlanManMonthDto;
import team.placeholder.internalprojectsmanagementsystem.dto.uidto.ProductivityDto;
import team.placeholder.internalprojectsmanagementsystem.service.impl.project.TaskServiceImpl;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/dashboard")
public class DashboardController {

    private final TaskServiceImpl taskService;

    @GetMapping("/actual-man-month/project/{projectId}")
    public ResponseEntity<List<ActualManMonthDto>> getMonthlyManMonth(
            @PathVariable("projectId") long projectId
    ) {
        return ResponseEntity.ok(taskService.calculateMonthlyActualManHoursFromTasks(projectId));
    }

    @GetMapping("/plan-man-month/project/{projectId}")
    public ResponseEntity<List<PlanManMonthDto>> getMonthlyPlanManMonth(
            @PathVariable("projectId") long projectId
    ) {
        return ResponseEntity.ok(taskService.calculateMonthlyPlanManHoursFromTasks(projectId));
    }

    @GetMapping("/productivity/project/{projectId}")
    public ResponseEntity<List<ProductivityDto>> getProductivity(
            @PathVariable("projectId") long projectId
    ) {
        return null;
    }
}
