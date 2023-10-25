package team.placeholder.internalprojectsmanagementsystem.service.impl.project;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import team.placeholder.internalprojectsmanagementsystem.repository.project.ProjectStatusRepository;

import static org.mockito.Mockito.*;
import static team.placeholder.internalprojectsmanagementsystem.model.project.projectenums.TaskStatus.IN_PROGRESS;


@ExtendWith(MockitoExtension.class)
class ProjectTaskStatusServiceImplTest {

    @Mock
    private ProjectStatusRepository projectStatusRepository;

    @InjectMocks
    private ProjectStatusServiceImpl projectStatusService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSaveProjectStatus() {
        ProjectStatus projectStatus = new ProjectStatus();
        projectStatus.setName(IN_PROGRESS);
        projectStatusRepository.save(projectStatus);
        verify(projectStatusRepository, times(1)).save(projectStatus);
    }

    @Test
    public void testUpdateProjectStatus() {
        ProjectStatus projectStatus = new ProjectStatus();
        projectStatus.setName(IN_PROGRESS);
        when(projectStatusRepository.findById(1L)).thenReturn(projectStatus);
        ProjectStatus projectStatus1 = projectStatusRepository.findById(1L);
        projectStatus1.setName(IN_PROGRESS);
        projectStatusRepository.save(projectStatus1);
        assertEquals(IN_PROGRESS, projectStatus1.getName());
        verify(projectStatusRepository, times(1)).save(projectStatus);
    }



}