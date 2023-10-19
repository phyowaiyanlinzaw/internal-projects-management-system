package team.placeholder.internalprojectsmanagementsystem.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.SecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
//@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http    .csrf(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(
                        (auth) -> auth
                                .requestMatchers(
                                        "/resources/**",
                                        "/js/**",
                                        "/css/**",
                                        "/images/**",
                                        "/fragments/**",
                                        "/font/**",
                                        "/lib/**",
                                        "/layout/**"
                                ).permitAll()
                                .requestMatchers("/login").permitAll()
                                .requestMatchers("/department/**").hasAnyRole("PMO","SDQC","DEPARTMENT_HEAD")
                                .requestMatchers("/").authenticated()
                                .anyRequest().authenticated()
                ).exceptionHandling(
                (exceptionHandling) -> exceptionHandling
                        .accessDeniedPage("/accessDenied")
        )
                .formLogin(
                        (formLogin) -> formLogin
                                .loginPage("/login")
                                .loginProcessingUrl("/processLogin")
                                .successHandler(
                                        (request, response, authentication) -> {
                                            response.sendRedirect("/");
                                        })
                                .permitAll()
                )
                .logout(
                        (logout) -> logout
                                .invalidateHttpSession(true)
                                .clearAuthentication(true)
                                .deleteCookies("JSESSIONID")
                                .logoutSuccessHandler(
                                        (request, response, authentication) -> {
                                            response.sendRedirect("/login");
                                        })
                                .permitAll()
                ).rememberMe(
                (rememberMe) -> rememberMe
                        .key("my-secure-key")
                        .rememberMeParameter("remember-me")
                );
        return http.build();

    }

    private final CustomUserDetailsService userDetailsService;
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
