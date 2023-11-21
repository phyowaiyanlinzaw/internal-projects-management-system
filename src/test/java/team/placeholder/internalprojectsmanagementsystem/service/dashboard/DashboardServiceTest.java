package team.placeholder.internalprojectsmanagementsystem.service.dashboard;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import team.placeholder.internalprojectsmanagementsystem.dto.uidto.KPIDto;
import team.placeholder.internalprojectsmanagementsystem.dto.uidto.ManMonthDto;
import team.placeholder.internalprojectsmanagementsystem.dto.uidto.ProductivityDto;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

class DashboardServiceTest {

    @Mock
    private DashboardService dashboardService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetManMonthWhenCalledWithValidProjectIdThenReturnListOfManMonthDto() {
        List<ManMonthDto> manMonthDtoList = new ArrayList<>();
        manMonthDtoList.add(new ManMonthDto());
        when(dashboardService.getManMonth(anyLong())).thenReturn(manMonthDtoList);

        List<ManMonthDto> result = dashboardService.getManMonth(1L);
        assertEquals(manMonthDtoList.size(), result.size());
    }

    @Test
    void getProductivity() {
        List<ProductivityDto> productivityDtoList = new ArrayList<>();
        productivityDtoList.add(new ProductivityDto());
        when(dashboardService.getProductivity(anyLong())).thenReturn(productivityDtoList);

        List<ProductivityDto> result = dashboardService.getProductivity(1L);
        assertEquals(productivityDtoList.size(), result.size());
    }

    @Test
    void getKPI() {
        KPIDto kpiDto = new KPIDto();
        when(dashboardService.getKPI(anyLong())).thenReturn(kpiDto);

        KPIDto result = dashboardService.getKPI(1L);
        assertEquals(kpiDto, result);
    }


}