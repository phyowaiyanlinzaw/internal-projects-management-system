package team.placeholder.internalprojectsmanagementsystem.security;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;
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
    public void testLoadUserByUsernameWithName() {
        when(userRepository.findByName("testUser")).thenReturn(new User());

        userDetailsService.loadUserByUsername("testUser");

        verify(userRepository, times(1)).findByName("testUser");
    }

    @Test
    public void testLoadUserByUsernameWithEmail() {
        // Mock the repository to return a user when findByEmail is called with any email
        when(userRepository.findByEmail(anyString())).thenReturn(new User());

        userDetailsService.loadUserByUsername("testUser@gmail.com");

        // Verify that findByEmail was called with any email
        verify(userRepository, times(1)).findByEmail(anyString());
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
    @Test
    public void testLoadUserByUsernameWithEmailUserNotFound() {
        when(userRepository.findByEmail("nonexistent@example.com")).thenReturn(null);

        assertThrows(
                UsernameNotFoundException.class,
                () -> userDetailsService.loadUserByUsername("nonexistent@example.com")
        );

        verify(userRepository, times(1)).findByEmail("nonexistent@example.com");
    }

}
