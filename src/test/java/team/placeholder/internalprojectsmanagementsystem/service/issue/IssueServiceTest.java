//package team.placeholder.internalprojectsmanagementsystem.service.issue;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.MockitoAnnotations;
//import team.placeholder.internalprojectsmanagementsystem.dto.model.issue.IssueDto;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class IssueServiceTest {
//    @Mock
//    private IssueService issueService;
//
//    @BeforeEach
//    public void setUp() {
//        MockitoAnnotations.initMocks(this);
//    }
//
//    @Test
//    public void testSaveIssue() {
//        IssueDto issueDto = new IssueDto();
//
//        Mockito.when(issueService.save(issueDto)).thenReturn(issueDto);
//        IssueDto savedIssue = issueService.save(issueDto);
//        assertEquals(issueDto, savedIssue);
//    }
//
//    @Test
//    public void testGetAllIssues() {
//        List<IssueDto> issueDtos = new ArrayList<>();
//
//        Mockito.when(issueService.getAllIssues()).thenReturn(issueDtos);
//
//        List<IssueDto> allIssues = issueService.getAllIssues();
//        assertEquals(issueDtos, allIssues);
//
//    }
//
//    @Test
//    public void testGetIssueById() {
//        long issueId = 1;
//        IssueDto issueDto = new IssueDto();
//
//        Mockito.when(issueService.getIssueById(issueId)).thenReturn(issueDto);
//
//        IssueDto issueById = issueService.getIssueById(1L);
//        assertEquals(issueDto, issueById);
//    }
//
//    @Test
//    public void testGetIssueByName() {
//        String issueName = "issueName";
//        IssueDto issueDto = new IssueDto();
//
//        Mockito.when(issueService.getIssueByName(issueName)).thenReturn(issueDto);
//
//        IssueDto issueByName = issueService.getIssueByName("issueName");
//        assertEquals(issueDto, issueByName);
//    }
//
//    @Test
//    public void testUpdateIssue() {
//        IssueDto issueDto = new IssueDto();
//
//        Mockito.when(issueService.updateIssue(issueDto)).thenReturn(issueDto);
//        IssueDto updatedIssue = issueService.updateIssue(issueDto);
//        assertEquals(issueDto, updatedIssue);
//    }
//
//    @Test
//    public void  testDeleteIssue() {
//        long issueId = 1;
//        issueService.deleteIssue(issueId);
//        Mockito.verify(issueService).deleteIssue(issueId);
//    }
//}