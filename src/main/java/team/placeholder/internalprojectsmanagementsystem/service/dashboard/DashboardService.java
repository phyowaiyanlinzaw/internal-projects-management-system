package team.placeholder.internalprojectsmanagementsystem.service.dashboard;

import team.placeholder.internalprojectsmanagementsystem.dto.model.project.TasksDto;
import team.placeholder.internalprojectsmanagementsystem.dto.uidto.ActualManMonthDto;
import team.placeholder.internalprojectsmanagementsystem.dto.uidto.ManMonthDto;
import team.placeholder.internalprojectsmanagementsystem.dto.uidto.PlanManMonthDto;
import team.placeholder.internalprojectsmanagementsystem.dto.uidto.ProductivityDto;

import java.util.List;

public interface DashboardService {

//    List<ActualManMonthDto> getActualManHours(long projectId);
//
//    List<PlanManMonthDto> getPlanManHours(long projectId);

    List<ManMonthDto> getManMonth(long projectId);

    List<ProductivityDto> getProductivity(long projectId);
}
