import org.junit.*;
import static org.junit.Assert.*;

public class AuthorTest {

  @Test
  public void author_instantiatesCorrectly_true() {
    Author newAuthor = new Author("Luca M","Reporter", "Born in may", "www.testUrl.com", "luca@gmail.com", "facebookLink", "twitterLink");
    assertTrue(newAuthor instanceof Author);
  }
}
