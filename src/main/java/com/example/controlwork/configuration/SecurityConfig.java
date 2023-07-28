package com.example.controlwork.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    private final DataSource dataSource;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        String fetchUserQuery = "select email, password ,enabled \n " +
                "from users \n" +
                "where email = ?;";

        String fetchRolesQuery = "select email, role  \n " +
                "from users u, \n" +
                "roles r \n" +
                "where u.email = ? \n" +
                "and u.role_id=r.id;";
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery(fetchUserQuery)
                .authoritiesByUsernameQuery(fetchRolesQuery);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy
                        .STATELESS))

                .httpBasic(Customizer.withDefaults())

                .formLogin(AbstractHttpConfigurer::disable)
                .logout(AbstractHttpConfigurer::disable)

                .csrf(AbstractHttpConfigurer::disable)

                .authorizeHttpRequests(authorize -> {
                    authorize
                            .requestMatchers(HttpMethod.POST, "/resumes").hasAuthority("JOB_SEEKER")
                            .requestMatchers(HttpMethod.DELETE,"/resumes").hasAuthority("JOB_SEEKER")
                            .requestMatchers(HttpMethod.PUT,"/resumes").hasAuthority("JOB_SEEKER")
                            .requestMatchers(HttpMethod.POST, "/jobresume").hasAuthority("EMPLOYER")
                            .requestMatchers(HttpMethod.DELETE, "/jobresume").hasAuthority("EMPLOYER")
                            .requestMatchers(HttpMethod.PUT, "/jobresume").hasAuthority("EMPLOYER")
                            .anyRequest().permitAll();
                });
        return http.build();
    }

}
