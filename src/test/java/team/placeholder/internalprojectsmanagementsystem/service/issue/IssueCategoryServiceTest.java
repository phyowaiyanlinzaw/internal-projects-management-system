//package team.placeholder.internalprojectsmanagementsystem.service.issue;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.MockitoAnnotations;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//class IssueCategoryServiceTest {
//
//    @Mock
//    private IssueCategoryService issueCategoryService;
//
//    @BeforeEach
//    public void setUp() {
//        MockitoAnnotations.initMocks(this);
//    }
//
//    @Test
//    public void testSaveIssueCategory() {
//        IssueCategoryDto issueCategoryDto = new IssueCategoryDto();
//        Mockito.when(issueCategoryService.save(issueCategoryDto)).thenReturn(issueCategoryDto);
//        IssueCategoryDto saveIssueCategory = issueCategoryService.save(issueCategoryDto);
//        assertEquals(issueCategoryDto,saveIssueCategory);
//
//    }
//
//    @Test
//    public void testUpdateIssueCategory() {
//        IssueCategoryDto issueCategoryDto = new IssueCategoryDto();
//        Mockito.when(issueCategoryService.update(issueCategoryDto)).thenReturn(issueCategoryDto);
//        IssueCategoryDto updateIssueCategory = issueCategoryService.update(issueCategoryDto);
//        assertEquals(issueCategoryDto,updateIssueCategory);
//
//    }
//
//    @Test
//    public void testFindById() {
//        long id = 1;
//        IssueCategoryDto issueCategoryDto = new IssueCategoryDto();
//        Mockito.when(issueCategoryService.findById(id)).thenReturn(issueCategoryDto);
//        IssueCategoryDto issueCategoryById = issueCategoryService.findById(1L);
//        assertEquals(issueCategoryDto,issueCategoryById);
//
//    }
//
//    @Test
//    public void testFindAll() {
//        List<IssueCategoryDto> issueCategoryDtoList= new ArrayList<>();
//        Mockito.when(issueCategoryService.findAll()).thenReturn(issueCategoryDtoList);
//        List<IssueCategoryDto> issueCategoryDtoList1 = issueCategoryService.findAll();
//        assertEquals(issueCategoryDtoList,issueCategoryDtoList1);
//
//    }
//
//
//
//}