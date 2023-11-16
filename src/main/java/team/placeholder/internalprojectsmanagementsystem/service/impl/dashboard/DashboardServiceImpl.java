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


//    public List<ActualManMonthDto> getActualManHours(long projectId) {
//        List<TasksDto> tasks = taskRepository.findByProjectId(projectId).stream()
//                .map(task -> modelMapper.map(task, TasksDto.class))
//                .toList();
//
//        List<ActualManMonthDto> actualManMonthDtos = new ArrayList<>();
//
//        for (TasksDto tasksDto : tasks) {
//            long actualStartTime = tasksDto.getActual_start_time();
//            String startMonthYear = ManMonthCalculator.getMonthYearFromDate(actualStartTime);
//
//            // Find the corresponding ActualManMonthDto in the list
//            Optional<ActualManMonthDto> existingDto = actualManMonthDtos.stream()
//                    .filter(dto -> dto.getMonthName().equals(startMonthYear))
//                    .findFirst();
//
//            if (existingDto.isPresent()) {
//                // If the month already exists, update the actual hours
//                double updatedHours = existingDto.get().getActualManMonthHours() + tasksDto.getActual_hours();
//                existingDto.get().setActualManMonthHours(updatedHours);
//            } else {
//                // If the month doesn't exist, create a new ActualManMonthDto
//                ActualManMonthDto newDto = new ActualManMonthDto();
//                newDto.setMonthName(startMonthYear);
//                newDto.setActualManMonthHours(tasksDto.getActual_hours());
//                actualManMonthDtos.add(newDto);
//            }
//        }
//
//        return actualManMonthDtos;
//    }


//    public List<PlanManMonthDto> getPlanManHours(long projectId) {
//        List<TasksDto> tasks = taskRepository.findByProjectId(projectId).stream()
//                .map(task -> modelMapper.map(task, TasksDto.class))
//                .toList();
//
//        List<PlanManMonthDto> planManMonthDtos = new ArrayList<>();
//
//        for (TasksDto tasksDto : tasks){
//            long planStartTIme = tasksDto.getPlan_start_time();
//            String startMonthYear = ManMonthCalculator.getMonthYearFromDate(planStartTIme);
//
//            // Find the corresponding ActualManMonthDto in the list
//            Optional<PlanManMonthDto> existingDto = planManMonthDtos.stream()
//                    .filter(dto -> dto.getMonthName().equals(startMonthYear))
//                    .findFirst();
//
//            double planHours = tasksDto.getPlan_hours() != null ? tasksDto.getPlan_hours() : 0.0;
//
//            if (existingDto.isPresent()) {
//                // If the month already exists, update the actual hours
//                double updatedHours = existingDto.get().getPlanManMonthHours() + planHours;
//                existingDto.get().setPlanManMonthHours(updatedHours);
//            } else {
//                // If the month doesn't exist, create a new ActualManMonthDto
//                PlanManMonthDto newDto = new PlanManMonthDto();
//                newDto.setMonthName(startMonthYear);
//                newDto.setPlanManMonthHours(tasksDto.getPlan_hours());
//                planManMonthDtos.add(newDto);
//            }
//        }
//        return planManMonthDtos;
//    }

    @Override
    public List<ProductivityDto> getProductivity(long projectId) {


        return null;
    }






}
