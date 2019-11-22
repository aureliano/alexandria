package br.org.alexandria.websecurity.helper;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Objects;

import br.org.alexandria.websecurity.exception.WebSecurityException;

public final class WebHelper {

  public static URI toURI (String path) {
    try {
      return new URI (path);
    } catch (URISyntaxException e) {
      throw new WebSecurityException (e);
    }
  }

  public static Throwable findCause (Throwable throwable) {
    Objects.requireNonNull (throwable);
    Throwable rootCause = throwable;
    while ((rootCause.getCause () != null
        && rootCause.getCause () != rootCause)) {
      rootCause = rootCause.getCause ();
    }

    return rootCause;
  }
}