package team.placeholder.internalprojectsmanagementsystem.controller.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import team.placeholder.internalprojectsmanagementsystem.dto.model.issue.IssueDto;
import team.placeholder.internalprojectsmanagementsystem.service.impl.issue.IssueServiceImpl;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
class IssueControllerTest {

    @Mock
    private IssueServiceImpl issueService;

    @InjectMocks
    private IssueController issueController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(issueController).build();
    }

    @Test
    void getAllIssues() throws Exception {
        IssueDto issueDto = new IssueDto();
        List<IssueDto> issueDtos = Arrays.asList(issueDto);

        when(issueService.getAllIssues()).thenReturn(issueDtos);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/issue/lists"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andReturn();
    }

    private String asJsonString(final Object obj) {
        try {
            final ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void testSaveWhenIssueSavedThenReturnIssueDto() throws Exception {
        IssueDto issueDto = new IssueDto();
        issueDto.setTitle("Test Issue");
        issueDto.setDescription("This is a test issue");

        when(issueService.save(eq(issueDto))).thenReturn(issueDto);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/issue/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(issueDto)))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(asJsonString(issueDto)));
    }

    @Test
    void testSaveWhenIssueNotSavedThenReturnBadRequest() throws Exception {
        IssueDto issueDto = new IssueDto();

        when(issueService.save(eq(issueDto))).thenReturn(null);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/issue/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(issueDto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testSaveWhenExceptionThrownThenReturnBadRequest() throws Exception {
        IssueDto issueDto = new IssueDto();

        when(issueService.save(eq(issueDto))).thenThrow(new RuntimeException("Some error message"));

        mockMvc.perform(MockMvcRequestBuilders.post("/api/issue/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(issueDto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void getIssueById() throws Exception {
        IssueDto issueDto = new IssueDto();
        issueDto.setId(1L);

        when(issueService.getIssueById(1L)).thenReturn(issueDto);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/issue/lists/byId/1"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(asJsonString(issueDto)));
    }

    @Test
    void updateIssue() throws Exception {
        IssueDto issueDto = new IssueDto();
        issueDto.setId(1L);
        issueDto.setTitle("Updated Issue");

        when(issueService.updateIssue(any(IssueDto.class))).thenReturn(issueDto);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/issue/update/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(issueDto)))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(asJsonString(issueDto)));
    }

    @Test
    void getIssueByTitle() throws Exception {
        IssueDto issueDto = new IssueDto();
        issueDto.setTitle("Test Issue");

        when(issueService.getIssueByTitle("Test Issue")).thenReturn(issueDto);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/issue/lists/byTitle/Test Issue"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(asJsonString(issueDto)));
    }

    @Test
    void deleteIssue() throws Exception {
        IssueDto issueDto = new IssueDto();
        issueDto.setId(1L);

        Mockito.doNothing().when(issueService).deleteIssue(1L);

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/issue/delete/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(issueDto)))
                .andExpect(status().isOk());
    }

    @Test
    void testSave() throws Exception {
        // Create a sample IssueDto
        IssueDto issueDto = new IssueDto();
        issueDto.setTitle("Test Issue");
        issueDto.setDescription("This is a test issue");

        when(issueService.save(any(IssueDto.class)))
                .thenReturn(issueDto);

        mockMvc.perform(MockMvcRequestBuilders.post("/aopi/issue/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(issueDto)))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("Test Issue"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value("This is a test issue"));

        // You can add more assertions based on your specific use case
    }
}