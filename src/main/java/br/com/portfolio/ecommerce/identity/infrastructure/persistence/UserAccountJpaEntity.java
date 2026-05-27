package br.com.portfolio.ecommerce.identity.infrastructure.persistence;
import jakarta.persistence.*; import java.util.*;
@Entity @Table(name="user_accounts") public class UserAccountJpaEntity { @Id private UUID id; @Column(nullable=false,unique=true) private String email; @Column(nullable=false) private String passwordHash; @Column(nullable=false) private String role; protected UserAccountJpaEntity(){} public UUID getId(){return id;} public String getEmail(){return email;} public String getPasswordHash(){return passwordHash;} public String getRole(){return role;} }
