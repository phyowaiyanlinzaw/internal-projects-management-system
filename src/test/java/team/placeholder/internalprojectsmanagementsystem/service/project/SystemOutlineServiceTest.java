package team.placeholder.internalprojectsmanagementsystem.service.project;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import team.placeholder.internalprojectsmanagementsystem.dto.model.project.SystemOutLineDto;

import static org.junit.jupiter.api.Assertions.*;

class SystemOutlineServiceTest {

    @Mock
    private SystemOutlineService systemOutlineService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    void save() {
        SystemOutLineDto systemOutlineDto = new SystemOutLineDto();
        Mockito.when(systemOutlineService.save(systemOutlineDto)).thenReturn(systemOutlineDto);
        SystemOutLineDto result = systemOutlineService.save(systemOutlineDto);
        assertEquals(systemOutlineDto, result);

    }

    @Test
    void getAllSystemOutlines() {
        SystemOutLineDto systemOutlineDto = new SystemOutLineDto();
        Mockito.when(systemOutlineService.save(systemOutlineDto)).thenReturn(systemOutlineDto);
        SystemOutLineDto result = systemOutlineService.save(systemOutlineDto);
        assertEquals(systemOutlineDto, result);
    }
}