package team.placeholder.internalprojectsmanagementsystem.model.project;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

public class DeliverableTest {

    @MockBean
    Deliverable deliverable1 = mock(Deliverable.class);
    @MockBean
    private Deliverable deliverable2 = mock(Deliverable.class);
    @MockBean
    DeliverableType mockDeliverableType = mock(DeliverableType.class);
    @Test
    public void testSetAndGetDeliverableType() {
        // Set deliverable type using setter
        deliverable1 = new Deliverable();
        deliverable1.setDeliverableTypes(mockDeliverableType);

        // Verify deliverable type is set and retrieved correctly
        assertEquals(mockDeliverableType, deliverable1.getDeliverableTypes());
    }
    @Test
    public void testSetAndGetId() {
        // Set id using constructor
        deliverable1 = new Deliverable();
        deliverable1.setId(1L);

        // Verify id is set and retrieved correctly
        assertEquals(1L, deliverable1.getId());
    }

    @Test
    public void testSetAndGetStatus() {
        // Set status using setter
        deliverable1 = new Deliverable();
        deliverable1.setStatus(true);

        // Verify status is set and retrieved correctly
        assertTrue(deliverable1.isStatus());
    }


    @Test
    public void testEqualsAndHashCode() {
        // Create two instances with the same id
        deliverable1 = new Deliverable();
        deliverable1.setId(1L);

        deliverable2 = new Deliverable();
        deliverable2.setId(1L);

        // Verify equals and hashCode methods
        assertEquals(deliverable1, deliverable2);
        assertEquals(deliverable1.hashCode(), deliverable2.hashCode());
    }

    @Test
    public void testNotEquals() {
        // Create two instances with different ids
        deliverable1 = new Deliverable();
        deliverable1.setId(1L);

        deliverable2 = new Deliverable();
        deliverable2.setId(2L);

        // Verify not equals
        assertNotEquals(deliverable1, deliverable2);
    }

    @Test
    public void testEqualsWithNull() {
        // Create an instance with a non-null id
        deliverable1 = new Deliverable();
        deliverable1.setId(1L);

        // Verify not equals when comparing with null
        assertNotEquals(deliverable1, null);
    }

    @Test
    public void testEqualsWithDifferentClass() {
        // Create an instance with a non-null id
        deliverable1 = new Deliverable();
        deliverable1.setId(1L);

        // Verify not equals when comparing with an object of a different class
        assertNotEquals(deliverable1, "not a Deliverable");
    }

}
