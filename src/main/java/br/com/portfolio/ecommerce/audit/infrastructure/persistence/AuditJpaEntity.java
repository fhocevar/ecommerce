package br.com.portfolio.ecommerce.audit.infrastructure.persistence;
import jakarta.persistence.*; import java.time.*; import java.util.*;
@Entity @Table(name="audit_logs") public class AuditJpaEntity { @Id private UUID id; private String action; @Column(columnDefinition="text") private String details; private Instant createdAt; protected AuditJpaEntity(){} public AuditJpaEntity(UUID id,String action,String details,Instant createdAt){this.id=id;this.action=action;this.details=details;this.createdAt=createdAt;} }
