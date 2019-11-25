package br.org.alexandria.websecurity.exception;

import org.springframework.http.HttpStatus;

public class WebSecurityException extends RuntimeException {

  private static final long serialVersionUID = 4805987492625278488L;
  private static final HttpStatus INTERNAL_SERVER_ERROR = HttpStatus.INTERNAL_SERVER_ERROR;

  private HttpStatus httpStatus;

  public WebSecurityException () {
    this (null, null, INTERNAL_SERVER_ERROR);
  }

  public WebSecurityException (String message) {
    this (message, null, INTERNAL_SERVER_ERROR);
  }

  public WebSecurityException (Throwable cause) {
    this (null, cause, INTERNAL_SERVER_ERROR);
  }

  public WebSecurityException (String message, HttpStatus status) {
    this (message, null, status);
  }

  public WebSecurityException (String message, Throwable cause,
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