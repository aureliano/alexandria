package br.org.alexandria.webalexandria.exception;

import org.springframework.http.HttpStatus;

public class WebAlexandriaException extends RuntimeException {

  private static final long serialVersionUID = -4236500586967469448L;
  private static final HttpStatus INTERNAL_SERVER_ERROR = HttpStatus.INTERNAL_SERVER_ERROR;

  private HttpStatus httpStatus;

  public WebAlexandriaException () {
    this (null, null, INTERNAL_SERVER_ERROR);
  }

  public WebAlexandriaException (String message) {
    this (message, null, INTERNAL_SERVER_ERROR);
  }

  public WebAlexandriaException (Throwable cause) {
    this (null, cause, INTERNAL_SERVER_ERROR);
  }

  public WebAlexandriaException (String message, HttpStatus status) {
    this (message, null, status);
  }

  public WebAlexandriaException (String message, Throwable cause,
      HttpStatus status) {
    super (message, cause);
    this.httpStatus = status;
  }

  public HttpStatus getHttpStatus () {
    if (this.httpStatus == null) {
      this.httpStatus = INTERNAL_SERVER_ERROR;
    }
    return this.httpStatus;
  }
}