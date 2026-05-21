package br.com.portfolio.ecommerce.shared.infrastructure;
import org.springframework.context.annotation.*; import org.springframework.security.config.annotation.web.builders.HttpSecurity; import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity; import org.springframework.security.web.SecurityFilterChain;
@Configuration @EnableWebSecurity
public class SecurityConfig {
 @Bean SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
   return http.csrf(csrf -> csrf.disable()).authorizeHttpRequests(auth -> auth
     .requestMatchers("/actuator/**", "/swagger-ui/**", "/v3/api-docs/**").permitAll()
     .anyRequest().permitAll()).build();
 }
}
