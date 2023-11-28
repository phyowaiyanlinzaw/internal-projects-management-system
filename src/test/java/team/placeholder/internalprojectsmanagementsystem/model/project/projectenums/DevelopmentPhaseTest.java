package team.placeholder.internalprojectsmanagementsystem.model.project.projectenums;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DevelopmentPhaseTest {

    @Test
    public void testPlanningPhase() {
        DevelopmentPhase phase = DevelopmentPhase.PLANNING;
        assertEquals("PLANNING", phase.toString());
    }

    @Test
    public void testDesignPhase() {
        DevelopmentPhase phase = DevelopmentPhase.DESIGN;
        assertEquals("DESIGN", phase.toString());
    }

    @Test
    public void testCodingPhase() {
        DevelopmentPhase phase = DevelopmentPhase.CODING;
        assertEquals("CODING", phase.toString());
    }

    @Test
    public void testTestingPhase() {
        DevelopmentPhase phase = DevelopmentPhase.TESTING;
        assertEquals("TESTING", phase.toString());
    }

    @Test
    public void testReviewPhase() {
        DevelopmentPhase phase = DevelopmentPhase.REVIEW;
        assertEquals("REVIEW", phase.toString());
    }

    @Test
    public void testDeploymentPhase() {
        DevelopmentPhase phase = DevelopmentPhase.DEPLOYMENT;
        assertEquals("DEPLOYMENT", phase.toString());
    }

    @Test
    public void testMaintenancePhase() {
        DevelopmentPhase phase = DevelopmentPhase.MAINTENANCE;
        assertEquals("MAINTENANCE", phase.toString());
    }

    @Test
    public void testGetPhaseName() {
        // Arrange
        DevelopmentPhase enumInstance = DevelopmentPhase.TESTING; // Replace with your actual enum value

        // Act
        String phaseName = enumInstance.getPhaseName();

        // Assert
        assertEquals("TESTING", phaseName); // Verify that the method returns the correct name
    }
}

