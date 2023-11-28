package team.placeholder.internalprojectsmanagementsystem.dto.mapper.project;

import org.junit.jupiter.api.Test;
import team.placeholder.internalprojectsmanagementsystem.dto.model.project.SystemOutLineDto;
import team.placeholder.internalprojectsmanagementsystem.model.project.SystemOutLine;

import java.util.List;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class SystemOutlineMapperTest {

    @Test
    public void testToSystemOutLineDto() {
        // Create a sample SystemOutLine
        SystemOutLine systemOutLine = new SystemOutLine();
        systemOutLine.setId(1L);
        systemOutLine.setAnalysis(true);
        systemOutLine.setSys_design(true);
        systemOutLine.setCoding(true);
        systemOutLine.setTesting(true);
        systemOutLine.setDeploy(true);
        systemOutLine.setMaintenance(true);
        systemOutLine.setDocument(true);

        // Use the mapper to convert the sample SystemOutLine to SystemOutLineDto
        SystemOutLineDto systemOutLineDto = SystemOutlineMapper.toSystemOutLineDto(systemOutLine);

        // Verify that the mapping is correct
        assertEquals(1L, systemOutLineDto.getId());
        assertTrue(systemOutLineDto.isAnalysis());
        assertTrue(systemOutLineDto.isSys_design());
        assertTrue(systemOutLineDto.isCoding());
        assertTrue(systemOutLineDto.isTesting());
        assertTrue(systemOutLineDto.isDeploy());
        assertTrue(systemOutLineDto.isMaintenance());
        assertTrue(systemOutLineDto.isDocument());
    }

    @Test
    public void testToSystemOutLineDto_NullInput() {
        // Use the mapper to convert a null SystemOutLine to SystemOutLineDto
        SystemOutLineDto systemOutLineDto = SystemOutlineMapper.toSystemOutLineDto(null);

        // Verify that the result is also null
        assertNull(systemOutLineDto);
    }

    @Test
    public void testToSystemOutline() {
        // Create a sample SystemOutLineDto
        SystemOutLineDto systemOutLineDto = new SystemOutLineDto();
        systemOutLineDto.setId(1L);
        systemOutLineDto.setAnalysis(true);
        systemOutLineDto.setSys_design(true);
        systemOutLineDto.setCoding(true);
        systemOutLineDto.setTesting(true);
        systemOutLineDto.setDeploy(true);
        systemOutLineDto.setMaintenance(true);
        systemOutLineDto.setDocument(true);

        // Use the mapper to convert the sample SystemOutLineDto to SystemOutLine
        SystemOutLine systemOutLine = SystemOutlineMapper.toSystemOutline(systemOutLineDto);

        // Verify that the mapping is correct
        assertEquals(1L, systemOutLine.getId());
        assertTrue(systemOutLine.isAnalysis());
        assertTrue(systemOutLine.isSys_design());
        assertTrue(systemOutLine.isCoding());
        assertTrue(systemOutLine.isTesting());
        assertTrue(systemOutLine.isDeploy());
        assertTrue(systemOutLine.isMaintenance());
        assertTrue(systemOutLine.isDocument());
    }

    @Test
    public void testToSystemOutline_NullInput() {
        // Use the mapper to convert a null SystemOutLineDto to SystemOutLine
        SystemOutLine systemOutLine = SystemOutlineMapper.toSystemOutline(null);

        // Verify that the result is also null
        assertNull(systemOutLine);
    }

    @Test
    public void testTosystemOutLineDtos() {
        // Create a list of sample SystemOutLines
        List<SystemOutLine> systemOutLines = Arrays.asList(
                createSystemOutLine(1L, true, true, true, true, true, true, true),
                createSystemOutLine(2L, false, true, false, true, false, true, false)
        );

        // Use the mapper to convert the list to a list of SystemOutLineDtos
        List<SystemOutLineDto> systemOutLineDtos = SystemOutlineMapper.tosystemOutLineDtos(systemOutLines);

        // Verify that the mapping is correct for each element
        assertEquals(2, systemOutLineDtos.size());
        assertEquals(1L, systemOutLineDtos.get(0).getId());
        assertTrue(systemOutLineDtos.get(0).isAnalysis());
        assertTrue(systemOutLineDtos.get(0).isSys_design());
        assertTrue(systemOutLineDtos.get(0).isCoding());
        assertTrue(systemOutLineDtos.get(0).isTesting());
        assertTrue(systemOutLineDtos.get(0).isDeploy());
        assertTrue(systemOutLineDtos.get(0).isMaintenance());
        assertTrue(systemOutLineDtos.get(0).isDocument());

        assertEquals(2L, systemOutLineDtos.get(1).getId());
        assertFalse(systemOutLineDtos.get(1).isAnalysis());
        assertTrue(systemOutLineDtos.get(1).isSys_design());
        assertFalse(systemOutLineDtos.get(1).isCoding());
        assertTrue(systemOutLineDtos.get(1).isTesting());
        assertFalse(systemOutLineDtos.get(1).isDeploy());
        assertTrue(systemOutLineDtos.get(1).isMaintenance());
        assertFalse(systemOutLineDtos.get(1).isDocument());
    }

    @Test
    public void testTosystemOutLines() {
        // Create a list of sample SystemOutLineDtos
        List<SystemOutLineDto> systemOutLineDtos = Arrays.asList(
                createSystemOutLineDto(1L, true, true, true, true, true, true, true),
                createSystemOutLineDto(2L, false, true, false, true, false, true, false)
        );

        // Use the mapper to convert the list to a list of SystemOutLines
        List<SystemOutLine> systemOutLines = SystemOutlineMapper.tosystemOutLines(systemOutLineDtos);

        // Verify that the mapping is correct for each element
        assertEquals(2, systemOutLines.size());
        assertEquals(1L, systemOutLines.get(0).getId());
        assertTrue(systemOutLines.get(0).isAnalysis());
        assertTrue(systemOutLines.get(0).isSys_design());
        assertTrue(systemOutLines.get(0).isCoding());
        assertTrue(systemOutLines.get(0).isTesting());
        assertTrue(systemOutLines.get(0).isDeploy());
        assertTrue(systemOutLines.get(0).isMaintenance());
        assertTrue(systemOutLines.get(0).isDocument());

        assertEquals(2L, systemOutLines.get(1).getId());
        assertFalse(systemOutLines.get(1).isAnalysis());
        assertTrue(systemOutLines.get(1).isSys_design());
        assertFalse(systemOutLines.get(1).isCoding());
        assertTrue(systemOutLines.get(1).isTesting());
        assertFalse(systemOutLines.get(1).isDeploy());
        assertTrue(systemOutLines.get(1).isMaintenance());
        assertFalse(systemOutLines.get(1).isDocument());
    }

    // Helper methods to create sample SystemOutLine and SystemOutLineDto instances
    private SystemOutLine createSystemOutLine(long id, boolean analysis, boolean sysDesign, boolean coding,
                                              boolean testing, boolean deploy, boolean maintenance, boolean document) {
        SystemOutLine systemOutLine = new SystemOutLine();
        systemOutLine.setId(id);
        systemOutLine.setAnalysis(analysis);
        systemOutLine.setSys_design(sysDesign);
        systemOutLine.setCoding(coding);
        systemOutLine.setTesting(testing);
        systemOutLine.setDeploy(deploy);
        systemOutLine.setMaintenance(maintenance);
        systemOutLine.setDocument(document);
        return systemOutLine;
    }

    private SystemOutLineDto createSystemOutLineDto(long id, boolean analysis, boolean sysDesign, boolean coding,
                                                    boolean testing, boolean deploy, boolean maintenance, boolean document) {
        SystemOutLineDto systemOutLineDto = new SystemOutLineDto();
        systemOutLineDto.setId(id);
        systemOutLineDto.setAnalysis(analysis);
        systemOutLineDto.setSys_design(sysDesign);
        systemOutLineDto.setCoding(coding);
        systemOutLineDto.setTesting(testing);
        systemOutLineDto.setDeploy(deploy);
        systemOutLineDto.setMaintenance(maintenance);
        systemOutLineDto.setDocument(document);
        return systemOutLineDto;
    }


}