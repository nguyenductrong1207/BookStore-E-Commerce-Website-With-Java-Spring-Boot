package project.bookstore.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import project.bookstore.service.UserService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {


    @Bean
    public UserDetailsService userDetailsService() {
        return new UserService();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();

        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());

        return authenticationProvider;
    }


    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http.authenticationProvider(authenticationProvider());

                http.authorizeHttpRequests(auth ->
                        auth.requestMatchers("/profile", "/order-history**", "/shop-checkout"
                                        , "/shop-cart/**", "/wishlist").authenticated()
                                .anyRequest().permitAll())
                .formLogin(login ->
                        login.loginPage("/client-login").usernameParameter("email").loginProcessingUrl("/login").defaultSuccessUrl("/").permitAll())
                .logout(logout ->
                        logout.logoutSuccessUrl("/").permitAll().deleteCookies("JSESSIONID"))
                .rememberMe(rememberMe ->
                        rememberMe.alwaysRemember(true).key("uniqueAndSecret")
                                .userDetailsService(userDetailsService()).tokenValiditySeconds(86400))
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.ALWAYS));

        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring()
                .requestMatchers("/static/**", "/webjar/**");
    }

}
