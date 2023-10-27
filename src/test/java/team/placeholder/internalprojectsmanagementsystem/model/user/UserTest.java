//package team.placeholder.internalprojectsmanagementsystem.model.user;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;import team.placeholder.internalprojectsmanagementsystem.model.department.Department;
//import team.placeholder.internalprojectsmanagementsystem.model.issue.Issue;
//import team.placeholder.internalprojectsmanagementsystem.model.project.Project;
//import team.placeholder.internalprojectsmanagementsystem.model.project.Review;
//import team.placeholder.internalprojectsmanagementsystem.model.project.Tasks;
//import team.placeholder.internalprojectsmanagementsystem.model.user.userenums.Role;
//import java.util.ArrayList;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//import static org.junit.jupiter.api.Assertions.*;
//
//public class UserTest {
//
//    @Mock
//    private Department department;
//    @Mock
//    private User user;
//
//    @BeforeEach
//    public void setup() {
//        MockitoAnnotations.initMocks(this);
//        user = new User();
//        user.setId(1L);
//        user.setName("John Doe");
//        user.setEmail("john.doe@example.com");
//        user.setPassword("password");
//        user.setRole(Role.EMPLOYEE);
//        user.setDepartment(department);
//
//        List<Tasks> tasks = new ArrayList<>();
//        // Initialize tasks with mock data
//
//        List<Review> reviews = new ArrayList<>();
//        // Initialize reviews with mock data
//
//        Set<Project> projects = new HashSet<>();
//        // Initialize projects with mock data
//
//        List<Issue> uploader = new ArrayList<>();
//        // Initialize user upload list with mock data
//
//        List<Issue> pm = new ArrayList<>();
//        // Initialize user PM list with mock data
//
//        user.setTasks(tasks);
//        user.setReviews(reviews);
//        user.setProjects(projects);
//        user.setUploader(uploader);
//        user.setPm(pm);
//    }
//
//    @Test
//    public void testGetterAndSetterMethods() {
//        // Test getter and setter methods
//        assertEquals(1L, user.getId());
//        assertEquals("John Doe", user.getName());
//        assertEquals("john.doe@example.com", user.getEmail());
//        assertEquals("password", user.getPassword());
//        assertEquals(Role.EMPLOYEE, user.getRole());
//        assertEquals(department, user.getDepartment());
//    }
//
//    @Test
//    public void testProjectsCollection() {
//        // Test the projects collection
//        Set<Project> projects = new HashSet<>();
//        // Initialize projects with mock data
//
//        user.setProjects(projects);
//
//        assertEquals(projects, user.getProjects());
//    }
//
//    @Test
//    public void testTasksList() {
//        // Test the tasks list
//        List<Tasks> tasks = new ArrayList<>();
//        // Initialize tasks with mock data
//
//        user.setTasks(tasks);
//
//        assertEquals(tasks, user.getTasks());
//    }
//
//    @Test
//    public void testReviewsList() {
//        // Test the reviews list
//        List<Review> reviews = new ArrayList<>();
//        // Initialize reviews with mock data
//
//        user.setReviews(reviews);
//
//        assertEquals(reviews, user.getReviews());
//    }
//
//    @Test
//    public void testProjectList() {
//        // Test the project list
//        List<Project> projects = new ArrayList<>();
//        // Initialize projects with mock data
//
//        user.setProject(projects);
//
//        assertEquals(projects, user.getProject());
//    }
//
//    @Test
//    public void testUserUploadList() {
//        // Test the userUpload list
//        List<Issue> uploader = new ArrayList<>();
//        // Initialize userUpload with mock data
//
//        user.setUploader(uploader);
//
//        assertEquals(uploader, user.getUploader());
//    }
//
//    @Test
//    public void testUserPMList() {
//        // Test the userPM list
//        List<Issue> pm = new ArrayList<>();
//        // Initialize userPM with mock data
//
//        user.setPm(pm);
//
//        assertEquals(pm, user.getPm());
//    }
//
//    @Test
//    public void testEqualsAndHashCode() {
//        // Test equals and hashCode methods
//        User anotherUser = new User();
//        anotherUser.setId(1L);
//        anotherUser.setName("John Doe");
//        anotherUser.setEmail("john.doe@example.com");
//        anotherUser.setPassword("password");
//        anotherUser.setRole(Role.EMPLOYEE);
//        anotherUser.setDepartment(department);
//
//        assertEquals(user, anotherUser);
//        assertEquals(user.hashCode(), anotherUser.hashCode());
//    }
//
//
//    // Add more test cases as needed to cover any additional business logic or constraints in the User class.
//}
//
