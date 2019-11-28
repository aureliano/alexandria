package br.org.alexandria.commons.helper;

import java.util.Objects;

public class ExceptionHelper {

  public Throwable findCause (Throwable throwable) {
    Objects.requireNonNull (throwable);
    Throwable rootCause = throwable;
    while ((rootCause.getCause () != null
        && rootCause.getCause () != rootCause)) {
      rootCause = rootCause.getCause ();
    }

    return rootCause;
  }
}