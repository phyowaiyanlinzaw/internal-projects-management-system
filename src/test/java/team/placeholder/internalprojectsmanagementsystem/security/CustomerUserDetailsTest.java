package team.placeholder.internalprojectsmanagementsystem.security;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import team.placeholder.internalprojectsmanagementsystem.model.user.User;
import team.placeholder.internalprojectsmanagementsystem.model.user.userenums.Role;
import team.placeholder.internalprojectsmanagementsystem.security.CustomerUserDetails;

import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CustomerUserDetailsTest {

    private CustomerUserDetails userDetails;
    @Mock
    private User user;

    @BeforeEach
    public void setUp() {
        user = new User();
        user.setName("admin");
        user.setPassword("admin123");

        userDetails = new CustomerUserDetails(user);
    }

    @Test
    public void testGetAuthorities() {
        Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
        List<SimpleGrantedAuthority> expectedAuthorities = Role.stream()
                .map(role -> new SimpleGrantedAuthority(role.name()))
                .toList();

        assertEquals(expectedAuthorities, authorities);
    }

    @Test
    public void testGetPassword() {
        String password = userDetails.getPassword();
        assertEquals("admin123", password);
    }

    @Test
    public void testGetUsername() {
        String username = userDetails.getUsername();
        assertEquals("admin", username);
    }

    @Test
    public void testIsAccountNonExpired() {
        boolean accountNonExpired = userDetails.isAccountNonExpired();
        assertEquals(true, accountNonExpired);
    }

    @Test
    public void testIsAccountNonLocked() {
        boolean accountNonLocked = userDetails.isAccountNonLocked();
        assertEquals(true, accountNonLocked);
    }

    @Test
    public void testIsCredentialsNonExpired() {
        boolean credentialsNonExpired = userDetails.isCredentialsNonExpired();
        assertEquals(true, credentialsNonExpired);
    }

    @Test
    public void testIsEnabled() {
        boolean enabled = userDetails.isEnabled();
        assertEquals(true, enabled);
    }
}
