package br.org.alexandria.commons.exception;

public class AlexandriaCommonsException extends RuntimeException {

  private static final long serialVersionUID = -458001856632126202L;

  public AlexandriaCommonsException () {
    this (null, null);
  }

  public AlexandriaCommonsException (String message) {
    this (message, null);
  }

  public AlexandriaCommonsException (Throwable cause) {
    this (null, cause);
  }

  public AlexandriaCommonsException (String message, Throwable cause) {
    super (message, cause);
  }
}