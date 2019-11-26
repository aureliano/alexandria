package br.org.alexandria.websecurity.helper;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Objects;

import org.springframework.stereotype.Component;

import br.org.alexandria.websecurity.exception.WebSecurityException;

@Component
public final class WebHelper {

  public URI toURI (String path) {
    try {
      return new URI (path);
    } catch (URISyntaxException e) {
      throw new WebSecurityException (e);
    }
  }

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