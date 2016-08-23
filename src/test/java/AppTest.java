import org.fluentlenium.adapter.FluentTest;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.Rule;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import static org.assertj.core.api.Assertions.assertThat;
import static org.fluentlenium.core.filter.FilterConstructor.*;

public class AppTest extends FluentTest {
  public WebDriver webDriver = new HtmlUnitDriver();

  @Override
  public WebDriver getDefaultDriver() {
    return webDriver;
  }

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @ClassRule
  public static ServerRule server = new ServerRule();

  @Test
  public void loginPageIsDisplayed() {
    goTo("http://localhost:4567/");
    assertThat(pageSource()).contains("Username");
  }

  @Test
  public void newArticlePageIsDisplayed() {
    goTo("http://localhost:4567/articles/new");
    assertThat(pageSource()).contains("Add an Article");
  }

  @Test
  public void newAuthorPageIsDisplayed() {
    goTo("http://localhost:4567/authors/new");
    assertThat(pageSource()).contains("Add an Author");
  }

  @Test
  public void loginWorksCorrectly() {
    Author newAuthor = new Author("Luca M","Reporter", "Born in may", "www.testUrl.com", "luca@gmail.com", "facebookLink", "twitterLink", "123456");
    newAuthor.save();
    goTo("http://localhost:4567/");
    fill("#username").with("Luca M");
    fill("#password").with("123456");
    submit("#loginBtn");
    assertThat(pageSource()).contains("click here to create a new Article");
  }

  @Test
  public void linksOnHubPageWorks() {
    goTo("http://localhost:4567/hub");
    submit("#newArticleBtn");
    assertThat(pageSource().contains("Article Title"));
    goTo("http://localhost:4567/hub");
    submit("#newAuthorBtn");
    assertThat(pageSource()).contains("Author Name");
  }

  @Test
  public void articlesGetsDisplayed() {
    Article newArticle = new Article("title","shortTitle","this article is about...","picture","subhead","subtitle","authorByLine");
    newArticle.save();
    goTo("http://localhost:4567/hub");
    assertThat(pageSource()).contains("this article is about...");
  }

  @Test
  public void articleEditPageIsDisplayed() {
    Article newArticle = new Article("How to learn English","shortTitle","this article is about...","picture","subhead","An Easy Way to Learn English","authorByLine");
    newArticle.save();
    goTo("http://localhost:4567/hub");
    click("a", withText("How to learn English"));
    assertThat(pageSource()).contains("An Easy Way to Learn English");
  }

  @Test
  public void articleGetsEditedCorrectly() {
    Article newArticle = new Article("How to learn English","shortTitle","this article is about...","picture","subhead","An Easy Way to Learn English","authorByLine");
    newArticle.save();
    String url = String.format("http://localhost:4567/articles/%d/edit", newArticle.getId());
    goTo(url);
    fill("#newTitle").with("editedTitle");
    fill("#newShortTitle").with("editedShortTitle");
    fill("#newBody").with("editedBody");
    fill("#newPicture").with("editedPicture");
    fill("#newSubhead").with("editedSubhead");
    fill("#newSubtitle").with("editedSubtitle");
    fill("#newAuthorByLine").with("editedAuthorByLine");
    submit("#editBtn");
    assertThat(pageSource()).contains("click here to create a new Article");
    assertThat(pageSource()).contains("editedTitle");
    click("a", withText("editedTitle"));
    assertThat(pageSource()).contains("editedTitle");
    assertThat(pageSource()).contains("editedShortTitle");
    assertThat(pageSource()).contains("editedBody");
    assertThat(pageSource()).contains("editedPicture");
    assertThat(pageSource()).contains("editedSubhead");
    assertThat(pageSource()).contains("editedSubtitle");
    assertThat(pageSource()).contains("editedAuthorByLine");
  }

  @Test
  public void articleGetsDelitedCorrectly() {
    Article article1 = new Article("How to learn English","shortTitle","this article is about...","picture","subhead","An Easy Way to Learn English","authorByLine");
    Article article2 = new Article("How to learn Spanish","shortTitle","this article is about...","picture","subhead","An Easy Way to Learn English","authorByLine");
    article1.save();
    article2.save();
    String url = String.format("http://localhost:4567/articles/%d/edit", article1.getId());
    goTo(url);
    submit("#deleteBtn");
    assertThat(pageSource()).contains("How to learn Spanish");
    assertThat(pageSource()).doesNotContain("How to learn English");
  }
}
