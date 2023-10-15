package team.placeholder.internalprojectsmanagementsystem.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(
                        (auth) -> auth
                                .requestMatchers(
                                        "/resources/**",
                                        "/js/**",
                                        "/css/**",
                                        "/images/**",
                                        "/fragments/**",
                                        "/layout/**",
                                        "/login"
                                ).permitAll()
                                .requestMatchers("/department/list").hasAnyRole("PMO","SDQC")
                                .requestMatchers("/department/**").hasAnyRole("PMO","SDQC","DEPARTMENT_HEAD")
                                .requestMatchers("/project/list").hasAnyRole("PMO","SDQC","PROJECT_MANAGER")
                                .requestMatchers("/project/**").hasAnyRole("PMO","SDQC","EMPLOYEE","FOC","CONTRACT")
                                .requestMatchers("/task", "/issues", "/profile").authenticated()
                                .anyRequest().permitAll()


                ).exceptionHandling(
                (exceptionHandling) -> exceptionHandling
                        .accessDeniedPage("/accessDenied")
        )

                .formLogin(
                        (formLogin) -> formLogin
                                .loginProcessingUrl("/processLogin")
                                .successHandler(
                                        (request, response, authentication) -> {
                                            response.sendRedirect("/");
                                        })
                                .permitAll()
                )
                .logout(
                        (logout) -> logout.logoutUrl("/logout").permitAll()
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
