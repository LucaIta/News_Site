import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;


public class ArticleTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void article_instantiatesCorrectly_true() {
    Article newArticle = new Article("How to learn Coding in 2 months...","How to learn Coding","If you have ever...","url","How to","Coding bootcamps","Luca");
    assertTrue(newArticle instanceof Article);
  }


}
