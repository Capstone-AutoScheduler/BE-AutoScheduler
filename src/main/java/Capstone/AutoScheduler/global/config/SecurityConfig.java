package Capstone.AutoScheduler.global.config;

import lombok.RequiredArgsConstructor;
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
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(
                        authorize -> authorize
                                // Member 관련 접근
                                .requestMatchers("/member/sign-up", "/member/sign-in").permitAll()
                                .requestMatchers("/member/**").permitAll()
                                // Event 관련 접근
                                .requestMatchers("/event/", "/event/{eventId}", "/event/member/{memberId}", "/event/member/{memberId}/event/{eventId}", "/event/member/{memberId}/date/{date}").permitAll()
                                // Generator 관련 접근
                                .requestMatchers("/generator/", "/generator/{generatorId}").permitAll()
                                // Bookmark 관련 접근
                                .requestMatchers("/bookmark/add/{memberId}/{generatorId}", "/bookmark/delete/{bookmarkId}" ).permitAll()
                                // Crawling 관련 접근
                                .requestMatchers("/crawl").permitAll() // /crawl 경로 접근 허용
                                // 기타 관련 접근
                                .requestMatchers("/", "/api-docs/**", "/api-docs/swagger-config/*", "/swagger-ui/*", "/swagger-ui/**", "/v3/api-docs/**").permitAll()
                                .anyRequest().authenticated()
                )
                //.addFilterBefore(new JwtAuthenticationFilter(), SecurityFilterChain.class)
                .build();
    }
}
