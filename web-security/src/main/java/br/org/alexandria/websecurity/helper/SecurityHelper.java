package br.org.alexandria.websecurity.helper;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import br.org.alexandria.websecurity.exception.WebSecurityException;

public final class SecurityHelper {

  public static String encodePassword (String rawPassword) {
    String salt = System.getProperty ("web-security.salt");

    MessageDigest md = null;

    try {
      md = MessageDigest.getInstance ("SHA-512");
    } catch (NoSuchAlgorithmException e) {
      throw new WebSecurityException (e);
    }

    md.update (salt.getBytes ());
    byte[] pwd = md.digest (rawPassword.getBytes (StandardCharsets.UTF_8));

    return new String (pwd);
  }
}