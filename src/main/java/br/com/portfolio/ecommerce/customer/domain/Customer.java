package br.com.portfolio.ecommerce.customer.domain;
import br.com.portfolio.ecommerce.shared.domain.BusinessException; import java.util.*;
public class Customer { private final UUID id; private final String name; private final String email; private final boolean active; public Customer(UUID id,String name,String email,boolean active){this.id=id;this.name=name;this.email=email;this.active=active;} public void ensureActive(){ if(!active) throw new BusinessException("Cliente inativo"); } public UUID id(){return id;} public String name(){return name;} public String email(){return email;} }
