package br.org.alexandria.commons.helper;

import static org.junit.Assert.assertEquals;

import java.net.URI;

import org.junit.Test;

import br.org.alexandria.commons.exception.AlexandriaCommonsException;

public class WebHelperTest {

  private WebHelper helper;

  public WebHelperTest () {
    this.helper = new WebHelper ();
  }

  @Test(expected = AlexandriaCommonsException.class)
  public void testToURIError () {
    String url = "\\path/to/somewhere";
    helper.toURI (url);
  }

  @Test
  public void testToURI () {
    String url = "/path/to/somewhere";
    URI uri = helper.toURI (url);
    assertEquals (url, uri.toString ());
  }
}