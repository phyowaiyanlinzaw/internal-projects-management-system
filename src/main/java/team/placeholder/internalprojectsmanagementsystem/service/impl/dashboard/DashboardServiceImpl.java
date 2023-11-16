package team.placeholder.internalprojectsmanagementsystem.service.impl.dashboard;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import team.placeholder.internalprojectsmanagementsystem.dto.model.project.TasksDto;
import team.placeholder.internalprojectsmanagementsystem.dto.uidto.ActualManMonthDto;
import team.placeholder.internalprojectsmanagementsystem.dto.uidto.ManMonthDto;
import team.placeholder.internalprojectsmanagementsystem.dto.uidto.PlanManMonthDto;
import team.placeholder.internalprojectsmanagementsystem.dto.uidto.ProductivityDto;
import team.placeholder.internalprojectsmanagementsystem.model.project.Tasks;
import team.placeholder.internalprojectsmanagementsystem.repository.project.ProjectRepository;
import team.placeholder.internalprojectsmanagementsystem.repository.project.TaskRepository;
import team.placeholder.internalprojectsmanagementsystem.service.dashboard.DashboardService;
import team.placeholder.internalprojectsmanagementsystem.util.ManMonthCalculator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.SimpleFormatter;
import java.util.stream.Collectors;

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
                double productivityRatio = actualManMonthHours / planManMonthHours;

                ProductivityDto newDto = new ProductivityDto();
                newDto.setMonthName(manMonthDto.getMonthName());
                newDto.setProductivityRatio(productivityRatio);
                productivityDtos.add(newDto);
            }
        }

        return productivityDtos;
    }






}
