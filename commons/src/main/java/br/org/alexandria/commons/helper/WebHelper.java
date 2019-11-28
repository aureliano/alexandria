package br.org.alexandria.commons.helper;

import java.net.URI;
import java.net.URISyntaxException;

import br.org.alexandria.commons.exception.AlexandriaCommonsException;

public class WebHelper {

  public URI toURI (String path) {
    try {
      return new URI (path);
    } catch (URISyntaxException e) {
      throw new AlexandriaCommonsException (e);
    }
  }
}