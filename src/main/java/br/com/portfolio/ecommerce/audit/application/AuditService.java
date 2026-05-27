package br.com.portfolio.ecommerce.audit.application;
import br.com.portfolio.ecommerce.audit.infrastructure.persistence.*; import org.springframework.stereotype.Service; import java.time.*; import java.util.*;
@Service public class AuditService { private final AuditJpaRepository repo; public AuditService(AuditJpaRepository repo){this.repo=repo;} public void register(String action,String details){repo.save(new AuditJpaEntity(UUID.randomUUID(),action,details,Instant.now()));} }
