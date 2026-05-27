package br.com.portfolio.ecommerce.identity.api;
import br.com.portfolio.ecommerce.identity.application.AuthService; import jakarta.validation.Valid; import jakarta.validation.constraints.*; import org.springframework.web.bind.annotation.*;
@RestController @RequestMapping("/api/v1/auth")
public class AuthController { private final AuthService service; public AuthController(AuthService service){this.service=service;} @PostMapping("/login") TokenResponse login(@Valid @RequestBody LoginRequest r){ return new TokenResponse(service.login(r.email(), r.password())); } public record LoginRequest(@Email String email,@NotBlank String password){} public record TokenResponse(String accessToken){} }
