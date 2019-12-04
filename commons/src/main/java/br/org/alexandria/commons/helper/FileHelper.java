package br.org.alexandria.commons.helper;

import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import br.org.alexandria.commons.exception.AlexandriaCommonsException;

public class FileHelper {

  private static final String ALGORITHM = "MD5";

  private MessageDigest messageDigest;

  public Path resolveFileSubDirectory (String seed) {
    if (seed == null) {
      throw new AlexandriaCommonsException ("Seed is required.");
    }

    if (this.messageDigest == null) {
      this.messageDigest = this.createMessageDigest ();
    }

    this.messageDigest.update (seed.getBytes (StandardCharsets.UTF_8));
    byte[] data = this.messageDigest.digest ();

    StringBuilder builder = new StringBuilder ();

    for (byte b : data) {
      builder.append (String.format ("%02x", b & 0xff).toUpperCase ());
    }

    Path subDir = Paths.get (builder.substring (0, 2),
        builder.substring (2, 4));

    return subDir;
  }

  private MessageDigest createMessageDigest () {
    try {
      return MessageDigest.getInstance (ALGORITHM);
    } catch (NoSuchAlgorithmException e) {
      throw new AlexandriaCommonsException (e);
    }
  }
}