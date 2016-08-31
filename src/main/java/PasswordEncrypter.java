import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class PasswordEncrypter {

  public static String getSha1SecurePassword(String passwordToHash, byte[] salt) {
    String generatedPassword = null;
    try {
      MessageDigest md = MessageDigest.getInstance("SHA-1");
      md.update(salt);
      byte[] bytes = md.digest(passwordToHash.getBytes());
      StringBuilder sb = new StringBuilder();
      for(int i=0; i< bytes.length ;i++) {
        sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
      }
      generatedPassword = sb.toString();
    }
    catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    }
    return generatedPassword; 
  }

  public static byte[] getSalt() {
    byte[] salt = new byte[16];
    try {
      SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
      sr.nextBytes(salt);
    } catch (NoSuchAlgorithmException e) {
      System.err.println("NoSuchAlgorithmException: " + e.getMessage());
    }
    return salt;
  }

}
