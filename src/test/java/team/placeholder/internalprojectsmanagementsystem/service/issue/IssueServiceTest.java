package team.placeholder.internalprojectsmanagementsystem.service.issue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import team.placeholder.internalprojectsmanagementsystem.dto.model.issue.IssueDto;
import team.placeholder.internalprojectsmanagementsystem.dto.uidto.IsuDto;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class IssueServiceTest {
    @Mock
    private IssueService issueService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSaveIssue() {
        IssueDto issueDto = new IssueDto();
        IsuDto isuDto = new IsuDto();
        Mockito.when(issueService.save(isuDto)).thenReturn(issueDto);
        IssueDto savedIssue = issueService.save(isuDto);
        assertEquals(issueDto, savedIssue);
    }

    @Test
    public void testGetAllIssues() {
        List<IssueDto> issueDtos = new ArrayList<>();

        Mockito.when(issueService.getAllIssues()).thenReturn(issueDtos);

        List<IssueDto> allIssues = issueService.getAllIssues();
        assertEquals(issueDtos, allIssues);

    }

    @Test
    public void testGetIssueById() {
        long issueId = 1;
        IssueDto issueDto = new IssueDto();

        Mockito.when(issueService.getIssueById(issueId)).thenReturn(issueDto);

        IssueDto issueById = issueService.getIssueById(1L);
        assertEquals(issueDto, issueById);
    }



    @Test
    public void testUpdateIssue() {
        IssueDto issueDto = new IssueDto();

        Mockito.when(issueService.updateIssue(issueDto)).thenReturn(issueDto);
        IssueDto updatedIssue = issueService.updateIssue(issueDto);
        assertEquals(issueDto, updatedIssue);
    }

    @Test
    public void testGetIssuesByStatus() {
        List<IssueDto> issueDtos = new ArrayList<>();

        Mockito.when(issueService.getIssuesByStatus("status")).thenReturn(issueDtos);

        List<IssueDto> issuesByStatus = issueService.getIssuesByStatus("status");
        assertEquals(issueDtos, issuesByStatus);
    }

    @Test
    public void testGetPendingIssueList() {
        List<IssueDto> issueDtos = new ArrayList<>();

        Mockito.when(issueService.getPendingIssueList(1L)).thenReturn(issueDtos);

        List<IssueDto> pendingIssueList = issueService.getPendingIssueList(1L);
        assertEquals(issueDtos, pendingIssueList);
    }

    @Test
    public void testUpdateStatusOfIssueList() {
        List<IssueDto> issueDtos = new ArrayList<>();

        Mockito.when(issueService.updateStatusOfIssueList(issueDtos)).thenReturn(issueDtos);

        List<IssueDto> updatedIssueList = issueService.updateStatusOfIssueList(issueDtos);
        assertEquals(issueDtos, updatedIssueList);
    }

    @Test
    public void testGetUnsolvedIssues() {
        List<IssueDto> issueDtos = new ArrayList<>();

        Mockito.when(issueService.getUnsolvedIssues(1L)).thenReturn(issueDtos);

        List<IssueDto> unsolvedIssues = issueService.getUnsolvedIssues(1L);
        assertEquals(issueDtos, unsolvedIssues);
    }

}