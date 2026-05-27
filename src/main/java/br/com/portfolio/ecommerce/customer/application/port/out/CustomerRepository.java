package br.com.portfolio.ecommerce.customer.application.port.out;
import br.com.portfolio.ecommerce.customer.domain.Customer; import java.util.*;
public interface CustomerRepository { Optional<Customer> findById(UUID id); }
