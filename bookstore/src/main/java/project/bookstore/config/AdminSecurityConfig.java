package project.bookstore.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import project.bookstore.enums.Role;
import project.bookstore.service.UserService;

@Configuration
@EnableWebSecurity
public class AdminSecurityConfig {
    @Primary
    @Bean
    public UserDetailsService userDetailsService() {
        return new UserService();
    }

    @Primary
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Primary
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();

        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());

        return authenticationProvider;
    }


    @Bean
    @Order(1)
    public SecurityFilterChain adminFilterChain(HttpSecurity http) throws Exception {
        http.authenticationProvider(authenticationProvider());

        http.securityMatcher("/admin**")
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/login").permitAll()
                        .requestMatchers("/admin**").hasAuthority(Role.ADMIN.toString())
                        .anyRequest().authenticated()
                )
                .formLogin(login -> login
                        .loginPage("/admin/login")
                        .usernameParameter("email")
                        .loginProcessingUrl("/admin/login")
                        .defaultSuccessUrl("/admin-dashboard", true)
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/admin/logout")
                        .logoutSuccessUrl("/")
                        .permitAll()
                        .deleteCookies("JSESSIONID")
                )
                .exceptionHandling(exceptionHandling -> exceptionHandling
                        .accessDeniedHandler(accessDeniedHandler())
                        .authenticationEntryPoint((request, response, authException) -> response.sendRedirect("/admin/login"))
                )
                .rememberMe(rememberMe -> rememberMe
                        .alwaysRemember(false)
                        .key("uniqueAndSecret")
                        .userDetailsService(userDetailsService())
                        .tokenValiditySeconds(86400)
                )
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
                );

        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring()
                .requestMatchers("/static/**", "/webjar/**");
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return (request, response, accessDeniedException) -> {
            response.sendRedirect("/404");
        };
    }
}