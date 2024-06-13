package com.example.press_lab.config;


import com.example.press_lab.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.http.HttpMethod.*;

@Configuration
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfig {

    private final JwtAuthFilter jwtAuthFilter;
//    private final CustomEntryPoint customEntryPoint;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.csrf(AbstractHttpConfigurer::disable);
        http.authorizeHttpRequests((authz) ->
                authz.requestMatchers( "/advertisements/create", "/advertisements/all", "/advertisements/by-content" , "/advertisements/by-image").permitAll()
                        .requestMatchers("/categories/create", "/categories/all").permitAll()
                        .requestMatchers("/kart/create", "/kart/readAll", "/kart/readByContent", "/kart/readByFkNewsId").permitAll()
                        .requestMatchers("/news/**").permitAll()
                        .requestMatchers("/subcategories/**").permitAll()
                        .requestMatchers("/subscriptions/create").permitAll().
                         requestMatchers(POST, "/v1/users/registration", "/v1/users/resendVerificationCode", "/v1/users/verification").permitAll()
                        .requestMatchers(PUT,"/v1/users/authenticate").permitAll()
                        .requestMatchers(GET,"/v1/users/login").permitAll()

                        .anyRequest().authenticated()
                );
//        http.httpBasic(basic -> basic.authenticationEntryPoint(customEntryPoint));
        http.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        http.exceptionHandling(Customizer.withDefaults());
        return http.build();

    }


//    @Bean
//    public WebSecurityCustomizer webSecurityCustomizer() {
//        return (web) -> web.ignoring().requestMatchers("/v2/api-docs",
//                "/swagger-resources",
//                "/swagger-resources/**",
//                "/configuration/ui",
//                "/configuration/security",
//                "/swagger-ui.html",
//                "/webjars/**",
//                // -- Swagger UI v3 (OpenAPI)
//                "/v3/api-docs/**",
//                "/swagger-ui/**");
//    }





//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
//        return configuration.getAuthenticationManager();
//    }


//    @Bean
//    public UserDetailsService users() {
//
//
//
//
//
//
//        User user1 = (User) User.builder()
//                .username("mubarizhabiboglu@gmail.com")
//                .password(passwordEncoder.encode("press@#lab2019guys!?"))
//                .roles("ADMIN")
//                .accountExpired(true)
//                .accountLocked(true)
//                .credentialsExpired(true)
//                .disabled(true)
//                .build();
//        userRepository.save(user1);
//        UserDetails admin = users
//                .username("admin")
//                .password("password")
//                .roles("USER", "ADMIN")
//                .build();
//        return new InMemoryUserDetailsManager(user1, admin);
//    }


}
