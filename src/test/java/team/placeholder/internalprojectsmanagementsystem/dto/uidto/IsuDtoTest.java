package team.placeholder.internalprojectsmanagementsystem.dto.uidto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class IsuDtoTest {

    @Test
    public void testGetterAndSetter() {
        // Create an IsuDto
        IsuDto isuDto = new IsuDto();
        isuDto.setTitle("Sample Issue");
        isuDto.setDescription("This is a sample issue.");
        isuDto.setPlace("Sample Place");
        isuDto.setImpact("High");
        isuDto.setRoot_cause("Root Cause");
        isuDto.setDirect_cause("Direct Cause");
        isuDto.setCorrective_action("Corrective Action");
        isuDto.setPreventive_action("Preventive Action");
        isuDto.setSolved(true);
        isuDto.setCreated_date(1636407600000L); // Assuming a timestamp in milliseconds
        isuDto.setUpdated_date(1636407600000L);
        isuDto.setSolved_date(1636407600000L);
        isuDto.setResponsible_type("Team");
        isuDto.setIssueCategory("Bug");
        isuDto.setStatus("Open");
        isuDto.setUser_uploader(1L);
        isuDto.setUser_pic(2L);
        isuDto.setResponsible_party(3L);
        isuDto.setProject_id(4L);

        // Verify that the getters return the expected values
        assertEquals("Sample Issue", isuDto.getTitle());
        assertEquals("This is a sample issue.", isuDto.getDescription());
        assertEquals("Sample Place", isuDto.getPlace());
        assertEquals("High", isuDto.getImpact());
        assertEquals("Root Cause", isuDto.getRoot_cause());
        assertEquals("Direct Cause", isuDto.getDirect_cause());
        assertEquals("Corrective Action", isuDto.getCorrective_action());
        assertEquals("Preventive Action", isuDto.getPreventive_action());
        assertTrue(isuDto.isSolved());
        assertEquals(1636407600000L, isuDto.getCreated_date());
        assertEquals(1636407600000L, isuDto.getUpdated_date());
        assertEquals(1636407600000L, isuDto.getSolved_date());
        assertEquals("Team", isuDto.getResponsible_type());
        assertEquals("Bug", isuDto.getIssueCategory());
        assertEquals("Open", isuDto.getStatus());
        assertEquals(1L, isuDto.getUser_uploader());
        assertEquals(2L, isuDto.getUser_pic());
        assertEquals(3L, isuDto.getResponsible_party());
        assertEquals(4L, isuDto.getProject_id());

        // Modify some values using setters
        isuDto.setTitle("Updated Issue");
        isuDto.setDescription("This issue has been updated.");
        isuDto.setPlace("Updated Place");
        isuDto.setImpact("Medium");
        isuDto.setRoot_cause("Updated Root Cause");
        isuDto.setDirect_cause("Updated Direct Cause");
        isuDto.setCorrective_action("Updated Corrective Action");
        isuDto.setPreventive_action("Updated Preventive Action");
        isuDto.setSolved(false);
        isuDto.setCreated_date(1636500000000L); // Assuming a different timestamp in milliseconds
        isuDto.setUpdated_date(1636500000000L);
        isuDto.setSolved_date(0L); // Assuming the issue is not solved
        isuDto.setResponsible_type("Individual");
        isuDto.setIssueCategory("Enhancement");
        isuDto.setStatus("Closed");
        isuDto.setUser_uploader(5L);
        isuDto.setUser_pic(6L);
        isuDto.setResponsible_party(7L);
        isuDto.setProject_id(8L);

        // Verify that the modified values are reflected
        assertEquals("Updated Issue", isuDto.getTitle());
        assertEquals("This issue has been updated.", isuDto.getDescription());
        assertEquals("Updated Place", isuDto.getPlace());
        assertEquals("Medium", isuDto.getImpact());
        assertEquals("Updated Root Cause", isuDto.getRoot_cause());
        assertEquals("Updated Direct Cause", isuDto.getDirect_cause());
        assertEquals("Updated Corrective Action", isuDto.getCorrective_action());
        assertEquals("Updated Preventive Action", isuDto.getPreventive_action());
        assertFalse(isuDto.isSolved());
        assertEquals(1636500000000L, isuDto.getCreated_date());
        assertEquals(1636500000000L, isuDto.getUpdated_date());
        assertEquals(0L, isuDto.getSolved_date());
        assertEquals("Individual", isuDto.getResponsible_type());
        assertEquals("Enhancement", isuDto.getIssueCategory());
        assertEquals("Closed", isuDto.getStatus());
        assertEquals(5L, isuDto.getUser_uploader());
        assertEquals(6L, isuDto.getUser_pic());
        assertEquals(7L, isuDto.getResponsible_party());
        assertEquals(8L, isuDto.getProject_id());
    }

    // Add more tests for edge cases or specific scenarios

    // ... rest of the tests
}
