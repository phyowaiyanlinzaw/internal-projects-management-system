package team.placeholder.internalprojectsmanagementsystem.security;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class SecurityConfigTest {

    @Autowired
    private MockMvc mockMvc;

    private static Stream<String> rolesProvider() {
        return Stream.of("PMO", "SDQC", "DEPARTMENT_HEAD", "PROJECT_MANAGER", "EMPLOYEE", "CONTRACT", "FOC");
    }

    @ParameterizedTest
    @MethodSource("rolesProvider")
    void testAccessToPage(String role) throws Exception {
        String[] accessibleUrls = {"/", "/task", "/issues", "/profile"};
        String[] restrictedUrls = {"/department/list"};

        for (String url : accessibleUrls) {
            mockMvc.perform(MockMvcRequestBuilders.get(url)
                            .with(SecurityMockMvcRequestPostProcessors.user(role)))
                    .andExpect(MockMvcResultMatchers.status().isOk());
        }

        for (String url : restrictedUrls) {
            mockMvc.perform(MockMvcRequestBuilders.get(url)
                            .with(SecurityMockMvcRequestPostProcessors.user(role)))
                    .andExpect(MockMvcResultMatchers.status().isForbidden());
        }
    }
}
