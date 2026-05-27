package br.com.portfolio.ecommerce.shared.api;
import br.com.portfolio.ecommerce.shared.domain.BusinessException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.*; import org.springframework.web.bind.MethodArgumentNotValidException; import org.springframework.web.bind.annotation.*;
import java.time.Instant; import java.util.*;
@RestControllerAdvice
public class ApiExceptionHandler {
  @ExceptionHandler(BusinessException.class) ResponseEntity<Map<String,Object>> business(BusinessException ex, HttpServletRequest req){ return build(HttpStatus.UNPROCESSABLE_ENTITY, ex.getMessage(), req.getRequestURI()); }
  @ExceptionHandler(MethodArgumentNotValidException.class) ResponseEntity<Map<String,Object>> validation(MethodArgumentNotValidException ex, HttpServletRequest req){ return build(HttpStatus.BAD_REQUEST, "Payload inválido", req.getRequestURI()); }
  @ExceptionHandler(Exception.class) ResponseEntity<Map<String,Object>> generic(Exception ex, HttpServletRequest req){ return build(HttpStatus.INTERNAL_SERVER_ERROR, "Erro interno", req.getRequestURI()); }
  private ResponseEntity<Map<String,Object>> build(HttpStatus s,String m,String p){ Map<String,Object> body=new LinkedHashMap<>(); body.put("timestamp", Instant.now()); body.put("status", s.value()); body.put("error", s.getReasonPhrase()); body.put("message", m); body.put("path", p); return ResponseEntity.status(s).body(body); }
}
