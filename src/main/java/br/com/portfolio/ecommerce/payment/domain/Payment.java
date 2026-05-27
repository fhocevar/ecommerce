package br.com.portfolio.ecommerce.payment.domain;
import java.time.*; import java.util.*;
public class Payment { private final UUID id; private final UUID orderId; private final String status; private final String externalReference; private final Instant createdAt; public Payment(UUID id,UUID orderId,String status,String externalReference,Instant createdAt){this.id=id;this.orderId=orderId;this.status=status;this.externalReference=externalReference;this.createdAt=createdAt;} }
