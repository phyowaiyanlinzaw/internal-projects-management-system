package team.placeholder.internalprojectsmanagementsystem.model.issue.issueenum;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IssueStatusTest {

    @Test
    public void testEnumValuesNotNull() {

        for (IssueStatus status : IssueStatus.values()) {
            assertNotNull(status);
        }
    }

    @Test
    public void testEnumToString() {

        assertEquals("PENDING", IssueStatus.PENDING.toString());
        assertEquals("APPROVE", IssueStatus.APPROVE.toString());
        assertEquals("DECLINE", IssueStatus.DECLINE.toString());
        assertEquals("DUPLICATE", IssueStatus.DUPLICATE.toString());
        assertEquals("IN_PROGRESS", IssueStatus.IN_PROGRESS.toString());
        assertEquals("OPEN", IssueStatus.OPEN.toString());
    }



}