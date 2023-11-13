package team.placeholder.internalprojectsmanagementsystem.controller.api;

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

    private  final TaskServiceImpl taskService;

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
