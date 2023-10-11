package team.placeholder.internalprojectsmanagementsystem.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import team.placeholder.internalprojectsmanagementsystem.model.user.User;
import team.placeholder.internalprojectsmanagementsystem.model.user.userenums.Role;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class CustomerUserDetails implements UserDetails {

    private final User user;
    public CustomerUserDetails(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return Role.stream()
                .map((role) -> new SimpleGrantedAuthority(role.name()))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}