//package team.placeholder.internalprojectsmanagementsystem.service.impl.project;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.mockito.junit.MockitoJUnitRunner;
//import org.springframework.boot.test.context.SpringBootTest;
//import team.placeholder.internalprojectsmanagementsystem.dto.model.department.DepartmentDto;
//import team.placeholder.internalprojectsmanagementsystem.dto.model.project.*;
//
//import team.placeholder.internalprojectsmanagementsystem.repository.department.DepartmentRepository;
//import team.placeholder.internalprojectsmanagementsystem.repository.project.*;
//import team.placeholder.internalprojectsmanagementsystem.repository.user.UserRepository;
//import team.placeholder.internalprojectsmanagementsystem.service.project.ArchitectureService;
//import team.placeholder.internalprojectsmanagementsystem.service.project.DeliverableTypeService;
//import team.placeholder.internalprojectsmanagementsystem.service.project.ProjectService;
//
//import java.time.LocalDate;
//import java.util.*;
//
//import static javax.swing.text.StyleConstants.Background;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.mockito.Mockito.*;
//import static team.placeholder.internalprojectsmanagementsystem.model.project.projectenums.DevelopmentPhase.PLANNING;
//@SpringBootTest
//class ProjectServiceImplTest {
//
//    @Mock
//    private ArchitectureService architectureService;
//
//    @Mock
//    private ArchitectureRepository architectureRepository;
//
//    @Mock
//    private DeliverableTypeService deliverableTypeService;
//
//    @Mock
//    private DeliverableRepository deliverableRepository;
//
//    @Mock
//    private UserRepository userRepository;
//
//    @Mock
//    private DepartmentRepository departmentRepository;
//
//    @InjectMocks
//    private ProjectService projectService;
//
//    @Test
//    public void toSaveProject(){
//        ProjectDto projectDto = new ProjectDto();
//
//    }
//
//
//
////@Test
////    public void toSaveProject(){
////    ProjectDto projectDto= new ProjectDto();
////    ArchitectureDto architectureDto1 = new ArchitectureDto();
////    architectureDto1.setId(null);
////    ArchitectureDto architectureDto2 = new ArchitectureDto();
////    architectureDto2.setId(1L);
////    Set<ArchitectureDto> architectureDtoSet = new HashSet<>();
////    architectureDtoSet.add(architectureDto1);
////    architectureDtoSet.add(architectureDto2);
////    projectDto.setArchitectureDto(architectureDtoSet);
////    when(architectureService.save(architectureDto1)).thenReturn(new Architecture());
////    when(architectureRepository.getReferenceById(1L)).thenReturn(new Architecture());
////
////    Set<Architecture> architectureSet = new HashSet<>();
////
////    for (ArchitectureDto architectureDto : projectDto.getArchitectureDto()) {
////        if (architectureDto.getId() == null) {
////            architectureSet.add(architectureService.save(architectureDto));
////        } else {
////            architectureSet.add(architectureRepository.getReferenceById(architectureDto.getId()));
////        }
////    }
////
////    assertEquals(2, architectureSet.size());
////
////    DeliverableTypeDto deliverableTypeDto1 = new DeliverableTypeDto();
////    deliverableTypeDto1.setId(0L);
////    DeliverableTypeDto deliverableTypeDto2 = new DeliverableTypeDto();
////    deliverableTypeDto2.setId(1L);
////
////    List<DeliverableDto> deliverableDtoList = new ArrayList<>();
////    DeliverableDto deliverableDto1 = new DeliverableDto();
////    deliverableDto1.setDeliverableType(deliverableTypeDto1);
////    DeliverableDto deliverableDto2 = new DeliverableDto();
////    deliverableDto2.setDeliverableType(deliverableTypeDto2);
////    deliverableDtoList.add(deliverableDto1);
////    deliverableDtoList.add(deliverableDto2);
////    projectDto.setDeliverableDto(deliverableDtoList);
////
////    // Mock behavior for deliverableTypeService and deliverableRepository
////    when(deliverableTypeService.save(deliverableTypeDto1)).thenReturn(new DeliverableType());
////    when(deliverableRepository.getReferenceById(1L)).thenReturn(new Deliverable());
////
////    List<Deliverable> deliverableList = new ArrayList<>();
////
////    for (DeliverableDto deliverableDto : projectDto.getDeliverableDto()) {
////        if (deliverableDto.getDeliverableType().getId() == 0) {
////            DeliverableType newDeliverableType = deliverableTypeService.save(deliverableDto.getDeliverableType());
////            Deliverable newDeliverable = DeliverableMapper.toDeliverable(deliverableDto);
////            newDeliverable.setDeliverableTypes(newDeliverableType);
////            deliverableList.add(newDeliverable);
////        } else {
////            DeliverableType existingDeliverableType = deliverableRepository.getReferenceById(deliverableDto.getDeliverableType().getId()).getDeliverableTypes();
////            Deliverable existingDeliverable = DeliverableMapper.toDeliverable(deliverableDto);
////            existingDeliverable.setDeliverableTypes(existingDeliverableType);
////            deliverableList.add(existingDeliverable);
////        }
////    }
////
////    assertEquals(2, deliverableList.size());
////
////
////}
//
//}
