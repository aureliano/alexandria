package br.org.alexandria.websecurity.helper;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import br.org.alexandria.websecurity.exception.WebSecurityException;

@Component
public final class SecurityHelper {

  @Value("${web-security.salt}")
  private String salt;

  public String encodePassword (String rawPassword) {
    MessageDigest md = null;

    try {
      md = MessageDigest.getInstance ("SHA-512");
    } catch (NoSuchAlgorithmException e) {
      throw new WebSecurityException (e);
    }

    md.update (this.salt.getBytes ());
    byte[] pwd = md.digest (rawPassword.getBytes (StandardCharsets.UTF_8));

    StringBuilder builder = new StringBuilder ();

    for (byte b : pwd) {
      builder.append (String.format ("%02x", b & 0xff));
    }

    return builder.toString ();
  }
}