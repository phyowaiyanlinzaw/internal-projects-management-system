package team.placeholder.internalprojectsmanagementsystem.controller.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import team.placeholder.internalprojectsmanagementsystem.service.FakerService;
import team.placeholder.internalprojectsmanagementsystem.service.impl.project.DeliverableServiceImpl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;


class DeliverableControllerTest {

    @Mock
    private DeliverableServiceImpl deliverableService;

    @Mock
    private FakerService fakerService;

    @InjectMocks
    private DeliverableController deliverableController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void generateFakeDeliverable() {
        int count = 10;
        doNothing().when(fakerService).generateAndSaveFakeDeliverable(count);
        String result= deliverableController.generateFakeDeliverable(count);
         assertEquals("Fake deliverables generated successfully",result);
        verify(fakerService, times(1)).generateAndSaveFakeDeliverable(count);



    }


}