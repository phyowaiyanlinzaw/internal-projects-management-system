package team.placeholder.internalprojectsmanagementsystem.dto.model.project;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SystemOutLineDtoTest {
    @Mock
    private SystemOutLineDto systemOutLineDto;

    @Test
    public void testSystemOutLineDto() {
        // Create a SystemOutLineDto object with some mock data
        systemOutLineDto = new SystemOutLineDto();
        systemOutLineDto.setId(1L);
        systemOutLineDto.setAnalysis(true);
        systemOutLineDto.setSys_design(true);
        systemOutLineDto.setCoding(false);
        systemOutLineDto.setTesting(true);
        systemOutLineDto.setDeploy(false);
        systemOutLineDto.setMaintenance(true);
        systemOutLineDto.setDocument(false);

        // Test the getter methods
        assertEquals(1L, systemOutLineDto.getId());
        assertTrue(systemOutLineDto.isAnalysis());
        assertTrue(systemOutLineDto.isSys_design());
        assertEquals(false, systemOutLineDto.isCoding());
        assertTrue(systemOutLineDto.isTesting());
        assertEquals(false, systemOutLineDto.isDeploy());
        assertTrue(systemOutLineDto.isMaintenance());
        assertEquals(false, systemOutLineDto.isDocument());
    }
}

