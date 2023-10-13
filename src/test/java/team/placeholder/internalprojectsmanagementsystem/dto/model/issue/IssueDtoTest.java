package team.placeholder.internalprojectsmanagementsystem.dto.model.issue;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.*;

public class IssueDtoTest {
    @Mock
    private IssueDto issueDto;

    @Test
    public void testIssueDto() {
        // Create an IssueDto object with some mock data
        issueDto = new IssueDto();
        issueDto.setId(1L);
        issueDto.setTitle("Sample Issue");
        issueDto.setDescription("This is a test issue.");
        issueDto.setPlace("Sample Place");
        issueDto.setImpact("High");
        issueDto.setRoot_cause("Test Root Cause");
        issueDto.setDirect_cause("Test Direct Cause");
        issueDto.setCorrective_action("Test Corrective Action");
        issueDto.setPreventive_action("Test Preventive Action");
        issueDto.setClient_or_user(1);
        issueDto.setSolved(true);
        issueDto.setCreated_date(new Date(System.currentTimeMillis()));
        issueDto.setUpdated_date(new Date(System.currentTimeMillis()));

        // Test the getter methods
        assertEquals(1L, issueDto.getId());
        assertEquals("Sample Issue", issueDto.getTitle());
        assertEquals("This is a test issue.", issueDto.getDescription());
        assertEquals("Sample Place", issueDto.getPlace());
        assertEquals("High", issueDto.getImpact());
        assertEquals("Test Root Cause", issueDto.getRoot_cause());
        assertEquals("Test Direct Cause", issueDto.getDirect_cause());
        assertEquals("Test Corrective Action", issueDto.getCorrective_action());
        assertEquals("Test Preventive Action", issueDto.getPreventive_action());
        assertEquals(1, issueDto.getClient_or_user());
        assertTrue(issueDto.isSolved());
        assertNotNull(issueDto.getCreated_date());
        assertNotNull(issueDto.getUpdated_date());
    }
}

