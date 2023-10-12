package team.placeholder.internalprojectsmanagementsystem.dto.model.project;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ArchitectureDtoTest {
    @Mock
    private  ArchitectureDto architectureDto;

    @Test
    public void testArchitectureDto() {
        // Create an ArchitectureDto object with some mock data
        architectureDto = new ArchitectureDto();
        architectureDto.setId(1L);
        architectureDto.setTech_name("Sample Technology");

        // Test the getter methods
        assertEquals(1L, architectureDto.getId());
        assertEquals("Sample Technology", architectureDto.getTech_name());
    }
}

