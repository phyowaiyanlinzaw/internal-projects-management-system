package team.placeholder.internalprojectsmanagementsystem.security;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import team.placeholder.internalprojectsmanagementsystem.model.user.User;
import team.placeholder.internalprojectsmanagementsystem.repository.user.UserRepository;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class CustomUserDetailsServiceTest {

    private CustomUserDetailsService userDetailsService;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        userDetailsService = new CustomUserDetailsService(userRepository);
    }

    @Test
    public void testLoadUserByUsername() {
        when(userRepository.findByName("testUser")).thenReturn(new User());

        userDetailsService.loadUserByUsername("testUser");

        verify(userRepository, times(1)).findByName("testUser");
    }

    @Test
    public void testLoadUserByUsernameUserNotFound() {
        when(userRepository.findByName("nonExistentUser")).thenReturn(null);

        assertThrows(
                UsernameNotFoundException.class,
                () -> userDetailsService.loadUserByUsername("nonExistentUser")
        );

        verify(userRepository, times(1)).findByName("nonExistentUser");
    }
}
