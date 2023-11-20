package team.placeholder.internalprojectsmanagementsystem.service.impl.project;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import team.placeholder.internalprojectsmanagementsystem.dto.mapper.project.SystemOutlineMapper;
import team.placeholder.internalprojectsmanagementsystem.dto.model.project.SystemOutLineDto;
import team.placeholder.internalprojectsmanagementsystem.model.project.SystemOutLine;
import team.placeholder.internalprojectsmanagementsystem.repository.project.SystemOutlineRepository;
import team.placeholder.internalprojectsmanagementsystem.service.impl.project.SystemOutlineServiceImpl;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


class SystemOutlineServiceImplTest {

    @Mock
    private SystemOutlineRepository systemOutlineRepository;

    @InjectMocks
    private SystemOutlineServiceImpl systemOutlineService;
    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }
    @Test
    public void testSave() {
        // Arrange
        SystemOutLineDto inputDto = new SystemOutLineDto();
        inputDto.setId(1L);
        SystemOutLine systemOutLine = SystemOutlineMapper.toSystemOutline(inputDto);

        when(systemOutlineRepository.save(systemOutLine)).thenReturn(systemOutLine);

        // Act
        SystemOutLineDto resultDto = systemOutlineService.save(inputDto);

        // Assert
        assertEquals(inputDto, resultDto);

        // Verify that the save method of the repository was called with the expected argument
        verify(systemOutlineRepository, times(1)).save(systemOutLine);
    }

    @Test
    public void testGetAllSystemOutlines() {
        // Arrange
        List<SystemOutLine> systemOutLines = Arrays.asList(new SystemOutLine(), new SystemOutLine());
        when(systemOutlineRepository.findAll()).thenReturn(systemOutLines);

        // Act
        List<SystemOutLineDto> resultDtos = systemOutlineService.getAllSystemOutlines();

        // Assert
        assertEquals(systemOutLines.size(), resultDtos.size());

        // Verify that the findAll method of the repository was called
        verify(systemOutlineRepository, times(1)).findAll();
    }
}