//package team.placeholder.internalprojectsmanagementsystem.service.impl.project;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.boot.test.context.SpringBootTest;
//import team.placeholder.internalprojectsmanagementsystem.dto.mapper.project.ArchitectureMapper;
//import team.placeholder.internalprojectsmanagementsystem.dto.model.project.ArchitectureDto;
//import team.placeholder.internalprojectsmanagementsystem.model.project.Architecture;
//import team.placeholder.internalprojectsmanagementsystem.repository.project.ArchitectureRepository;
//
//import java.util.Arrays;
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//
//class ArchitectureServiceImplTest {
//
//    @Mock
//    ArchitectureRepository architectureRepository;
//
//    @InjectMocks
//    private ArchitectureServiceImpl architectureService;
//
//    @BeforeEach
//    public void setUp(){
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    public void testGetAllArchitecture(){
//        Architecture architecture1 = new Architecture();
//        architecture1.setId(1L);
//        architecture1.setTech_name("Architecture 1");
//
//        Architecture architecture2 = new Architecture();
//        architecture2.setId(2L);
//        architecture2.setTech_name("Architecture 2");
//
//        when(architectureRepository.findAll()).thenReturn(Arrays.asList(architecture1, architecture2));
//
//        ArchitectureServiceImpl architectureService = new ArchitectureServiceImpl(architectureRepository);
//
//        List<ArchitectureDto> architectureDtoList = architectureService.getAllArchitecture();
//
//        assertEquals(2, architectureDtoList.size());
//        assertEquals("Architecture 1", architectureDtoList.get(0).getTech_name());
//        assertEquals(1L, architectureDtoList.get(0).getId());
//        assertEquals("Architecture 2", architectureDtoList.get(1).getTech_name());
//        assertEquals(2L, architectureDtoList.get(1).getId());
//    }
//
//    @Test
//    public void testFindById() {
//        Architecture architecture = new Architecture();
//        architecture.setId(1L);
//        architecture.setTech_name("Test Architecture");
//
//        when(architectureRepository.findById(1L)).thenReturn(Optional.of(architecture));
//        when(architectureRepository.findById(2L)).thenReturn(Optional.empty());
//
//        ArchitectureServiceImpl architectureService = new ArchitectureServiceImpl(architectureRepository);
//
//        Architecture foundArchitecture = architectureService.findById(1L);
//        Architecture nonExistentArchitecture = architectureService.findById(2L);
//
//        assertNotNull(foundArchitecture);
//        assertEquals(1L, foundArchitecture.getId());
//        assertEquals("Test Architecture", foundArchitecture.getTech_name());
//
//        assertNull(nonExistentArchitecture);
//    }
//
//    @Test
//    public void testSave(){
//        ArchitectureDto architectureDto = new ArchitectureDto();
//        architectureDto.setTech_name("HTML");
//
//        Architecture expectedArchitecture = ArchitectureMapper.toArchitecture(architectureDto);
//
//        when(architectureRepository.save(any())).thenReturn(expectedArchitecture);
//
//        // Act
//        Architecture result = architectureService.save(architectureDto);
//
//        // Assert
//        verify(architectureRepository, times(1)).save(any());
//        assertEquals(expectedArchitecture, result);
//    }
//
//
//
//
//}