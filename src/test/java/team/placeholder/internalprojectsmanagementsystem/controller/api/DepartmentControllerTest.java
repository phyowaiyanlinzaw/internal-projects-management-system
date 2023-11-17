//package team.placeholder.internalprojectsmanagementsystem.controller.api;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.MvcResult;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//import team.placeholder.internalprojectsmanagementsystem.dto.model.department.DepartmentDto;
//import team.placeholder.internalprojectsmanagementsystem.service.FakerService;
//import team.placeholder.internalprojectsmanagementsystem.service.impl.department.DepartmentServiceImpl;
//
//import java.util.Arrays;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@ExtendWith({SpringExtension.class, MockitoExtension.class})
//@SpringBootTest
//@AutoConfigureMockMvc
//class DepartmentControllerTest {
//
//    @Mock
//    private DepartmentServiceImpl departmentService;
//
//    @Mock
//    private FakerService fakerService;
//
//    @InjectMocks
//    private DepartmentController departmentController;
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Autowired
//    private ObjectMapper objectMapper;
//
//    @Test
//    public void testGetAllDepartments() throws Exception {
//        // Mock the service method to return a sample list of DepartmentDto
//        when(departmentService.getAllDepartments()).thenReturn(Arrays.asList(new DepartmentDto(), new DepartmentDto()));
//
//        // Perform the GET request to the "/api/department/list" endpoint
//        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/api/department/list")
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andReturn();
//
//        // Add additional assertions if needed
//    }
//
//    @Test
//    public void testSaveDepartment() throws Exception {
//        // Create a sample DepartmentDto
//        DepartmentDto departmentDto = new DepartmentDto();
//        departmentDto.setName("Test Department");
//
//        // Mock the service method to return the saved DepartmentDto
//        when(departmentService.save(any(DepartmentDto.class))).thenReturn(departmentDto);
//
//        // Perform the POST request to the "/api/department/save" endpoint
//        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/api/department/save")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(departmentDto))) // Convert DepartmentDto to JSON
//                .andExpect(status().isOk()) // Expect HTTP 200 OK
//                .andReturn();
//
//        // Validate the response content
//        String responseContent = result.getResponse().getContentAsString();
//        // You may add more specific assertions based on your application logic
//        assert responseContent.contains("Department saved successfully");
//    }
//
//
//
//}