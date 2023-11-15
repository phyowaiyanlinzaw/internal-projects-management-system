//package team.placeholder.internalprojectsmanagementsystem.service.project;
//
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import team.placeholder.internalprojectsmanagementsystem.dto.model.project.DeliverableTypeDto;
//import team.placeholder.internalprojectsmanagementsystem.model.project.DeliverableType;
//import team.placeholder.internalprojectsmanagementsystem.repository.project.DeliverableTypeRepo;
//import team.placeholder.internalprojectsmanagementsystem.service.impl.project.DeliverableTypeServiceImpl;
//
//import java.util.HashSet;
//import java.util.Set;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.assertj.core.api.Assertions.assertThatThrownBy;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//public class DeliverableTypeServiceTest {
//
//    @Mock
//    private DeliverableTypeRepo deliverableTypeRepo;
//
//    @InjectMocks
//    private DeliverableTypeServiceImpl deliverableTypeService;
//
//    @Test
//    public void testGetAllWhenRepositoryReturnsNonEmptySetThenReturnNonEmptySet() {
//        // Arrange
//        Set<DeliverableType> deliverableTypes = new HashSet<>();
//        deliverableTypes.add(new DeliverableType());
//        when(deliverableTypeRepo.findAll()).thenReturn(deliverableTypes);
//
//        // Act
//        Set<DeliverableTypeDto> result = deliverableTypeService.getAll();
//
//        // Assert
//        verify(deliverableTypeRepo, times(1)).findAll();
//        assertThat(result).isNotEmpty();
//    }
//
//    @Test
//    public void testGetAllWhenRepositoryReturnsEmptySetThenReturnEmptySet() {
//        // Arrange
//        when(deliverableTypeRepo.findAll()).thenReturn(new HashSet<>());
//
//        // Act
//        Set<DeliverableTypeDto> result = deliverableTypeService.getAll();
//
//        // Assert
//        verify(deliverableTypeRepo, times(1)).findAll();
//        assertThat(result).isEmpty();
//    }
//
//    @Test
//    public void testGetAllWhenRepositoryThrowsExceptionThenThrowException() {
//        // Arrange
//        when(deliverableTypeRepo.findAll()).thenThrow(new RuntimeException());
//
//        // Act & Assert
//        assertThatThrownBy(() -> deliverableTypeService.getAll())
//                .isInstanceOf(RuntimeException.class);
//
//        verify(deliverableTypeRepo, times(1)).findAll();
//    }
//}