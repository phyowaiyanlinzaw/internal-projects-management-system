package team.placeholder.internalprojectsmanagementsystem.service.impl.dashboard;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import team.placeholder.internalprojectsmanagementsystem.dto.model.project.TasksDto;
import team.placeholder.internalprojectsmanagementsystem.dto.uidto.ActualManMonthDto;
import team.placeholder.internalprojectsmanagementsystem.dto.uidto.PlanManMonthDto;
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
    public List<ActualManMonthDto> getActualManHours(long projectId) {
        List<TasksDto> tasks = taskRepository.findByProjectId(projectId).stream()
                .map(task -> modelMapper.map(task, TasksDto.class))
                .toList();

        List<ActualManMonthDto> actualManMonthDtos = new ArrayList<>();

        for (TasksDto tasksDto : tasks) {
            long actualStartTime = tasksDto.getActual_start_time();
            String startMonthYear = ManMonthCalculator.getMonthYearFromDate(actualStartTime);

            // Find the corresponding ActualManMonthDto in the list
            Optional<ActualManMonthDto> existingDto = actualManMonthDtos.stream()
                    .filter(dto -> dto.getMonthName().equals(startMonthYear))
                    .findFirst();

            if (existingDto.isPresent()) {
                // If the month already exists, update the actual hours
                double updatedHours = existingDto.get().getActualManMonthHours() + tasksDto.getActual_hours();
                existingDto.get().setActualManMonthHours(updatedHours);
            } else {
                // If the month doesn't exist, create a new ActualManMonthDto
                ActualManMonthDto newDto = new ActualManMonthDto();
                newDto.setMonthName(startMonthYear);
                newDto.setActualManMonthHours(tasksDto.getActual_hours());
                actualManMonthDtos.add(newDto);
            }
        }

        return actualManMonthDtos;
    }


    @Override
    public List<PlanManMonthDto> getPlanManHours(long projectId) {
        return null;
    }
}
