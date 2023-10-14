package team.placeholder.internalprojectsmanagementsystem.security;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

@SpringBootTest
@AutoConfigureMockMvc
class SecurityConfigTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AuthenticationProvider authenticationProvider;

    @MockBean
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

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

    @BeforeEach
    void setUp() {

        String  password = passwordEncoder.encode("user123");
        // Define a mock UserDetails object
        UserDetails userDetails = User.builder()
                .username("user")
                .password(password)
                .roles("PMO")
                .build();

        // Configure the behavior of the mock userDetailsService
        Mockito.when(userDetailsService.loadUserByUsername("user"))
                .thenReturn(userDetails);
    }

    @Test
    void testAuthenticationProvider() {
        // Use the mocked userDetailsService
        UserDetails userDetails = userDetailsService.loadUserByUsername("user");

        // Create an Authentication object
        Authentication authentication = new UsernamePasswordAuthenticationToken("user", "user123");

        // Authenticate using the AuthenticationProvider
        Authentication result = authenticationProvider.authenticate(authentication);

        // Assert that authentication was successful
        Assertions.assertTrue(result.isAuthenticated());
        Assertions.assertEquals(userDetails, result.getPrincipal());
    }

}
