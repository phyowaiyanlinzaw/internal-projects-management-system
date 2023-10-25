//package team.placeholder.internalprojectsmanagementsystem.service.impl.issue;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.mockito.junit.jupiter.MockitoExtension;
//import team.placeholder.internalprojectsmanagementsystem.model.department.Department;
//import team.placeholder.internalprojectsmanagementsystem.model.issue.Issue;
//import team.placeholder.internalprojectsmanagementsystem.model.issue.IssueCategory;
//import team.placeholder.internalprojectsmanagementsystem.model.project.Project;
//import team.placeholder.internalprojectsmanagementsystem.model.user.User;
//import team.placeholder.internalprojectsmanagementsystem.repository.issue.IssueRepository;
//
//import java.sql.Date;
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//class IssueServiceImplTest {
//
//    @Mock
//    private IssueRepository issueRepository;
//
//    @InjectMocks
//    private IssueServiceImpl issueService;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.initMocks(this);
//    }
//
//    @Test
//    public void testSaveIssue() {
//        Issue issue = new Issue();
//        issue.setTitle("Test");
//        issue.setDescription("Test");
//        issue.setPlace("Test");
//        issue.setImpact("Test");
//        issue.setRoot_cause("Test");
//        issue.setDirect_cause("Test");
//        issue.setCorrective_action("Test");
//        issue.setPreventive_action("Test");
//        issue.setClint_or_user(1);
//        issue.setSolved(true);
//
//        Date createdDate = new Date(System.currentTimeMillis());
//        Date updatedDate = new Date(System.currentTimeMillis());
//        issue.setCreated_date(createdDate);
//        issue.setUpdated_date(updatedDate);
//
//        issue.setIssueCategory(new IssueCategory());
//        issue.setProject(new Project());
//        issue.setUser_uploader(new User());
//        issue.setUser_pic(new User());
//        issueRepository.save(issue);
//
//        verify(issueRepository, times(1)).save(issue);
//    }
//
//    @Test
//    public void testGetAllIssues() {
//        List<Issue> list = new ArrayList<>();
//        Issue issue1 = new Issue();
//        issue1.setTitle("Test");
//        issue1.setDescription("Test");
//        issue1.setPlace("Test");
//        issue1.setImpact("Test");
//        issue1.setRoot_cause("Test");
//        issue1.setDirect_cause("Test");
//        issue1.setCorrective_action("Test");
//        issue1.setPreventive_action("Test");
//        issue1.setClint_or_user(1);
//        issue1.setSolved(true);
//        Date createdDate = new Date(System.currentTimeMillis());
//        Date updatedDate = new Date(System.currentTimeMillis());
//        issue1.setCreated_date(createdDate);
//        issue1.setUpdated_date(updatedDate);
//        issue1.setIssueCategory(new IssueCategory());
//        issue1.setProject(new Project());
//        issue1.setUser_uploader(new User());
//        issue1.setUser_pic(new User());
//        list.add(issue1);
//        when(issueRepository.findAll()).thenReturn(list);
//        List<Issue> issues = issueRepository.findAll();
//        assertEquals(1, issues.size());
//        verify(issueRepository, times(1)).findAll();
//    }
//
//    @Test
//    public void testGetAllIssueById() {
//        Issue issue = new Issue();
//        issue.setTitle("Test");
//        issue.setDescription("Test");
//        issue.setPlace("Test");
//        issue.setImpact("Test");
//        issue.setRoot_cause("Test");
//        issue.setDirect_cause("Test");
//        issue.setCorrective_action("Test");
//        issue.setPreventive_action("Test");
//        issue.setClint_or_user(1);
//        issue.setSolved(true);
//        Date createdDate = new Date(System.currentTimeMillis());
//        Date updatedDate = new Date(System.currentTimeMillis());
//        issue.setCreated_date(createdDate);
//        issue.setUpdated_date(updatedDate);
//        issue.setIssueCategory(new IssueCategory());
//        issue.setProject(new Project());
//        issue.setUser_uploader(new User());
//        issue.setUser_pic(new User());
//        when(issueRepository.findById(1L)).thenReturn(issue);
//        Issue issue1 = issueRepository.findById(1L);
//        assertEquals("Test", issue1.getTitle());
//        verify(issueRepository, times(1)).findById(1L);
//    }
//
//    @Test
//    public void testGetAllIssueByName() {
//        Issue issue = new Issue();
//        issue.setTitle("Test");
//        issue.setDescription("Test");
//        issue.setPlace("Test");
//        issue.setImpact("Test");
//        issue.setRoot_cause("Test");
//        issue.setDirect_cause("Test");
//        issue.setCorrective_action("Test");
//        issue.setPreventive_action("Test");
//        issue.setClint_or_user(1);
//        issue.setSolved(true);
//        Date createdDate = new Date(System.currentTimeMillis());
//        Date updatedDate = new Date(System.currentTimeMillis());
//        issue.setCreated_date(createdDate);
//        issue.setUpdated_date(updatedDate);
//        issue.setIssueCategory(new IssueCategory());
//        issue.setProject(new Project());
//        issue.setUser_uploader(new User());
//        issue.setUser_pic(new User());
//        when(issueRepository.findByTitle("Test")).thenReturn(issue);
//        Issue issue1 = issueRepository.findByTitle("Test");
//        assertEquals("Test", issue1.getTitle());
//        verify(issueRepository, times(1)).findByTitle("Test");
//    }
//
//    @Test
//    public void testUpdateIssue() {
//        Issue issue = new Issue();
//        issue.setTitle("Test");
//        issue.setDescription("Test");
//        issue.setPlace("Test");
//        issue.setImpact("Test");
//        issue.setRoot_cause("Test");
//        issue.setDirect_cause("Test");
//        issue.setCorrective_action("Test");
//        issue.setPreventive_action("Test");
//        issue.setClint_or_user(1);
//        issue.setSolved(true);
//        Date createdDate = new Date(System.currentTimeMillis());
//        Date updatedDate = new Date(System.currentTimeMillis());
//        issue.setCreated_date(createdDate);
//        issue.setUpdated_date(updatedDate);
//        issue.setIssueCategory(new IssueCategory());
//        issue.setProject(new Project());
//        issue.setUser_uploader(new User());
//        issue.setUser_pic(new User());
//        when(issueRepository.findById(1L)).thenReturn(issue);
//        Issue issue1 = issueRepository.findById(1L);
//        issue1.setTitle("Test2");
//        issueRepository.save(issue1);
//        assertEquals("Test2", issue1.getTitle());
//        verify(issueRepository, times(1)).save(issue1);
//    }
//
//    @Test
//    public void testDeleteIssue() {
//        Issue issue = new Issue();
//        issue.setTitle("Test");
//        issue.setDescription("Test");
//        issue.setPlace("Test");
//        issue.setImpact("Test");
//        issue.setRoot_cause("Test");
//        issue.setDirect_cause("Test");
//        issue.setCorrective_action("Test");
//        issue.setPreventive_action("Test");
//        issue.setClint_or_user(1);
//        issue.setSolved(true);
//        Date createdDate = new Date(System.currentTimeMillis());
//        Date updatedDate = new Date(System.currentTimeMillis());
//        issue.setCreated_date(createdDate);
//        issue.setUpdated_date(updatedDate);
//        issue.setIssueCategory(new IssueCategory());
//        issue.setProject(new Project());
//        issue.setUser_uploader(new User());
//        issue.setUser_pic(new User());
//        when(issueRepository.findById(1L)).thenReturn(issue);
//        Issue issue1 = issueRepository.findById(1L);
//        issueRepository.delete(issue1);
//        verify(issueRepository, times(1)).delete(issue1);
//
//    }
//}
