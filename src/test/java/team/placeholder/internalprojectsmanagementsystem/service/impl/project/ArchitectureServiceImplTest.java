package team.placeholder.internalprojectsmanagementsystem.service.impl.project;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import team.placeholder.internalprojectsmanagementsystem.dto.model.project.ArchitectureDto;
import team.placeholder.internalprojectsmanagementsystem.model.project.Architecture;
import team.placeholder.internalprojectsmanagementsystem.repository.project.ArchitectureRepository;
import team.placeholder.internalprojectsmanagementsystem.service.impl.project.ArchitectureServiceImpl;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ArchitectureServiceImplTest {

    @Mock
    private ArchitectureRepository architectureRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private ArchitectureServiceImpl architectureService;

    @Test
    void getAllArchitectures() {
        // Arrange
        // Mock the behavior of the repository
        when(architectureRepository.findAll()).thenReturn(List.of(new Architecture(), new Architecture()));

        // Act
        List<ArchitectureDto> architectureDtos = architectureService.getAllArchitecture();

        // Assert
        assertEquals(2, architectureDtos.size());
        // Add more assertions if needed
    }

    @Test
    void save() {
        // Arrange
        ArchitectureDto architectureDto = new ArchitectureDto();
        Architecture mappedArchitecture = new Architecture();

        // Mock the behavior of the modelMapper
        when(modelMapper.map(architectureDto, Architecture.class)).thenReturn(mappedArchitecture);
        // Mock the behavior of the repository
        when(architectureRepository.save(mappedArchitecture)).thenReturn(mappedArchitecture);

        // Act
        Architecture savedArchitecture = architectureService.save(architectureDto);

        // Assert
        assertNotNull(savedArchitecture);
        // Add more assertions if needed
    }

    @Test
    void findById() {
        // Arrange
        long architectureId = 1L;
        Architecture existingArchitecture = new Architecture();

        // Mock the behavior of the repository
        when(architectureRepository.findById(architectureId)).thenReturn(java.util.Optional.of(existingArchitecture));

        // Act
        Architecture foundArchitecture = architectureService.findById(architectureId);

        // Assert
        assertNotNull(foundArchitecture);
        // Add more assertions if needed
    }
}