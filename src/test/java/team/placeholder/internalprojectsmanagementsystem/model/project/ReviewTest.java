package team.placeholder.internalprojectsmanagementsystem.model.project;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;

class ReviewTest {

    @MockBean
    private Review review;

    @MockBean
    private Project project;

    @BeforeEach
    public void setUp() {
        review = new Review();
    }

    @Test
    public void testConstructor() {
        assertNotNull(review);
    }

    @Test
    public void testId() {
        review.setId(1L);
        assertEquals(1L, review.getId());
    }

    @Test
    public void testInternalReviewCount() {
        review.setInternal_review_count(5);
        assertEquals(5, review.getInternal_review_count());
    }

    @Test
    public void testExternalReviewCount() {
        review.setExternal_review_count(3);
        assertEquals(3, review.getExternal_review_count());
    }

    @Test
    public void testProject() {
        review.setProject(project);
        assertEquals(project, review.getProject());
    }


}