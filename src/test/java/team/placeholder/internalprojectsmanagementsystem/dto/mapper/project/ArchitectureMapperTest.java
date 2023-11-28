package team.placeholder.internalprojectsmanagementsystem.dto.mapper.project;

import org.junit.jupiter.api.Test;
import team.placeholder.internalprojectsmanagementsystem.dto.model.project.ArchitectureDto;
import team.placeholder.internalprojectsmanagementsystem.model.project.Architecture;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ArchitectureMapperTest {

    @Test
    public void testToArchitectureDto() {
        // Create a sample Architecture object
        Architecture architecture = new Architecture();
        architecture.setId(1L);
        architecture.setTech_name("SampleTech");

        // Use the mapper to convert it to ArchitectureDto
        ArchitectureDto architectureDto = ArchitectureMapper.toArchitectureDto(architecture);

        // Verify that the mapping is correct
        assertEquals(1L, architectureDto.getId());
        assertEquals("SampleTech", architectureDto.getTech_name());
    }

    @Test
    public void testToArchitecture() {
        // Create a sample ArchitectureDto object
        ArchitectureDto architectureDto = new ArchitectureDto();
        architectureDto.setId(1L);
        architectureDto.setTech_name("SampleTech");

        // Use the mapper to convert it to Architecture
        Architecture architecture = ArchitectureMapper.toArchitecture(architectureDto);

        // Verify that the mapping is correct
        assertEquals(1L, architecture.getId());
        assertEquals("SampleTech", architecture.getTech_name());
    }

    @Test
    public void testToArchitectureDtos() {
        // Create a set of sample Architecture objects
        Set<Architecture> architectures = new HashSet<>();
        Architecture architecture1 = new Architecture();
        architecture1.setId(1L);
        architecture1.setTech_name("Tech1");
        architectures.add(architecture1);

        Architecture architecture2 = new Architecture();
        architecture2.setId(2L);
        architecture2.setTech_name("Tech2");
        architectures.add(architecture2);

        // Use the mapper to convert the set to a set of ArchitectureDto
        Set<ArchitectureDto> architectureDtos = ArchitectureMapper.toArchitectureDtos(architectures);

        // Verify that the mapping is correct
        assertEquals(2, architectureDtos.size());
        // Add more assertions if needed
    }

    @Test
    public void testToArchitectures() {
        // Create a set of sample ArchitectureDto objects
        Set<ArchitectureDto> architectureDtos = new HashSet<>();
        ArchitectureDto architectureDto1 = new ArchitectureDto();
        architectureDto1.setId(1L);
        architectureDto1.setTech_name("Tech1");
        architectureDtos.add(architectureDto1);

        ArchitectureDto architectureDto2 = new ArchitectureDto();
        architectureDto2.setId(2L);
        architectureDto2.setTech_name("Tech2");
        architectureDtos.add(architectureDto2);

        // Use the mapper to convert the set to a set of Architecture
        Set<Architecture> architectures = ArchitectureMapper.toArchitectures(architectureDtos);

        // Verify that the mapping is correct
        assertEquals(2, architectures.size());
        // Add more assertions if needed
    }

    //test for null condition
    @Test
    public void testToArchitectureDto_NullInput() {
        // Use the mapper to convert a null Architecture to ArchitectureDto
        ArchitectureDto architectureDto = ArchitectureMapper.toArchitectureDto(null);

        // Verify that the result is also null
        assertNull(architectureDto);
    }

    @Test
    public void testToArchitecture_NullInput() {
        // Use the mapper to convert a null ArchitectureDto to Architecture
        Architecture architecture = ArchitectureMapper.toArchitecture(null);

        // Verify that the result is also null
        assertNull(architecture);
    }

    // Add similar tests for toArchitectureDtos and toArchitectures with null input

    @Test
    public void testToArchitectureDtos_NullSet() {
        // Use the mapper to convert a null set of Architectures to a set of ArchitectureDtos
        Set<ArchitectureDto> architectureDtos = ArchitectureMapper.toArchitectureDtos(null);

        // Verify that the result is also null
        assertNull(architectureDtos);
    }

    @Test
    public void testToArchitectures_NullSet() {
        // Use the mapper to convert a null set of ArchitectureDtos to a set of Architectures
        Set<Architecture> architectures = ArchitectureMapper.toArchitectures(null);

        // Verify that the result is also null
        assertNull(architectures);
    }

}
