package team.placeholder.internalprojectsmanagementsystem.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final DataSource dataSource;

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
                                        "/layout/**",
                                        "/reset-password",
                                        "/api/user/send-otp/**",
                                        "/api/user/confirm-otp/**",
                                        "/api/user/reset-password/**"
                                ).permitAll()
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
                                            response.sendRedirect("/dashboard");
                                        })
                                .permitAll()
                )
                .logout(
                        (logout) -> logout
                                .logoutUrl("/logout")
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
                        .rememberMeParameter("remember-me")
                        .rememberMeServices(rememberMeServices())
                        .tokenValiditySeconds(864000)//10 days
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

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);
        if (tokenRepository.getJdbcTemplate() == null) {
            tokenRepository.setJdbcTemplate(new JdbcTemplate(dataSource));
        }
        tokenRepository.getJdbcTemplate().execute("CREATE TABLE IF NOT EXISTS persistent_logins (" +
                "username VARCHAR(64) NOT NULL," +
                "series VARCHAR(64) PRIMARY KEY," +
                "token VARCHAR(64) NOT NULL," +
                "last_used TIMESTAMP NOT NULL" +
                ")");

        return tokenRepository;
    }

    @Bean
    public PersistentTokenBasedRememberMeServices rememberMeServices() {
        return new PersistentTokenBasedRememberMeServices("remember-my-pp",userDetailsService,persistentTokenRepository());
    }

}
