package team.placeholder.internalprojectsmanagementsystem.controller.api;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import team.placeholder.internalprojectsmanagementsystem.service.impl.dashboard.DashboardServiceImpl;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DashboardControllerTest {

    @Mock
    private DashboardServiceImpl dashboardService;

    @InjectMocks
    private DashboardController dashboardController;

    @Test
    public void testGetPlanManHours(){


    }

    @Test
    public void testGetKpi(){

    }

    @Test
    public void testGetProductivity(){

    }

}