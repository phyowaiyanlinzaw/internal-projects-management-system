//package team.placeholder.internalprojectsmanagementsystem.model.project;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import team.placeholder.internalprojectsmanagementsystem.model.user.User;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//public class ReviewTest {
//    @MockBean
//    private Review review1;
//    @MockBean
//    private Review review2;
//    @MockBean
//    private Project project;
//
//    @MockBean
//    private User user;
//
//    @BeforeEach
//    public void setUp() {
//        review1 = new Review();
//        review1.setId(1L);
//        review1.setInternal_review_count(3);
//        review1.setExternal_review_count(5);
//
//        review2 = new Review();
//        review2.setId(2L);
//        review2.setInternal_review_count(2);
//        review2.setExternal_review_count(4);
//
//        project = new Project();
//        project.setId(101L);
//        review1.setProject(project);
//
//        user = new User();
//        user.setId(201L);
//        review1.setUser(user);
//    }
//
//    @Test
//    public void testConstructor() {
//        assertNotNull(review1);
//        assertNotNull(review2);
//    }
//
//
//    @Test
//    public void testId() {
//        assertEquals(1L, review1.getId());
//        assertEquals(2L, review2.getId());
//    }
//
//    @Test
//    public void testInternalReviewCount() {
//        assertEquals(3, review1.getInternal_review_count());
//        assertEquals(2, review2.getInternal_review_count());
//    }
//
//    @Test
//    public void testExternalReviewCount() {
//        assertEquals(5, review1.getExternal_review_count());
//        assertEquals(4, review2.getExternal_review_count());
//    }
//
//    @Test
//    public void testProject() {
//        assertEquals(project, review1.getProject());
//        assertNull(review2.getProject());
//    }
//
//    @Test
//    public void testUser() {
//        assertEquals(user, review1.getUser());
//        assertNull(review2.getUser());
//    }
//
//    @Test
//    public void testEquals() {
//        assertEquals(review1, review1);
//        Review review1Copy = new Review();
//        review1Copy.setId(1L);
//        assertEquals(review1, review1Copy);
//        assertNotEquals(review1, review2);
//        assertNotEquals("Not a Review object", review1);
//    }
//
//    @Test
//    public void testHashCode() {
//        assertEquals(review1.hashCode(), review1.hashCode());
//        Review review1Copy = new Review();
//        review1Copy.setId(1L);
//        assertEquals(review1.hashCode(), review1Copy.hashCode());
//        assertNotEquals(review1.hashCode(), review2.hashCode());
//    }
//}
