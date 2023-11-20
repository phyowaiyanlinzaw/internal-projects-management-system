package team.placeholder.internalprojectsmanagementsystem.repository.project;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import team.placeholder.internalprojectsmanagementsystem.model.project.Deliverable;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class DeliverableRepositoryTest {

    @Mock
    private DeliverableRepository deliverableRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindById(){
        long id = 1L;
        Deliverable expectedDeliverable = new Deliverable();

        when(deliverableRepository.findById(id)).thenReturn(expectedDeliverable);

        Deliverable result = deliverableRepository.findById(id);

        assertEquals(expectedDeliverable, result);
    }
    }

