package team.placeholder.internalprojectsmanagementsystem.dto.model.project;

import org.junit.jupiter.api.Test;
import team.placeholder.internalprojectsmanagementsystem.dto.model.department.DepartmentDto;
import team.placeholder.internalprojectsmanagementsystem.dto.model.issue.IssueDto;
import team.placeholder.internalprojectsmanagementsystem.dto.model.project.*;
import team.placeholder.internalprojectsmanagementsystem.dto.model.user.ClientDto;
import team.placeholder.internalprojectsmanagementsystem.dto.model.user.UserDto;
import team.placeholder.internalprojectsmanagementsystem.model.project.projectenums.DevelopmentPhase;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class ProjectDtoTest {

    @Test
    void testGetterAndSetter() {
        // Arrange
        ProjectDto projectDto = new ProjectDto();

        // Set values using setter methods
        projectDto.setId(1L);
        projectDto.setName("Project1");
        projectDto.setBackground("Background1");
        projectDto.setDuration(30);
        projectDto.setStart_date(System.currentTimeMillis());
        projectDto.setEnd_date(System.currentTimeMillis() + 2592000000L); // Adding 30 days in milliseconds
        projectDto.setCurrent_phase(DevelopmentPhase.PLANNING);
        projectDto.setObjective("Objective1");

        ClientDto clientDto = new ClientDto();
        projectDto.setClientDto(clientDto);

        UserDto projectManagerUserDto = new UserDto();
        projectDto.setProjectManagerUserDto(projectManagerUserDto);

        DepartmentDto departmentDto = new DepartmentDto();
        projectDto.setDepartmentDto(departmentDto);

        AmountDto amountDto = new AmountDto();
        projectDto.setAmountDto(amountDto);

        projectDto.setArchitectureDto(new HashSet<>(Arrays.asList(new ArchitectureDto())));
        projectDto.setReviewDto(new ReviewDto());
        projectDto.setSystemOutLineDto(new SystemOutLineDto());
        projectDto.setDeliverableDto(Arrays.asList(new DeliverableDto()));
        projectDto.setCompleteTaskCount(1L);
        projectDto.setTotalTaskCount(1L);
        projectDto.setStatus("Status1");
        projectDto.setMembersUserDto(Arrays.asList(new UserDto()));
        projectDto.setTasksDto(Arrays.asList(new TasksDto()));
        projectDto.setIssueDto(Arrays.asList(new IssueDto()));

        projectDto.setAmountDto(amountDto);

        ArchitectureDto architectureDto = new ArchitectureDto();
        projectDto.setArchitectureDto(new HashSet<>(Arrays.asList(architectureDto)));

        ReviewDto reviewDto = new ReviewDto();
        projectDto.setReviewDto(reviewDto);

        SystemOutLineDto systemOutLineDto = new SystemOutLineDto();
        projectDto.setSystemOutLineDto(systemOutLineDto);

        DeliverableDto deliverableDto = new DeliverableDto();
        projectDto.setDeliverableDto(Arrays.asList(deliverableDto));

        // Act
        Long id = projectDto.getId();
        String name = projectDto.getName();
        String background = projectDto.getBackground();
        int duration = projectDto.getDuration();
        long start_date = projectDto.getStart_date();
        long end_date = projectDto.getEnd_date();
        DevelopmentPhase current_phase = projectDto.getCurrent_phase();
        String objective = projectDto.getObjective();
        ClientDto retrievedClientDto = projectDto.getClientDto();
        UserDto retrievedProjectManagerUserDto = projectDto.getProjectManagerUserDto();
        DepartmentDto retrievedDepartmentDto = projectDto.getDepartmentDto();
        AmountDto retrievedAmountDto = projectDto.getAmountDto();
        Set<ArchitectureDto> retrievedArchitectureDto = projectDto.getArchitectureDto();
        ReviewDto retrievedReviewDto = projectDto.getReviewDto();
        SystemOutLineDto retrievedSystemOutLineDto = projectDto.getSystemOutLineDto();
        List<DeliverableDto> retrievedDeliverableDto = projectDto.getDeliverableDto();
        Long retrievedCompleteTaskCount = projectDto.getCompleteTaskCount();
        Long retrievedTotalTaskCount = projectDto.getTotalTaskCount();
        String retrievedStatus = projectDto.getStatus();
        List<UserDto> retrievedMembersUserDto = projectDto.getMembersUserDto();
        List<TasksDto> retrievedTasksDto = projectDto.getTasksDto();
        List<IssueDto> retrievedIssueDto = projectDto.getIssueDto();

        // Assert
        assertEquals("Project1", name);
        assertEquals("Background1", background);
        assertEquals(30, duration);
        assertTrue(start_date > 0);
        assertTrue(end_date > start_date);
        assertEquals(DevelopmentPhase.PLANNING, current_phase);
        assertEquals("Objective1", objective);
        assertEquals(clientDto, retrievedClientDto);
        assertEquals(projectManagerUserDto, retrievedProjectManagerUserDto);
        assertEquals(departmentDto, retrievedDepartmentDto);
        assertEquals(amountDto, retrievedAmountDto);
        assertEquals(1, retrievedArchitectureDto.size());
        assertEquals(reviewDto, retrievedReviewDto);
        assertEquals(systemOutLineDto, retrievedSystemOutLineDto);
        assertEquals(1, retrievedDeliverableDto.size());
        assertEquals(1L, retrievedCompleteTaskCount);
        assertEquals(1L, retrievedTotalTaskCount);
        assertEquals(1L, id);
        assertEquals("Status1", retrievedStatus);
        assertEquals(1, retrievedMembersUserDto.size());
        assertEquals(1, retrievedTasksDto.size());
        assertEquals(1, retrievedIssueDto.size());
    }

    @Test
    void testConstructor() {
        // Arrange
        Long id = 1L;
        DepartmentDto departmentDto = new DepartmentDto();
        String name = "Project1";

        // Act
        ProjectDto projectDto = new ProjectDto(id, departmentDto, name);

        // Assert
        assertEquals(id, projectDto.getId());
        assertEquals(departmentDto, projectDto.getDepartmentDto());
        assertEquals(name, projectDto.getName());
    }
}
