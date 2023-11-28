package team.placeholder.internalprojectsmanagementsystem.dto.uidto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class KPIDtoTest {

    @Test
    public void testGetterAndSetter() {
        // Create a KPIDto
        KPIDto kpiDto = new KPIDto();
        kpiDto.setReview_kpi(95.5);
        kpiDto.setDetail_kpi(88.0);
        kpiDto.setCoding_kpi(92.5);
        kpiDto.setUnittest_kpi(96.8);
        kpiDto.setIntegratedtest_kpi(90.2);

        // Verify that the getters return the expected values
        assertEquals(95.5, kpiDto.getReview_kpi(), 0.001); // Specify a delta for double comparisons
        assertEquals(88.0, kpiDto.getDetail_kpi(), 0.001);
        assertEquals(92.5, kpiDto.getCoding_kpi(), 0.001);
        assertEquals(96.8, kpiDto.getUnittest_kpi(), 0.001);
        assertEquals(90.2, kpiDto.getIntegratedtest_kpi(), 0.001);

        // Modify some values using setters
        kpiDto.setReview_kpi(85.0);
        kpiDto.setDetail_kpi(90.5);
        kpiDto.setCoding_kpi(88.2);
        kpiDto.setUnittest_kpi(94.3);
        kpiDto.setIntegratedtest_kpi(87.7);

        // Verify that the modified values are reflected
        assertEquals(85.0, kpiDto.getReview_kpi(), 0.001);
        assertEquals(90.5, kpiDto.getDetail_kpi(), 0.001);
        assertEquals(88.2, kpiDto.getCoding_kpi(), 0.001);
        assertEquals(94.3, kpiDto.getUnittest_kpi(), 0.001);
        assertEquals(87.7, kpiDto.getIntegratedtest_kpi(), 0.001);
    }

}