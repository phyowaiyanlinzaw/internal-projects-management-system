package team.placeholder.internalprojectsmanagementsystem.model.project.projectenums;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DevelopmentPhaseTest {

    @Test
    public void testPlanningPhase() {
        Development_phase phase = Development_phase.PLANNING;
        assertEquals("PLANNING", phase.toString());
    }

    @Test
    public void testDesignPhase() {
        Development_phase phase = Development_phase.DESIGN;
        assertEquals("DESIGN", phase.toString());
    }

    @Test
    public void testCodingPhase() {
        Development_phase phase = Development_phase.CODING;
        assertEquals("CODING", phase.toString());
    }

    @Test
    public void testTestingPhase() {
        Development_phase phase = Development_phase.TESTING;
        assertEquals("TESTING", phase.toString());
    }

    @Test
    public void testReviewPhase() {
        Development_phase phase = Development_phase.REVIEW;
        assertEquals("REVIEW", phase.toString());
    }

    @Test
    public void testDeploymentPhase() {
        Development_phase phase = Development_phase.DEPLOYMENT;
        assertEquals("DEPLOYMENT", phase.toString());
    }

    @Test
    public void testMaintenancePhase() {
        Development_phase phase = Development_phase.MAINTENANCE;
        assertEquals("MAINTENANCE", phase.toString());
    }

    @Test
    public void testGetPhaseName() {
        // Arrange
        Development_phase enumInstance = Development_phase.TESTING; // Replace with your actual enum value

        // Act
        String phaseName = enumInstance.getPhaseName();

        // Assert
        assertEquals("TESTING", phaseName); // Verify that the method returns the correct name
    }
}

