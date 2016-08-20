import org.junit.rules.ExternalResource;
import org.sql2o.*;

public class DatabaseRule extends ExternalResource {

  @Override
  protected void before() {
    DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/news_site", null, null);
  }

  @Override
  protected void after() {
    try (Connection con = DB.sql2o.open()) {
      String deleteAuthorsQuery = "DELETE FROM authors *;";
      String deleteArticlesQuery = "DELETE FROM articles *;";
      con.createQuery(deleteAuthorsQuery).executeUpdate();
      con.createQuery(deleteArticlesQuery).executeUpdate();
    }
  }
}
