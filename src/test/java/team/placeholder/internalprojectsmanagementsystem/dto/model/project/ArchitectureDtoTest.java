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
    @Test
    public void testToStringWithIdAndTechName() {
        ArchitectureDto architectureDto = new ArchitectureDto();
        architectureDto.setId(1L);
        architectureDto.setTech_name("TestTech");

        assertEquals("ArchitectureDto{id=1, tech_name='TestTech'}", architectureDto.toString());
    }

    @Test
    public void testToStringWithNullIdAndTechName() {
        ArchitectureDto architectureDto = new ArchitectureDto();
        architectureDto.setId(null);
        architectureDto.setTech_name(null);

        assertEquals("ArchitectureDto{id=null, tech_name='null'}", architectureDto.toString());
    }

    @Test
    public void testToStringWithNullId() {
        ArchitectureDto architectureDto = new ArchitectureDto();
        architectureDto.setId(null);
        architectureDto.setTech_name("TestTech");

        assertEquals("ArchitectureDto{id=null, tech_name='TestTech'}", architectureDto.toString());
    }

    @Test
    public void testToStringWithNullTechName() {
        ArchitectureDto architectureDto = new ArchitectureDto();
        architectureDto.setId(1L);
        architectureDto.setTech_name(null);

        assertEquals("ArchitectureDto{id=1, tech_name='null'}", architectureDto.toString());
    }

    @Test
    public void testToStringWithEmptyTechName() {
        ArchitectureDto architectureDto = new ArchitectureDto();
        architectureDto.setId(1L);
        architectureDto.setTech_name("");

        assertEquals("ArchitectureDto{id=1, tech_name=''}", architectureDto.toString());
    }

    @Test
    public void testToStringWithSpecialCharactersInTechName() {
        ArchitectureDto architectureDto = new ArchitectureDto();
        architectureDto.setId(1L);
        architectureDto.setTech_name("Special#Tech");

        assertEquals("ArchitectureDto{id=1, tech_name='Special#Tech'}", architectureDto.toString());
    }
}

