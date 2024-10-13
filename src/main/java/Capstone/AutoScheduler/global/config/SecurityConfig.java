package Capstone.AutoScheduler.global.config;

import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .httpBasic(httpBasic -> httpBasic.disable())
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(
                        authorize -> authorize
                                // Member 관련 접근
                                .requestMatchers("/members/sign-up", "/members/sign-in").permitAll()
                                .requestMatchers("/members/**").permitAll()
                                // Event 관련 접근
                                .requestMatchers("/events/", "/events/{eventId}", "/events/member/{memberId}", "/events/member/{memberId}/event/{eventId}", "/events/member/{memberId}/date/{date}").permitAll()
                                // 기타 관련 접근
                                .requestMatchers("/", "/api-docs/**", "/api-docs/swagger-config/*", "/swagger-ui/*", "/swagger-ui/**", "/v3/api-docs/**").permitAll()
                                .anyRequest().authenticated()
                )
                //.addFilterBefore(new JwtAuthenticationFilter(), SecurityFilterChain.class)
                .build();
    }
}
