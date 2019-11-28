package br.org.alexandria.commons.security;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.security.NoSuchAlgorithmException;

import org.junit.Test;

import br.org.alexandria.commons.exception.AlexandriaCommonsException;

public class PasswordDigestTest {

  private PasswordDigest pd;

  public PasswordDigestTest () {
    this.pd = new PasswordDigest ();
  }

  @Test
  public void testDigestInvalidAlgorithm () {
    try {
      this.pd.encodePassword ("invalid", "test", "test", 1);
    } catch (AlexandriaCommonsException e) {
      assertTrue (e.getCause () instanceof NoSuchAlgorithmException);
    }
  }

  @Test
  public void testDigestSaltIsNull () {
    try {
      this.pd.encodePassword ("SHA-512", "test", null, 1);
    } catch (AlexandriaCommonsException e) {
      assertEquals ("You must provide a salt number.", e.getMessage ());
    }
  }

  @Test
  public void testDigestRoundsLesserThan1 () {
    try {
      this.pd.encodePassword ("SHA-512", "test", "test", 0);
    } catch (AlexandriaCommonsException e) {
      assertEquals ("Rounds parameter must be at least one.", e.getMessage ());
    }
  }

  @Test
  public void testDigestRoundsGreaterThan50 () {
    try {
      this.pd.encodePassword ("SHA-512", "test", "test", 51);
    } catch (AlexandriaCommonsException e) {
      assertEquals ("Max rounds allowed is 50.", e.getMessage ());
    }
  }

  @Test
  public void testDigest () {
    String expected = "ee26b0dd4af7e749aa1a8ee3c10ae9923f618980772e473f8819a5d4940e0db27ac185f8a0e1d5f84f88bc887fd67b143732c304cc5fa9ad8e6f57f50028a8ff";
    String actual = this.pd.encodePassword ("SHA-512", "test", "", 1);
    assertEquals (expected, actual);
  }
}