//package team.placeholder.internalprojectsmanagementsystem.service.project;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import team.placeholder.internalprojectsmanagementsystem.dto.model.project.DeliverableDto;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.when;
//
//@ExtendWith(MockitoExtension.class)
//class DeliverableServiceTest {
//
//    @Mock
//    private DeliverableDto deliverableDto;
//
//    @InjectMocks
//    private DeliverableServiceImpl deliverableService;
//
//    @BeforeEach
//    void setUp() {
//        deliverableDto = new DeliverableDto();
//        deliverableDto.setId(1L);
//        deliverableDto.setStatus(true);
//    }
//
//    @Test
//    void testCreateDeliverableWhenValidDtoThenReturnDto() {
//        when(deliverableService.createDeliverable(deliverableDto)).thenReturn(deliverableDto);
//
//        DeliverableDto result = deliverableService.createDeliverable(deliverableDto);
//
//        assertEquals(deliverableDto, result);
//    }
//
//    @Test
//    void testUpdateDeliverableWhenValidDtoThenReturnDto() {
//        when(deliverableService.updateDeliverable(deliverableDto)).thenReturn(deliverableDto);
//
//        DeliverableDto result = deliverableService.updateDeliverable(deliverableDto);
//
//        assertEquals(deliverableDto, result);
//    }
//}