package team.placeholder.internalprojectsmanagementsystem.service.impl.dashboard;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import team.placeholder.internalprojectsmanagementsystem.dto.model.project.AmountDto;
import team.placeholder.internalprojectsmanagementsystem.dto.model.project.ProjectDto;
import team.placeholder.internalprojectsmanagementsystem.dto.model.project.ReviewDto;
import team.placeholder.internalprojectsmanagementsystem.dto.model.project.TasksDto;
import team.placeholder.internalprojectsmanagementsystem.dto.uidto.*;
import team.placeholder.internalprojectsmanagementsystem.model.project.Project;
import team.placeholder.internalprojectsmanagementsystem.repository.project.ProjectRepository;
import team.placeholder.internalprojectsmanagementsystem.repository.project.TaskRepository;
import team.placeholder.internalprojectsmanagementsystem.service.dashboard.DashboardService;
import team.placeholder.internalprojectsmanagementsystem.util.ManMonthCalculator;

import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class DashboardServiceImpl implements DashboardService {

    private final ProjectRepository projectRepository;
    private final TaskRepository taskRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<ManMonthDto> getManMonth(long projectId) {
        List<TasksDto> tasks = taskRepository.findByProjectId(projectId).stream()
                .map(task -> modelMapper.map(task, TasksDto.class))
                .toList();

        List<ManMonthDto> manMonthDtos = new ArrayList<>();
        for (TasksDto taskDto: tasks){
            long startTime = taskDto.getPlan_start_time();
            String startMonthYear = ManMonthCalculator.getMonthYearFromDate(startTime);

            Optional<ManMonthDto> existingDto = manMonthDtos.stream()
                    .filter(dto -> dto.getMonthName().equals(startMonthYear))
                    .findFirst();

            double planHours = taskDto.getPlan_hours() != null ? taskDto.getPlan_hours() : 0.0;
            double actualHours = taskDto.getActual_hours() != null ? taskDto.getActual_hours() : 0.0;

            if (existingDto.isPresent()){
                double updatedPlanHours = existingDto.get().getPlanManMonthHours() + planHours;
                double updatedActualHours = existingDto.get().getActualManMonthHours() + actualHours;
                existingDto.get().setPlanManMonthHours(updatedPlanHours);
                existingDto.get().setActualManMonthHours(updatedActualHours);
            }else{
                ManMonthDto newDto = new ManMonthDto();
                newDto.setMonthName(startMonthYear);
                newDto.setPlanManMonthHours(taskDto.getPlan_hours());
                newDto.setActualManMonthHours(taskDto.getActual_hours());
                manMonthDtos.add(newDto);
            }
        }
        return manMonthDtos;
    }

    @Override
    public List<ProductivityDto> getProductivity(long projectId) {
        List<ManMonthDto> manMonthDtos = getManMonth(projectId);
        List<ProductivityDto> productivityDtos = new ArrayList<>();

        for (ManMonthDto manMonthDto : manMonthDtos) {
            double actualManMonthHours = manMonthDto.getActualManMonthHours() != null ? manMonthDto.getActualManMonthHours() : 0.0;
            double planManMonthHours = manMonthDto.getPlanManMonthHours() != null ? manMonthDto.getPlanManMonthHours() : 0.0;

            if (planManMonthHours != 0.0) {
                double productivityRatio = planManMonthHours / actualManMonthHours;

                ProductivityDto newDto = new ProductivityDto();
                newDto.setMonthName(manMonthDto.getMonthName());
                newDto.setProductivityRatio(productivityRatio);
                productivityDtos.add(newDto);
            }
        }

        return productivityDtos;
    }

    @Override
    public KPIDto getKPI(long id) {
        Project project = projectRepository.findById(id);
        KPIDto kpiDto = new KPIDto();

        if (project!=null){
                ProjectDto projectDto = modelMapper.map(project, ProjectDto.class);
                projectDto.setAmountDto(modelMapper.map(project.getAmount(), AmountDto.class));
                projectDto.setReviewDto(modelMapper.map(project.getReviews(), ReviewDto.class));

                AmountDto amount = projectDto.getAmountDto();
                ReviewDto review = projectDto.getReviewDto();

                int internal = review.getInternal_review_count();
                int external = review.getExternal_review_count();
                int review_count = internal + external;

                int basic_design = amount.getBasic_design();
                int detail_design = amount.getDetail_design();
                int coding = amount.getCoding();
                int unit_testing = amount.getUnit_testing();
                int integrated_testing = amount.getIntegrated_testing();

                double review_kpi = calculateKPI(basic_design,review_count);
                double detail_kpi = calculateKPI(detail_design,review_count);
                double coding_kpi = calculateKPI(coding,review_count);
                double unit_test_kpi = calculateKPI(unit_testing,review_count);
                double integrated_test_kpi = calculateKPI(integrated_testing,review_count);

                kpiDto.setReview_kpi(review_kpi);
                kpiDto.setDetail_kpi(detail_kpi);
                kpiDto.setCoding_kpi(coding_kpi);
                kpiDto.setUnittest_kpi(unit_test_kpi);
                kpiDto.setIntegratedtest_kpi(integrated_test_kpi);
            }


        return kpiDto;
    }

    public int calculateKPI(int phase, int review_count){
        return phase / review_count;
    }






}
