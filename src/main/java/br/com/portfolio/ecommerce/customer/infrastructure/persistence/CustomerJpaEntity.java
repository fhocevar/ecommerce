package br.com.portfolio.ecommerce.customer.infrastructure.persistence;
import jakarta.persistence.*; import java.util.*;
@Entity @Table(name="customers") public class CustomerJpaEntity { @Id private UUID id; private String name; private String email; private boolean active; protected CustomerJpaEntity(){} public UUID getId(){return id;} public String getName(){return name;} public String getEmail(){return email;} public boolean isActive(){return active;} }
