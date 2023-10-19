package team.placeholder.internalprojectsmanagementsystem.service.project;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import team.placeholder.internalprojectsmanagementsystem.dto.model.project.ProjectStatusDto;

import static org.junit.jupiter.api.Assertions.*;

class ProjectStatusServiceTest {

    @Mock
    private ProjectStatusService projectStatusService;

    @BeforeEach
   public void setUp() {
            MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSaveProjectStatus() {
        ProjectStatusDto projectStatusDto = new ProjectStatusDto();

        Mockito.when(projectStatusService.save(projectStatusDto)).thenReturn(projectStatusDto);
        ProjectStatusDto savedProjectStatus = projectStatusService.save(projectStatusDto);
        assertEquals(projectStatusDto, savedProjectStatus);

    }

    @Test
    public void testUpdateProjectStatus() {
        ProjectStatusDto projectStatusDto = new ProjectStatusDto();

        Mockito.when(projectStatusService.update(projectStatusDto)).thenReturn(projectStatusDto);
        ProjectStatusDto updatedProjectStatus = projectStatusService.update(projectStatusDto);
        assertEquals(projectStatusDto, updatedProjectStatus);
    }

}