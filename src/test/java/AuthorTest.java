import org.junit.*;
import static org.junit.Assert.*;

public class AuthorTest {

  @Test
  public void author_instantiatesCorrectly_true() {
    Author newAuthor = new Author();
    assertTrue(newAuthor instanceof Author);
  }
}
