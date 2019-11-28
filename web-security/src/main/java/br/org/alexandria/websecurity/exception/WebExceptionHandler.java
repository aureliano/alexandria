package br.org.alexandria.websecurity.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.org.alexandria.commons.helper.ExceptionHelper;

@ControllerAdvice
public class WebExceptionHandler extends ResponseEntityExceptionHandler {

  @Autowired
  private ExceptionHelper exceptionHelper;

  private static final Logger logger = LoggerFactory
      .getLogger (WebExceptionHandler.class);

  @ExceptionHandler(value = { DataIntegrityViolationException.class })
  protected ResponseEntity<Object> handleConflict (RuntimeException ex,
      WebRequest request) {

    Throwable rootCause = this.exceptionHelper.findCause (ex);
    HttpStatus status = HttpStatus.CONFLICT;
    logger.error (ex.getMessage (), ex);

    return handleExceptionInternal (ex, rootCause.getMessage (),
        new HttpHeaders (), status, request);
  }

  @ExceptionHandler(value = { WebSecurityException.class })
  protected ResponseEntity<Object> handleWebSecurityException (
      WebSecurityException ex, WebRequest request) {

    Throwable rootCause = this.exceptionHelper.findCause (ex);
    logger.warn ("Handled exception: {}\nCause: {}\nResponse code: {}",
        ex.getClass ().getName (), rootCause.getMessage (),
        ex.getHttpStatus ());

    return handleExceptionInternal (ex, rootCause.getMessage (),
        new HttpHeaders (), ex.getHttpStatus (), request);
  }
}