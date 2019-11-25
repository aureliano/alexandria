package br.org.alexandria.websecurity.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.org.alexandria.websecurity.helper.WebHelper;

@ControllerAdvice
public class WebExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(value = { DataIntegrityViolationException.class })
  protected ResponseEntity<Object> handleConflict (RuntimeException ex,
      WebRequest request) {

    Throwable rootCause = WebHelper.findCause (ex);
    return handleExceptionInternal (ex, rootCause.getMessage (),
        new HttpHeaders (), HttpStatus.CONFLICT, request);
  }

  @ExceptionHandler(value = { WebSecurityException.class })
  protected ResponseEntity<Object> handleWebSecurityException (
      WebSecurityException ex, WebRequest request) {

    Throwable rootCause = WebHelper.findCause (ex);
    return handleExceptionInternal (ex, rootCause.getMessage (),
        new HttpHeaders (), ex.getHttpStatus (), request);
  }
}