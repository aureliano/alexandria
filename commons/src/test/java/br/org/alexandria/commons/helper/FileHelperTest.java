package br.org.alexandria.commons.helper;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import br.org.alexandria.commons.exception.AlexandriaCommonsException;

public class FileHelperTest {

  private FileHelper helper;

  public FileHelperTest () {
    this.helper = new FileHelper ();
  }

  @Test(expected = AlexandriaCommonsException.class)
  public void testResolveImageSubDirectorySeedIsNull () {
    this.helper.resolveImageSubDirectory (null);
  }

  @Test
  public void testResolveImageSubDirectory () {
    String expected = "C4/CA";
    String actual = this.helper.resolveImageSubDirectory ("1").toString ();

    assertEquals (expected, actual);
  }
}