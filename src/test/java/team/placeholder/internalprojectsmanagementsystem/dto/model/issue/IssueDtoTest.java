package team.placeholder.internalprojectsmanagementsystem.dto.model.issue;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import team.placeholder.internalprojectsmanagementsystem.dto.model.project.ProjectDto;
import team.placeholder.internalprojectsmanagementsystem.dto.model.user.UserDto;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.*;

public class IssueDtoTest {
    @Mock
    private IssueDto issueDto;

    @Test
    void testIssueDto() {
        // Given
        IssueDto issueDto = new IssueDto();

        // When
        issueDto.setId(1L);
        issueDto.setTitle("Sample Title");
        issueDto.setDescription("Sample Description");
        issueDto.setPlace("Sample Place");
        issueDto.setImpact("Sample Impact");
        issueDto.setRoot_cause("Sample Root Cause");
        issueDto.setDirect_cause("Sample Direct Cause");
        issueDto.setCorrective_action("Sample Corrective Action");
        issueDto.setPreventive_action("Sample Preventive Action");
        issueDto.setResponsible_party(new Object()); // Adjust as needed
        issueDto.setSolved(true);
        issueDto.setCreated_date(System.currentTimeMillis());
        issueDto.setUpdated_date(System.currentTimeMillis());
        issueDto.setSolved_date(System.currentTimeMillis());
        issueDto.setStatus("Open");
        issueDto.setIssueCategory("Sample Category");
        issueDto.setResponsible_type("Sample Responsible Type");
        ProjectDto projectDto = new ProjectDto();
        issueDto.setProjectDto(projectDto);
        UserDto uploaderDto = new UserDto();
        issueDto.setUser_uploader(uploaderDto);
        UserDto picDto = new UserDto();
        issueDto.setUser_pic(picDto);

        // Then
        assertEquals(1L, issueDto.getId());
        assertEquals("Sample Title", issueDto.getTitle());
        assertEquals("Sample Description", issueDto.getDescription());
        assertEquals("Sample Place", issueDto.getPlace());
        assertEquals("Sample Impact", issueDto.getImpact());
        assertEquals("Sample Root Cause", issueDto.getRoot_cause());
        assertEquals("Sample Direct Cause", issueDto.getDirect_cause());
        assertEquals("Sample Corrective Action", issueDto.getCorrective_action());
        assertEquals("Sample Preventive Action", issueDto.getPreventive_action());
        assertNotNull(issueDto.getResponsible_party());
        assertTrue(issueDto.isSolved());
        assertNotNull(issueDto.getCreated_date());
        assertNotNull(issueDto.getUpdated_date());
        assertNotNull(issueDto.getSolved_date());
        assertEquals("Open", issueDto.getStatus());
        assertEquals("Sample Category", issueDto.getIssueCategory());
        assertEquals("Sample Responsible Type", issueDto.getResponsible_type());
        assertEquals(projectDto, issueDto.getProjectDto());
        assertEquals(uploaderDto, issueDto.getUser_uploader());
        assertEquals(picDto, issueDto.getUser_pic());
    }
}

