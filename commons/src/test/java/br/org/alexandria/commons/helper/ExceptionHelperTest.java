package br.org.alexandria.commons.helper;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;

import br.org.alexandria.commons.exception.AlexandriaCommonsException;

public class ExceptionHelperTest {

  private ExceptionHelper helper;

  public ExceptionHelperTest () {
    this.helper = new ExceptionHelper ();
  }

  @Test
  public void testFindCauseFirstException () {
    Throwable expected = new AlexandriaCommonsException ("Root exception");
    Throwable actual = this.helper.findCause (expected);

    assertEquals (expected, actual);
  }

  @Test
  public void testFindCauseSecondException () {
    Throwable expected = new AlexandriaCommonsException ("Cause of exception");
    Throwable root = new IllegalArgumentException (expected);
    Throwable actual = this.helper.findCause (root);

    assertEquals (expected, actual);
  }

  @Test
  public void testFindCauseThirdException () {
    Throwable expected = new AlexandriaCommonsException ("Cause of exception");
    Throwable second = new IllegalArgumentException (expected);
    Throwable root = new IOException (second);
    Throwable actual = this.helper.findCause (root);

    assertEquals (expected, actual);
  }
}