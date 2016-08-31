import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

public class PasswordEncrypterTest {

  @Test
  public void getSha1SecurePassword_encryptPasswordCorrectly_encryptedPassword() {
    byte[] salt = PasswordEncrypter.getSalt();
    String hashedPassword1 = PasswordEncrypter.getSha1SecurePassword("password", salt);
    String hashedPassword2 = PasswordEncrypter.getSha1SecurePassword("password", salt);
    assertEquals(hashedPassword1,hashedPassword2);
  }
}
