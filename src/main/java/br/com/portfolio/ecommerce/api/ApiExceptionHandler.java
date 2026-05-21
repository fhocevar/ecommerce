package br.com.portfolio.ecommerce.api;
import br.com.portfolio.ecommerce.shared.domain.BusinessException; import org.springframework.http.*; import org.springframework.web.bind.MethodArgumentNotValidException; import org.springframework.web.bind.annotation.*; import java.time.Instant; import java.util.*;
@RestControllerAdvice
public class ApiExceptionHandler {
 @ExceptionHandler(BusinessException.class) @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY) public ErrorResponse business(BusinessException ex){ return new ErrorResponse(Instant.now(),422,ex.getMessage()); }
 @ExceptionHandler(NoSuchElementException.class) @ResponseStatus(HttpStatus.NOT_FOUND) public ErrorResponse notFound(Exception ex){ return new ErrorResponse(Instant.now(),404,"Resource not found"); }
 @ExceptionHandler(MethodArgumentNotValidException.class) @ResponseStatus(HttpStatus.BAD_REQUEST) public ErrorResponse validation(MethodArgumentNotValidException ex){ return new ErrorResponse(Instant.now(),400,"Invalid request payload"); }
 public record ErrorResponse(Instant timestamp, int status, String message){}
}
