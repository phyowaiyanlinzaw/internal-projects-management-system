package team.placeholder.internalprojectsmanagementsystem.controller.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import team.placeholder.internalprojectsmanagementsystem.controller.api.IssueController;
import team.placeholder.internalprojectsmanagementsystem.dto.model.issue.IssueDto;
import team.placeholder.internalprojectsmanagementsystem.dto.uidto.IsuDto;
import team.placeholder.internalprojectsmanagementsystem.service.impl.issue.IssueServiceImpl;
import team.placeholder.internalprojectsmanagementsystem.service.issue.IssueService;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class IssueControllerTest {

    @Mock
    private IssueServiceImpl issueService;

    @InjectMocks
    private IssueController issueController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetAllIssues() {
        // Mocking the service response
        List<IssueDto> mockIssues = Arrays.asList(new IssueDto(), new IssueDto());
        when(issueService.getAllIssues()).thenReturn(mockIssues);

        // Calling the controller method
        ResponseEntity<List<IssueDto>> response = issueController.getAllIssues();

        // Verifying the results
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockIssues, response.getBody());

        // Verifying service method invocation
        verify(issueService, times(1)).getAllIssues();

        // Mocking ResponseEntity for additional validation
        ResponseEntity<List<IssueDto>> expectedResponse = ResponseEntity.ok(mockIssues);
        assertEquals(expectedResponse, response);
    }

    @Test
    void testSaveIssue() {
        // Mocking the request body
        IsuDto isuDto = new IsuDto(); // create your IsuDto instance

        // Mock the behavior of the issueService.save() method
        when(issueService.save(any(IsuDto.class))).thenReturn(new IssueDto());

        // When
        ResponseEntity<IssueDto> responseEntity = issueController.saveIssue(isuDto);

        // Then
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        // Optionally, you can also verify that the issueService.save() method was called with the correct argument
        verify(issueService).save(isuDto);
    }

    @Test
    void testGetIssueById() {
        // Mocking the path variable
        long issueId = 1L;
        // Mocking the service response
        IssueDto mockIssueDto = new IssueDto();
        when(issueService.getIssueById(issueId)).thenReturn(mockIssueDto);

        // Calling the controller method
        ResponseEntity<IssueDto> response = issueController.getIssueById(issueId);

        // Verifying the results
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockIssueDto, response.getBody());

        // Verifying service method invocation
        verify(issueService, times(1)).getIssueById(issueId);
    }

    @Test
    void testGetIssueByIdNotFound() {
        // Mocking the path variable
        long nonExistentIssueId = 999L;

        // Mocking the service response for a non-existent issue
        when(issueService.getIssueById(nonExistentIssueId)).thenReturn(null);

        // Calling the controller method
        ResponseEntity<IssueDto> response = issueController.getIssueById(nonExistentIssueId);

        // Verifying the results for a not found scenario
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertTrue(response.getBody() == null || response.getBody().equals(""));
        assertTrue(response.getBody() == null || response.getBody().equals(""));
    }


    @Test
    void testUpdateIssue() {
        // Mocking the path variable
        long issueId = 1L;

        // Mocking the request body
        IssueDto issueDto = new IssueDto();
        issueDto.setId(issueId);

        IssueDto mockUpdatedIssue = new IssueDto();
        when(issueService.updateIssue(issueDto)).thenReturn(mockUpdatedIssue);

        // Calling the controller method
        ResponseEntity<IssueDto> response = issueController.updateIssue(issueId, issueDto);

        // Verifying the results
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockUpdatedIssue, response.getBody());

        verify(issueService, times(1)).updateIssue(issueDto);
    }

    @Test
    void testUpdateIssueBadRequest() {
        // Mocking the path variable
        long issueId = 1L;

        // Mocking the request body
        IssueDto issueDto = new IssueDto();
        issueDto.setId(issueId);

        // Mocking the service response for a failed update
        when(issueService.updateIssue(issueDto)).thenReturn(null);

        ResponseEntity<IssueDto> response = issueController.updateIssue(issueId, issueDto);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertTrue(response.getBody() == null || response.getBody().equals(""));
        assertTrue(response.getBody() == null || response.getBody().equals(""));

        // Verifying service method invocation
        verify(issueService, times(1)).updateIssue(issueDto);
    }

    @Test
    void testGetFilteredDetails() {
        // Mocking the path variable
        String status = "PENDING";

        // Mocking the service response
        List<IssueDto> mockFilteredDetails = Arrays.asList(new IssueDto(), new IssueDto());
        when(issueService.getIssuesByStatus(status)).thenReturn(mockFilteredDetails);

        // Calling the controller method
        ResponseEntity<List<IssueDto>> response = issueController.getFilteredDetails(status);
        // Verifying the results
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockFilteredDetails, response.getBody());

        // Verifying service method invocation
        verify(issueService, times(1)).getIssuesByStatus(status);
    }

    @Test
    void testGetIssuesBySolvedStatusWhenTrue() {

        Boolean solved = Boolean.TRUE;

        // Mock the service response
        List<IssueDto> mockIssues = Arrays.asList(new IssueDto(), new IssueDto());
        when(issueController.issueService.getIssuesBySolvedStatus(true)).thenReturn(mockIssues);

        // Act
        List<IssueDto> result = issueController.getIssuesBySolvedStatus(solved);

        // Assert
        assertEquals(mockIssues, result);
    }

    @Test
    void testGetIssuesBySolvedStatusWhenFalse() {

        Boolean solved = Boolean.FALSE;

        // Mock the service response
        List<IssueDto> mockIssues = Arrays.asList(new IssueDto(), new IssueDto());
        when(issueController.issueService.getIssuesBySolvedStatus(false)).thenReturn(mockIssues);

        // Act
        List<IssueDto> result = issueController.getIssuesBySolvedStatus(solved);

        // Assert
        assertEquals(mockIssues, result);
    }

    @Test
    void testGetIssuesBySolvedStatusWhenNull() {

        Boolean solved = null;

        List<IssueDto> mockAllIssues = Arrays.asList(new IssueDto(), new IssueDto());
        when(issueController.issueService.getAllIssues()).thenReturn(mockAllIssues);

        // Act
        List<IssueDto> result = issueController.getIssuesBySolvedStatus(solved);

        // Assert
        assertEquals(mockAllIssues, result);
    }




    @Test
    void testGetFilteredCategory() {
        // Mocking the path variable
        String category = "BUG";
        List<IssueDto> mockIssues = Arrays.asList(new IssueDto(), new IssueDto());
        when(issueService.getIssuesByCategory(category)).thenReturn(mockIssues);
        ResponseEntity<List<IssueDto>> result = issueController.getFilteredCategory(category);
        assertEquals(mockIssues, result.getBody());
        verify(issueService, times(1)).getIssuesByCategory(category);
    }

    @Test
    void testGetIssueByPending() {
        // Mocking the path variable
        long id = 1L;

        // Mocking the service response
        List<IssueDto> mockIssues = Arrays.asList(new IssueDto(), new IssueDto());
        when(issueService.getPendingIssueList(id)).thenReturn(mockIssues);

        // Calling the controller method
        ResponseEntity<List<IssueDto>> result = issueController.getIssueByPending(id);

        assertEquals(mockIssues, result.getBody());

        verify(issueService, times(1)).getPendingIssueList(id);
    }

    @Test
    void testUpdateIssueList() {
        // Mocking the request body
        List<IssueDto> mockIssueDtos = Arrays.asList(new IssueDto(), new IssueDto());

        // Mocking the service response
        List<IssueDto> mockUpdatedIssueList = Arrays.asList(new IssueDto(), new IssueDto());
        when(issueService.updateStatusOfIssueList(mockIssueDtos)).thenReturn(mockUpdatedIssueList);

        // Calling the controller method
        ResponseEntity<List<IssueDto>> result = issueController.updateIssueList(mockIssueDtos);

        // Verifying the results
        assertEquals(mockUpdatedIssueList, result.getBody());

        // Verifying service method invocation
        verify(issueService, times(1)).updateStatusOfIssueList(mockIssueDtos);
    }

    @Test
    void testGetUnsolvedIssues() {
        // Mocking the path variable
        long userId = 1L;

        List<IssueDto> mockUnsolvedIssues = Arrays.asList(new IssueDto(), new IssueDto());
        when(issueService.getUnsolvedIssues(userId)).thenReturn(mockUnsolvedIssues);

        ResponseEntity<List<IssueDto>> result = issueController.getUnsolvedIssues(userId);

        assertEquals(mockUnsolvedIssues, result.getBody());

        verify(issueService, times(1)).getUnsolvedIssues(userId);
    }
    @Test
    void testCountIssuesByProjectManagerId() {
        // Arrange
        long projectManagerId = 1L;

        long expectedCount = 10L; // Replace with the expected count

        // Mock the behavior of issueService.countIssuesByProjectManagerId
        when(issueService.countIssuesByProjectManagerId(projectManagerId)).thenReturn(expectedCount);

        // Act
        ResponseEntity<Long> result = issueController.countIssuesByProjectManagerId(projectManagerId);

        // Assert
        assertEquals(200, result.getStatusCodeValue()); // OK status code
        assertEquals(expectedCount, result.getBody()); // Compare the count in the response with the expected count

        // Optionally, you can add more assertions or verifications based on your specific requirements.
    }

}
