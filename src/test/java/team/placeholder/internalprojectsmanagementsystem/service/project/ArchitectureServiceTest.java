package team.placeholder.internalprojectsmanagementsystem.service.project;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import team.placeholder.internalprojectsmanagementsystem.dto.model.project.ArchitectureDto;
import team.placeholder.internalprojectsmanagementsystem.model.project.Architecture;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ArchitectureServiceTest {

    @Mock
    private ArchitectureService architectureService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void getAllArchitecture() {
        List<ArchitectureDto> architectureDtoList = architectureService.getAllArchitecture();
        Mockito.when(architectureService.getAllArchitecture()).thenReturn(architectureDtoList);
       List<ArchitectureDto> architectureDtoList1 = architectureService.getAllArchitecture();
        assertEquals(architectureDtoList, architectureDtoList1);

    }

    @Test
    void save() {
        ArchitectureDto architectureDto = new ArchitectureDto();
        Architecture architecture = new Architecture();
        Mockito.when(architectureService.save(architectureDto)).thenReturn(architecture);
        Architecture saveArchi = architectureService.save(architectureDto);
        assertEquals(architecture, saveArchi);
    }

    @Test
    void findById() {
        long id = 1;
        Architecture architecture = new Architecture();
        Mockito.when(architectureService.findById(id)).thenReturn(architecture);
        Architecture architecture1 = architectureService.findById(id);
        assertEquals(architecture, architecture1);
    }
}