package team.placeholder.internalprojectsmanagementsystem.model.project;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class DeliverableTypeTest {
    @Mock
    private DeliverableType deliverableType;

    @Test
    public void testDeliverableTypeConstruction() {
        // Arrange
        Long deliverableTypeId = 1L;
        String type = "Type1";

        // Act
        deliverableType = new DeliverableType();
        deliverableType.setId(deliverableTypeId);
        deliverableType.setType(type);

        // Assert
        assertEquals(deliverableTypeId, deliverableType.getId());
        assertEquals(type, deliverableType.getType());
    }

    @Test
    public void testDeliverableTypeEquality() {
        // Arrange
        Long id1 = 1L;
        Long id2 = 2L;
        String type = "Type1";
        String type2 = "Type2";

        DeliverableType deliverableType1 = new DeliverableType();
        deliverableType1.setId(id1);
        deliverableType1.setType(type);

        DeliverableType deliverableType2 = new DeliverableType();
        deliverableType2.setId(id1); // Same ID as deliverableType1
        deliverableType2.setType(type);

        DeliverableType deliverableType3 = new DeliverableType();
        deliverableType3.setId(id2); // Different ID
        deliverableType3.setType(type2);

        // Assert
        assertEquals(deliverableType1.getId(), deliverableType2.getId());
        assertEquals(deliverableType1.getType(), deliverableType2.getType());
        assertEquals(deliverableType1, deliverableType2);
        assertEquals(deliverableType1.hashCode(), deliverableType2.hashCode());

        assertNotEquals(deliverableType1.getId(), deliverableType3.getId());
        assertNotEquals(deliverableType1.getType(), deliverableType3.getType()); // Modified assertion
        assertNotEquals(deliverableType1, deliverableType3);
        assertNotEquals(deliverableType1.hashCode(), deliverableType3.hashCode());
    }

}
