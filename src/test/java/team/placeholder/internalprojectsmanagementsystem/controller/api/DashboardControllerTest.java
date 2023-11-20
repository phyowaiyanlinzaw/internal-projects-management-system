package team.placeholder.internalprojectsmanagementsystem.controller.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import team.placeholder.internalprojectsmanagementsystem.dto.uidto.KPIDto;
import team.placeholder.internalprojectsmanagementsystem.dto.uidto.ManMonthDto;
import team.placeholder.internalprojectsmanagementsystem.dto.uidto.ProductivityDto;
import team.placeholder.internalprojectsmanagementsystem.service.impl.dashboard.DashboardServiceImpl;
import team.placeholder.internalprojectsmanagementsystem.service.impl.project.ProjectServiceImpl;
import team.placeholder.internalprojectsmanagementsystem.service.impl.project.TaskServiceImpl;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class DashboardControllerTest {

    @Mock
    private DashboardServiceImpl dashboardService;

    @Mock
    private ProjectServiceImpl projectService;

    @Mock
    private TaskServiceImpl taskService;

    @InjectMocks
    private DashboardController dashboardController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetPlanManHours(){
        long projectId = 1L;
        List<ManMonthDto> manMonthDtos = new ArrayList<>();

        when(dashboardService.getManMonth(projectId)).thenReturn(manMonthDtos);

        ResponseEntity<List<ManMonthDto>> responseEntity = dashboardController.getPlanManHours(projectId);

        assertEquals(ResponseEntity.ok(manMonthDtos), responseEntity);

    }

    @Test
    public void testGetKpi(){
        long projectId = 1L;
        KPIDto kpiDto = new KPIDto();

        when(dashboardService.getKPI(projectId)).thenReturn(kpiDto);

        ResponseEntity<KPIDto> responseEntity = dashboardController.getKpi(projectId);

        assertEquals(ResponseEntity.ok(kpiDto), responseEntity);
        verify(dashboardService, times(1)).getKPI(projectId);
    }

    @Test
    public void testGetProductivity(){
        long projectId = 1L;
        List<ProductivityDto> productivityDtos = new ArrayList<>();

        when(dashboardService.getProductivity(projectId)).thenReturn(productivityDtos);

        ResponseEntity<List<ProductivityDto>> responseEntity = dashboardController.getProductivity(projectId);

        assertEquals(ResponseEntity.ok(productivityDtos), responseEntity);
    }

}