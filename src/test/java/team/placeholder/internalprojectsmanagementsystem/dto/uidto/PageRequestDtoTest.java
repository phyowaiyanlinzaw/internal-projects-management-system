package team.placeholder.internalprojectsmanagementsystem.dto.uidto;

import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import static org.junit.jupiter.api.Assertions.*;

public class PageRequestDtoTest {

    @Test
    public void testGetPageableWithDefaultValues() {
        // Create a PageRequestDto with default values
        PageRequestDto pageRequestDto = new PageRequestDto();

        // Get the Pageable object using the method under test
        Pageable pageable = pageRequestDto.getPageable(pageRequestDto);

        // Verify that the returned Pageable has the expected values
        assertEquals(0, pageable.getPageNumber());
        assertEquals(10, pageable.getPageSize());
        assertEquals(Sort.Direction.ASC, pageable.getSort().getOrderFor("id").getDirection());
    }

    @Test
    public void testGetPageableWithCustomValues() {
        // Create a PageRequestDto with custom values
        PageRequestDto pageRequestDto = new PageRequestDto();
        pageRequestDto.setPageNo(1);
        pageRequestDto.setPageSize(20);
        pageRequestDto.setSort(Sort.Direction.DESC);
        pageRequestDto.setSortById("name");

        // Get the Pageable object using the method under test
        Pageable pageable = pageRequestDto.getPageable(pageRequestDto);

        // Verify that the returned Pageable has the expected custom values
        assertEquals(1, pageable.getPageNumber());
        assertEquals(20, pageable.getPageSize());
        assertEquals(Sort.Direction.DESC, pageable.getSort().getOrderFor("name").getDirection());
    }

    // Add more tests for edge cases or specific scenarios

    // ... rest of the tests
}
