package br.org.alexandria.websecurity.exception;

public class WebSecurityException extends RuntimeException {

  private static final long serialVersionUID = 4805987492625278488L;

  public WebSecurityException () {
    this (null, null);
  }

  public WebSecurityException (String message) {
    this (message, null);
  }

  public WebSecurityException (Throwable cause) {
    this (null, cause);
  }

  public WebSecurityException (String message, Throwable cause) {
    super (message, cause);
  }
}