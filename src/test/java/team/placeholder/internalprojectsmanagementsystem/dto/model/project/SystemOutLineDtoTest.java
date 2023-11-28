package team.placeholder.internalprojectsmanagementsystem.dto.model.project;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SystemOutLineDtoTest {

    @Test
    public void testGettersAndSetters() {
        SystemOutLineDto dto = new SystemOutLineDto();
        dto.setId(1);
        dto.setAnalysis(true);
        dto.setSys_design(true);
        dto.setCoding(true);
        dto.setTesting(true);
        dto.setDeploy(true);
        dto.setMaintenance(true);
        dto.setDocument(true);

        assertEquals(1, dto.getId());
        assertTrue(dto.isAnalysis());
        assertTrue(dto.isSys_design());
        assertTrue(dto.isCoding());
        assertTrue(dto.isTesting());
        assertTrue(dto.isDeploy());
        assertTrue(dto.isMaintenance());
        assertTrue(dto.isDocument());
    }

    @Test
    public void testSettersAndGetters() {
        SystemOutLineDto dto = new SystemOutLineDto();
        dto.setId(1);
        dto.setAnalysis(false);
        dto.setSys_design(false);
        dto.setCoding(false);
        dto.setTesting(false);
        dto.setDeploy(false);
        dto.setMaintenance(false);
        dto.setDocument(false);

        assertEquals(1, dto.getId());
        assertFalse(dto.isAnalysis());
        assertFalse(dto.isSys_design());
        assertFalse(dto.isCoding());
        assertFalse(dto.isTesting());
        assertFalse(dto.isDeploy());
        assertFalse(dto.isMaintenance());
        assertFalse(dto.isDocument());
    }

    @Test
    public void testToString() {
        SystemOutLineDto systemOutlineDto = new SystemOutLineDto();
        systemOutlineDto.setId(1L);
        systemOutlineDto.setAnalysis(true);
        systemOutlineDto.setSys_design(true);
        systemOutlineDto.setCoding(true);
        systemOutlineDto.setTesting(true);
        systemOutlineDto.setDeploy(true);
        systemOutlineDto.setMaintenance(true);
        systemOutlineDto.setDocument(true);

        assertEquals("SystemOutLineDto [id=1, analysis=true, sys_design=true, coding=true, testing=true, deploy=true, maintenance=true, document=true]", systemOutlineDto.toString());
    }

    @Test
    public void testToStringWithDefaultValues() {
        SystemOutLineDto systemOutlineDto = new SystemOutLineDto();

        assertEquals("SystemOutLineDto [id=0, analysis=false, sys_design=false, coding=false, testing=false, deploy=false, maintenance=false, document=false]", systemOutlineDto.toString());
    }

    @Test
    public void testToStringWithNullValues() {
        SystemOutLineDto systemOutlineDto = new SystemOutLineDto();
        systemOutlineDto.setId(2L);
        systemOutlineDto.setAnalysis(true);
        systemOutlineDto.setSys_design(true);
        systemOutlineDto.setCoding(false);
        systemOutlineDto.setTesting(false);
        systemOutlineDto.setDeploy(false);
        systemOutlineDto.setMaintenance(false);
        systemOutlineDto.setDocument(false);

        assertEquals("SystemOutLineDto [id=2, analysis=true, sys_design=true, coding=false, testing=false, deploy=false, maintenance=false, document=false]", systemOutlineDto.toString());
    }

    @Test
    public void testEqualsAndHashCode() {
        SystemOutLineDto systemOutlineDto1 = new SystemOutLineDto();
        systemOutlineDto1.setId(1L);
        systemOutlineDto1.setAnalysis(true);
        systemOutlineDto1.setSys_design(true);
        systemOutlineDto1.setCoding(true);
        systemOutlineDto1.setTesting(true);
        systemOutlineDto1.setDeploy(true);
        systemOutlineDto1.setMaintenance(true);
        systemOutlineDto1.setDocument(true);

        SystemOutLineDto systemOutlineDto2 = new SystemOutLineDto();
        systemOutlineDto2.setId(1L);
        systemOutlineDto2.setAnalysis(true);
        systemOutlineDto2.setSys_design(true);
        systemOutlineDto2.setCoding(true);
        systemOutlineDto2.setTesting(true);
        systemOutlineDto2.setDeploy(true);
        systemOutlineDto2.setMaintenance(true);
        systemOutlineDto2.setDocument(true);
        assertEquals(systemOutlineDto1, systemOutlineDto2);
        assertEquals(systemOutlineDto1.hashCode(), systemOutlineDto2.hashCode());
    }


    @Test
    public void testNotEquals() {
        SystemOutLineDto systemOutlineDto1 = new SystemOutLineDto();
        systemOutlineDto1.setId(1L);
        systemOutlineDto1.setAnalysis(true);
        systemOutlineDto1.setSys_design(true);
        systemOutlineDto1.setCoding(true);
        systemOutlineDto1.setTesting(true);
        systemOutlineDto1.setDeploy(true);
        systemOutlineDto1.setMaintenance(true);
        systemOutlineDto1.setDocument(true);

        SystemOutLineDto systemOutlineDto2 = new SystemOutLineDto();
        systemOutlineDto2.setId(2L);
        systemOutlineDto2.setAnalysis(false);
        systemOutlineDto2.setSys_design(false);
        systemOutlineDto2.setCoding(false);
        systemOutlineDto2.setTesting(false);
        systemOutlineDto2.setDeploy(false);
        systemOutlineDto2.setMaintenance(false);
        systemOutlineDto2.setDocument(false);

        assertNotEquals(systemOutlineDto1, systemOutlineDto2);
        assertNotEquals(systemOutlineDto1.hashCode(), systemOutlineDto2.hashCode());
    }
}

