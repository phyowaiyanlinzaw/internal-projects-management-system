package team.placeholder.internalprojectsmanagementsystem.service.dashboard;

import team.placeholder.internalprojectsmanagementsystem.dto.model.project.TasksDto;
import team.placeholder.internalprojectsmanagementsystem.dto.uidto.*;

import java.util.List;

public interface DashboardService {

    List<ManMonthDto> getManMonth(long projectId);

    List<ProductivityDto> getProductivity(long projectId);

    KPIDto getKPI(long id);
}
