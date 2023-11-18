package team.placeholder.internalprojectsmanagementsystem.controller.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import team.placeholder.internalprojectsmanagementsystem.service.FakerService;
import team.placeholder.internalprojectsmanagementsystem.service.impl.project.SystemOutlineServiceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class SystemOutlineControllerTest {
    @Mock
    private FakerService fakerService;

    @Mock
    private SystemOutlineServiceImpl systemOutlineService;

    @InjectMocks
    private SystemOutlineController systemOutlineController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void generateFakeSystemOutline_Success() {
        int count = 5;

        doNothing().when(fakerService).generateAndSaveFakeSystemOutline(count);

        ResponseEntity<String> response = systemOutlineController.generateFakeSystemOutline(count);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Fake system outline generated", response.getBody());

        verify(fakerService, times(1)).generateAndSaveFakeSystemOutline(count);
    }

}
