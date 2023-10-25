//package team.placeholder.internalprojectsmanagementsystem.service.impl.issue;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.mockito.junit.jupiter.MockitoExtension;
//import team.placeholder.internalprojectsmanagementsystem.dto.model.issue.IssueCategoryDto;
//import team.placeholder.internalprojectsmanagementsystem.model.issue.IssueCategory;
//import team.placeholder.internalprojectsmanagementsystem.repository.issue.IssueCategoryRepository;
//import java.util.ArrayList;
//import java.util.List;
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//class IssueCategoryServiceImplTest {
//    @Mock
//    private IssueCategoryRepository issueCategoryRepository;
//
//    @InjectMocks
//    private IssueCategoryServiceImpl issueCategoryService;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    public void testSaveIssueCategory() {
//       IssueCategoryDto issueCategoryDto = new IssueCategoryDto();
//       issueCategoryDto.setName("IT");
//
//         IssueCategory issueCategory = new IssueCategory();
//            issueCategory.setName(issueCategoryDto.getName());
//            issueCategory.setId(1L);
//            when(issueCategoryRepository.save(any(IssueCategory.class))).thenReturn(issueCategory);
//            IssueCategoryDto savedDto = issueCategoryService.save(issueCategoryDto);
//            System.out.println("Saved DTO: " + savedDto);
//            assertNotNull(savedDto);
//
//            assertEquals(issueCategoryDto.getName(), savedDto.getName());
//            assertEquals(issueCategory.getId(), savedDto.getId());
//            verify(issueCategoryRepository, times(1)).save(any(IssueCategory.class));
//
//    }
//    @Test
//    public void testUpdateIssueCategory() {
//        IssueCategory issueCategory = new IssueCategory();
//        issueCategory.setId(1L);
//
//        issueCategory.setName("HR");
//        issueCategoryRepository.save(issueCategory);
//        assertEquals("HR", issueCategory.getName());
//        verify(issueCategoryRepository, times(1)).save(issueCategory);
//    }
//
//
//    @Test
//    public void testFindIssueCategoryById() {
//        IssueCategory issueCategory = new IssueCategory();
//        issueCategory.setId(1L);
//        issueCategory.setName("IT");
//        when(issueCategoryRepository.findById(issueCategory.getId())).thenReturn(issueCategory);
//
//        IssueCategory issueCategory1 = issueCategoryRepository.findById(issueCategory.getId());
//
//        assertEquals("IT", issueCategory1.getName());
//        verify(issueCategoryRepository, times(1)).findById(issueCategory.getId());
//    }
//
//
//    @Test
//    public void testGetAllIssue() {
//        List<IssueCategory> list = new ArrayList<>();
//        IssueCategory issueCategory1 = new IssueCategory();
//        issueCategory1.setName("IT");
//
//        IssueCategory issueCategory2 = new IssueCategory();
//        issueCategory2.setName("HR");
//
//        list.add(issueCategory1);
//        list.add(issueCategory2);
//
//        when(issueCategoryRepository.findAll()).thenReturn(list);
//        List<IssueCategory> issueCategories = issueCategoryRepository.findAll();
//        assertEquals(2, issueCategories.size());
//        verify(issueCategoryRepository, times(1)).findAll();
//    }
//
//
//
//
//}