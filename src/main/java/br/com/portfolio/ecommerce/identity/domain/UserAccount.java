package br.com.portfolio.ecommerce.identity.domain;
import java.util.*;
public class UserAccount { private final UUID id; private final String email; private final String passwordHash; private final String role; public UserAccount(UUID id,String email,String passwordHash,String role){this.id=id;this.email=email;this.passwordHash=passwordHash;this.role=role;} public UUID id(){return id;} public String email(){return email;} public String passwordHash(){return passwordHash;} public String role(){return role;} }
