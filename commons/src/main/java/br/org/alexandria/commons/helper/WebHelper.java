package br.org.alexandria.commons.helper;

import java.net.URI;
import java.net.URISyntaxException;

import br.org.alexandria.commons.exception.AlexandriaCommonsException;

public class WebHelper {

  private static final Integer MIN_PAGE_SIZE = 1;
  private static final Integer MAX_PAGE_SIZE = 20;
  private static final Integer MIN_PAGE_NUM = 1;
  private static final Integer MAX_PAGE_NUM = 1000;

  public URI toURI (String path) {
    try {
      return new URI (path);
    } catch (URISyntaxException e) {
      throw new AlexandriaCommonsException (e);
    }
  }

  public void validatePageRequest (int num, int size) {
    validatePageRequest (num, size, MIN_PAGE_NUM, MAX_PAGE_NUM, MIN_PAGE_SIZE,
        MAX_PAGE_SIZE);
  }

  public void validatePageRequest (int num, int size, int minNum, int maxNum,
      int minSize, int maxSize) {
    String msg = null;

    if (num < minNum) {
      msg = String.format ("Page number must be at least %d.", minNum);
    } else if (num > maxNum) {
      msg = String.format ("Page number must be at most %d.", maxNum);
    } else if (size < minSize) {
      msg = String.format ("Page size must be at least %d.", minSize);
    } else if (size > maxSize) {
      msg = String.format ("Page size must be at most %d.", maxSize);
    }

    if (msg != null) {
      throw new AlexandriaCommonsException (msg);
    }
  }
}