package team.placeholder.internalprojectsmanagementsystem.controller.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import team.placeholder.internalprojectsmanagementsystem.dto.model.project.AmountDto;
import team.placeholder.internalprojectsmanagementsystem.dto.model.project.ProjectDto;
import team.placeholder.internalprojectsmanagementsystem.dto.model.project.ReviewDto;
import team.placeholder.internalprojectsmanagementsystem.dto.uidto.ActualManMonthDto;
import team.placeholder.internalprojectsmanagementsystem.dto.uidto.KPIDto;
import team.placeholder.internalprojectsmanagementsystem.dto.uidto.PlanManMonthDto;
import team.placeholder.internalprojectsmanagementsystem.service.impl.project.ProjectServiceImpl;
import team.placeholder.internalprojectsmanagementsystem.service.impl.project.TaskServiceImpl;

import java.util.List;

@RestController
@Slf4j
public class DashboardController {
    private final ProjectServiceImpl projectService;
    private final TaskServiceImpl taskService;

    public DashboardController(ProjectServiceImpl projectService,TaskServiceImpl taskService) {
        this.projectService = projectService;
        this.taskService = taskService;
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
}
