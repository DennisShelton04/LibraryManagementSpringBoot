package com.librarymanagement.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // Authentication
    @Bean
    public UserDetailsService userDetailsService() {
        // Hard codeing username and password for security
        // UserDetails admin = User.withUsername("admin")
        // .password(passwordEncoder.encode("admin"))
        // .roles("ADMIN")
        // .build();
        // UserDetails user = User.withUsername("user")
        // .password(passwordEncoder.encode("user"))
        // .roles("USER")
        // .build();
        // return new InMemoryUserDetailsManager(admin, user);
        // Now userdetailsService will fetch the username and password from database
        return new UserinfoUserDetailService();
    }

    // Autherization
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity security) throws Exception {
        return security.csrf().disable()
                .authorizeHttpRequests().requestMatchers("/admin/**").hasRole("ADMIN")
                .and().authorizeHttpRequests().requestMatchers("/librarian/**").hasRole("USER")
                .anyRequest().authenticated()
                .and().formLogin()

                .and().logout().invalidateHttpSession(true)
                .and().build();
    }

    // PasswordEncoder
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Also need an Authenticationprovider to create an instance to communicate with
    // UserInfoService
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService());
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }

}
