package team.placeholder.internalprojectsmanagementsystem.dto.model.project;

import org.junit.jupiter.api.Test;
import team.placeholder.internalprojectsmanagementsystem.dto.model.issue.IssueDto;

import static org.junit.jupiter.api.Assertions.*;

class IssueNotificationDtoTest {
    @Test
    void testIssueNotificationDto() {
        // Given
        IssueNotificationDto issueNotificationDto = new IssueNotificationDto();

        // When
        issueNotificationDto.setId(1L);
        issueNotificationDto.setDescription("Sample Description");
        issueNotificationDto.setNoti_time(System.currentTimeMillis());
        IssueDto issueDto = new IssueDto();
        issueNotificationDto.setIssue(issueDto);

        // Then
        assertEquals(1L, issueNotificationDto.getId());
        assertEquals("Sample Description", issueNotificationDto.getDescription());
        assertNotNull(issueNotificationDto.getNoti_time());
        assertEquals(issueDto, issueNotificationDto.getIssue());
    }

    @Test
    void testGettersAndSetters() {
        // Given
        IssueNotificationDto issueNotificationDto = new IssueNotificationDto();

        // When
        issueNotificationDto.setId(2L);
        issueNotificationDto.setDescription("Another Description");
        issueNotificationDto.setNoti_time(System.currentTimeMillis());
        IssueDto issueDto = new IssueDto();
        issueNotificationDto.setIssue(issueDto);

        // Then
        assertEquals(2L, issueNotificationDto.getId());
        assertEquals("Another Description", issueNotificationDto.getDescription());
        assertNotNull(issueNotificationDto.getNoti_time());
        assertEquals(issueDto, issueNotificationDto.getIssue());
    }


}