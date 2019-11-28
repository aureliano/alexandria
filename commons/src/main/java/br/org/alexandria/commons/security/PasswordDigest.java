package br.org.alexandria.commons.security;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import br.org.alexandria.commons.exception.AlexandriaCommonsException;

public class PasswordDigest {

  public String encodePassword (String algorithm, String rawPassword,
      String salt, int rounds) {
    this.validateParameters (salt, rounds);
    MessageDigest md = this.createMessageDigest (algorithm);

    return this.calculateHash (md, rawPassword, salt, rounds);
  }

  private void validateParameters (String salt, int rounds) {
    if (salt == null) {
      throw new AlexandriaCommonsException ("You must provide a salt number.");
    } else if (rounds < 1) {
      throw new AlexandriaCommonsException (
          "Rounds parameter must be at least one.");
    } else if (rounds > 50) {
      throw new AlexandriaCommonsException ("Max rounds allowed is 50.");
    }
  }

  private MessageDigest createMessageDigest (String algorithm) {
    try {
      return MessageDigest.getInstance (algorithm);
    } catch (NoSuchAlgorithmException e) {
      throw new AlexandriaCommonsException (e);
    }
  }

  private String calculateHash (MessageDigest md, String rawPassword,
      String salt, int rounds) {
    String hash = "";
    for (int i = 0; i < rounds; i++) {
      String pass = rawPassword + salt + hash;
      md.update (pass.getBytes (StandardCharsets.UTF_8));
      byte[] data = md.digest ();

      StringBuilder builder = new StringBuilder ();

      for (byte b : data) {
        builder.append (String.format ("%02x", b & 0xff));
      }

      hash = builder.toString ();
    }

    return hash;
  }
}