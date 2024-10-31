package in.vit.security.jwt.advice;


import in.vit.security.jwt.dto.ErrorDetails;
import in.vit.security.jwt.exception.ProductNotFoundException;
import in.vit.security.jwt.exception.TokenRefreshException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.exception.JDBCConnectionException;
import org.modelmapper.MappingException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.naming.CommunicationException;
import jakarta.servlet.http.HttpServletRequest;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.sql.SQLException;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class ExceptionHandler {

  @org.springframework.web.bind.annotation.ExceptionHandler(NoHandlerFoundException.class)
  protected ResponseEntity<Object> handleNoHandlerFoundException1( NoHandlerFoundException noHandlerFoundException, HttpHeaders headers, HttpStatus status, WebRequest request) {
    String error = "No handler found for " + noHandlerFoundException.getHttpMethod() + " " + noHandlerFoundException.getRequestURL();
    ErrorDetails apiError = new ErrorDetails(error, "RA_SYS_ERR_407");
    log.error("handleNoHandlerFoundException1 : {} ",apiError);
    return new ResponseEntity<>(apiError, new HttpHeaders(), HttpStatus.NOT_FOUND);
  }


  @org.springframework.web.bind.annotation.ExceptionHandler({CommunicationException.class, JDBCConnectionException.class,ConnectException.class, SocketTimeoutException.class})
  protected ResponseEntity<Object> handleNoHandlerFoundException( Exception exception) {
    ErrorDetails apiError = new ErrorDetails(exception.getMessage(), "RA_SYS_ERR_507");
    log.error("handleNoHandlerFoundException : {} ",apiError);
    return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
  }

  @org.springframework.web.bind.annotation.ExceptionHandler(ConstraintViolationException.class)
  public ResponseEntity<?> handleConstraintViolationException(ConstraintViolationException constraintViolationException) {
    ErrorDetails apiError = new ErrorDetails(constraintViolationException.getMessage(),"RA_SYS_ERR_487");
    log.error("handleConstraintViolationException : {} ",apiError);
    return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
  }


  @org.springframework.web.bind.annotation.ExceptionHandler(MappingException.class)
  public ResponseEntity<?> handleMappingException(MappingException mappingException) {
    HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
    HandlerMethod handlerMethod = (HandlerMethod) request.getAttribute(HandlerMapping.BEST_MATCHING_HANDLER_ATTRIBUTE);
    ErrorDetails apiError = new ErrorDetails(mappingException.getMessage(),"RA_SYS_ERR_506");
    log.error("handleMappingException : {} ",apiError);
    return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
  }


  @org.springframework.web.bind.annotation.ExceptionHandler(SQLException.class)
  public ResponseEntity<?> handleSQLException(SQLException sqlException) {
    ErrorDetails apiError = new ErrorDetails(sqlException.getMessage(),"RA_SYS_ERR_505");
    log.error("handleSQLException : {} ",apiError);
    return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
  }


  @org.springframework.web.bind.annotation.ExceptionHandler({MethodArgumentTypeMismatchException.class})
  public ResponseEntity<?> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException methodArgumentTypeMismatchException, WebRequest request) {
    String error = methodArgumentTypeMismatchException.getName() + " should be of type " + methodArgumentTypeMismatchException.getRequiredType().getName();
    ErrorDetails apiError = new ErrorDetails(error,"RA_SYS_ERR_406");
    log.error("handleMethodArgumentTypeMismatch : {} ",apiError);
    return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
  }


  @org.springframework.web.bind.annotation.ExceptionHandler(MethodArgumentNotValidException.class)
  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException methodArgumentNotValidException) {
    Map<String, String> errorsMap = new HashMap<>();
    methodArgumentNotValidException.getBindingResult().getFieldErrors().forEach((error) -> {
      errorsMap.put(error.getField(), error.getDefaultMessage());
    });
    ErrorDetails errorDetails = new ErrorDetails("method argument not valid", "RA_SYS_ERR_405");
    log.error("handleMethodArgumentNotValid : {} ",errorDetails);
    return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
  }


  @org.springframework.web.bind.annotation.ExceptionHandler(HttpMessageNotReadableException.class)
  public ResponseEntity<ErrorDetails> handleHttpMessageNotReadableException(HttpMessageNotReadableException httpMessageNotReadableException) {
    ErrorDetails errorDetails = new ErrorDetails(httpMessageNotReadableException.getMessage(), "RA_FW_ERR_484");
    log.error("handleHttpMessageNotReadableException : {} ",errorDetails);
    return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
  }


  @org.springframework.web.bind.annotation.ExceptionHandler(ProductNotFoundException.class)
  public ResponseEntity<ErrorDetails> handleProductNotFoundException(ProductNotFoundException productNotFoundException) {
    ErrorDetails errorDetails = new ErrorDetails(productNotFoundException.getMessage(), "RA_SYS_ERR_404");
    log.error("handleProductNotFoundException : {} ",errorDetails);
    return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
  }


  @org.springframework.web.bind.annotation.ExceptionHandler(IllegalArgumentException.class)
  public ResponseEntity<ErrorDetails> handleIllegalArgumentException(IllegalArgumentException illegalArgumentException) {
    ErrorDetails errorResponse = new ErrorDetails(illegalArgumentException.getMessage(), "RA_SYS_ERR_502");
    log.error("handleIllegalArgumentException:{}", errorResponse);
    return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
  }


  @org.springframework.web.bind.annotation.ExceptionHandler(RuntimeException.class)
  public ResponseEntity<ErrorDetails> handleRuntimeException(RuntimeException runtimeException) {
    ErrorDetails errorDetails = new ErrorDetails(runtimeException.getMessage(), "RA_SYS_ERR_501");
    log.error("handleRuntimeException :{} ",errorDetails);
    return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
  }


  @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorDetails> handleException(Exception exception) {
    ErrorDetails errorDetails = new ErrorDetails(exception.getMessage(), "RA_SYS_ERR_500");
    log.error("handleException : {} ",errorDetails);
    return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @org.springframework.web.bind.annotation.ExceptionHandler(value = TokenRefreshException.class)
  @ResponseStatus(HttpStatus.FORBIDDEN)
  public ErrorMessage handleTokenRefreshException(TokenRefreshException ex, WebRequest request) {
    return new ErrorMessage(
        HttpStatus.FORBIDDEN.value(),
        new Date(),
        ex.getMessage(),
        request.getDescription(false));
  }
}
