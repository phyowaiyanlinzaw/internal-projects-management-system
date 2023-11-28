package team.placeholder.internalprojectsmanagementsystem.repository.project;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import team.placeholder.internalprojectsmanagementsystem.model.project.Tasks;
import team.placeholder.internalprojectsmanagementsystem.model.project.projectenums.TaskStatus;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class TaskRepositoryTest {

    @Mock
    private TaskRepository tasksRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void findById() {
        long id = 1L;
        Tasks expectedTask = new Tasks();

        when(tasksRepository.findById(id)).thenReturn(expectedTask);

        Tasks result = tasksRepository.findById(id);

        assertEquals(expectedTask, result);
    }

    @Test
    void findByUserId() {
        long userId = 1L;

        List<Tasks> expectedTasks = new ArrayList<>();
        expectedTasks.add(new Tasks());

        when(tasksRepository.findByUserId(userId)).thenReturn(expectedTasks);

        List<Tasks> result = tasksRepository.findByUserId(userId);

        assertEquals(expectedTasks.size(), result.size());
        for (int i = 0; i < expectedTasks.size(); i++) {
            assertEquals(expectedTasks.get(i), result.get(i));
        }
    }

    @Test
    void findTasksByProjectIdAndUserId() {
        long projectId = 1L;
        long userId = 2L;

        List<Tasks> expectedTasks = new ArrayList<>();
        expectedTasks.add(new Tasks());

        when(tasksRepository.findTasksByProjectIdAndUserId(projectId, userId)).thenReturn(expectedTasks);

        List<Tasks> result = tasksRepository.findTasksByProjectIdAndUserId(projectId, userId);

        assertEquals(expectedTasks.size(), result.size());
        for (int i = 0; i < expectedTasks.size(); i++) {
            assertEquals(expectedTasks.get(i), result.get(i));
        }
    }

    @Test
    void countByProjectIdAndStatus() {
        long projectId = 1L;
        TaskStatus status = TaskStatus.IN_PROGRESS;

        long expectedCount = 3L;

        when(tasksRepository.countByProjectIdAndStatus(projectId, status)).thenReturn(expectedCount);

        long result = tasksRepository.countByProjectIdAndStatus(projectId, status);

        assertEquals(expectedCount, result);
    }

    @Test
    void countByProjectId() {

        long projectId = 1L;

        long expectedCount = 5L;

        when(tasksRepository.countByProjectId(projectId)).thenReturn(expectedCount);


        long result = tasksRepository.countByProjectId(projectId);


        assertEquals(expectedCount, result);
    }

    @Test
    void countByProjectIdAndDeletedFalse() {
        long projectId = 1L;

        long expectedCount = 3L;

        when(tasksRepository.countByProjectIdAndDeletedFalse(projectId)).thenReturn(expectedCount);


        long result = tasksRepository.countByProjectIdAndDeletedFalse(projectId);


        assertEquals(expectedCount, result);
    }

    @Test
    void findByProjectId() {
        long projectId = 1L;

        List<Tasks> expectedTasks = new ArrayList<>();
        expectedTasks.add(new Tasks());

        when(tasksRepository.findByProjectId(projectId)).thenReturn(expectedTasks);

        List<Tasks> result = tasksRepository.findByProjectId(projectId);

        assertEquals(expectedTasks.size(), result.size());
        for (int i = 0; i < expectedTasks.size(); i++) {
            assertEquals(expectedTasks.get(i), result.get(i));
        }
    }
}