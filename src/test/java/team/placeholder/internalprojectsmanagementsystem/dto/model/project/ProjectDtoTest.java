package team.placeholder.internalprojectsmanagementsystem.dto.model.project;


import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import team.placeholder.internalprojectsmanagementsystem.dto.model.department.DepartmentDto;
import team.placeholder.internalprojectsmanagementsystem.dto.model.issue.IssueDto;
import team.placeholder.internalprojectsmanagementsystem.dto.model.user.ClientDto;
import team.placeholder.internalprojectsmanagementsystem.dto.model.user.UserDto;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

@Slf4j
public class ProjectDtoTest {

    @Test
    void testEqualsAndHashCode() {
    // Mock dependencies
    ClientDto clientDto1 = mock(ClientDto.class);
    UserDto userDto1 = mock(UserDto.class);
    DepartmentDto departmentDto1 = mock(DepartmentDto.class);
    AmountDto amountDto1 = mock(AmountDto.class);
    Set<ArchitectureDto> architectureDto1 = new HashSet<>();
    ReviewDto reviewDto1 = mock(ReviewDto.class);
    SystemOutLineDto systemOutLineDto1 = mock(SystemOutLineDto.class);
    List<DeliverableDto> deliverableDto1 = mock(List.class);
    List<UserDto> membersUserDto1 = mock(List.class);
    List<TasksDto> tasksDto1 = mock(List.class);
    List<IssueDto> issueDto1 = mock(List.class);

    ClientDto clientDto2 = mock(ClientDto.class);
    UserDto userDto2 = mock(UserDto.class);
    DepartmentDto departmentDto2 = mock(DepartmentDto.class);
    AmountDto amountDto2 = mock(AmountDto.class);
    Set<ArchitectureDto> architectureDto2 = new HashSet<>();
    ReviewDto reviewDto2 = mock(ReviewDto.class);
    SystemOutLineDto systemOutLineDto2 = mock(SystemOutLineDto.class);
    List<DeliverableDto> deliverableDto2 = mock(List.class);
    List<UserDto> membersUserDto2 = mock(List.class);
    List<TasksDto> tasksDto2 = mock(List.class);
    List<IssueDto> issueDto2 = mock(List.class);

        ProjectDto projectDto1 = new ProjectDto(1L, departmentDto1, "Project 1");
        projectDto1.setClientDto(clientDto1);
        projectDto1.setProjectManagerUserDto(userDto1);
        projectDto1.setAmountDto(amountDto1);
        projectDto1.setArchitectureDto(architectureDto1);
        projectDto1.setReviewDto(reviewDto1);
        projectDto1.setSystemOutLineDto(systemOutLineDto1);
        projectDto1.setDeliverableDto(deliverableDto1);
        projectDto1.setCompleteTaskCount(10L);
        projectDto1.setTotalTaskCount(20L);
        projectDto1.setClosed(true);
        projectDto1.setMembersUserDto(membersUserDto1);
        projectDto1.setTasksDto(tasksDto1);
        projectDto1.setIssueDto(issueDto1);

        ProjectDto projectDto2 = new ProjectDto(1L, departmentDto2, "Project 1");
        projectDto2.setClientDto(clientDto2);
        projectDto2.setProjectManagerUserDto(userDto2);
        projectDto2.setAmountDto(amountDto2);
        projectDto2.setArchitectureDto(architectureDto2);
        projectDto2.setReviewDto(reviewDto2);
        projectDto2.setSystemOutLineDto(systemOutLineDto2);
        projectDto2.setDeliverableDto(deliverableDto2);
        projectDto2.setCompleteTaskCount(10L);
        projectDto2.setTotalTaskCount(20L);
        projectDto2.setClosed(true);
        projectDto2.setMembersUserDto(membersUserDto2);
        projectDto2.setTasksDto(tasksDto2);
        projectDto2.setIssueDto(issueDto2);

        // Test equals and hashCode methods
        assertTrue(projectDto1.equals(projectDto2));
        assertEquals(projectDto1.hashCode(), projectDto2.hashCode());
}

    @Test
    void testNotEquals() {
        // Mock dependencies
        DepartmentDto departmentDto1 = mock(DepartmentDto.class);
        DepartmentDto departmentDto2 = mock(DepartmentDto.class);

        // Create two ProjectDto instances with different values
        ProjectDto projectDto1 = new ProjectDto(1L, departmentDto1, "Project 1");
        ProjectDto projectDto2 = new ProjectDto(2L, departmentDto2, "Project 2");

        // Test equals and hashCode methods
        assertFalse(projectDto1.equals(projectDto2) || projectDto2.equals(projectDto1));
        assertFalse(projectDto1.hashCode() == projectDto2.hashCode());
    }

}
